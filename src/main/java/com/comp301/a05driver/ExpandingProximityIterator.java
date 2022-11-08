package com.comp301.a05driver;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver> {
    private Iterator<Driver> countIterator;
    private Iterator<Driver> iterator;
    private Position clientPosition;
    private int expansionStep;
    private Driver nextDriver;
    private int total;
    private int counter = 0;
    private Iterable<Driver> driverPool;
    private int lower;
    private int upperVariable;
    private int upper;

    public ExpandingProximityIterator(Iterable<Driver> driverPool, Position clientPosition, int expansionStep) {
        if (driverPool == null || clientPosition == null) {
            throw new IllegalArgumentException();
        }
        this.driverPool = driverPool;
        countIterator = this.driverPool.iterator();
        iterator = driverPool.iterator();
        this.clientPosition = clientPosition;
        this.expansionStep = expansionStep;
        nextDriver = null;
        lower = -1;
        upperVariable = 0;
        upper = 1;
    }

    public void totalDrivers() {
        while (countIterator.hasNext()) {
            countIterator.next();
            total ++;
        }
    }

    public void iteratorReset() {
        if (driverPool == null) {
            return;
        }
        iterator = driverPool.iterator();
    }

    private void loadNextDriver() {
        if (nextDriver != null) {
            return;
        }
        totalDrivers();
        while (counter < total) {
            while (iterator.hasNext()) {
                Driver potential = iterator.next();
                if (clientPosition.getManhattanDistanceTo(potential.getVehicle().getPosition()) <= upper && clientPosition.getManhattanDistanceTo(potential.getVehicle().getPosition()) > lower) {
                    nextDriver = potential;
                    counter ++;
                    return;
                }
            }
            iteratorReset();
            lower = upper;
            upperVariable += 1;
            upper = 1 + upperVariable * expansionStep;
        }
    }

    @Override
    public boolean hasNext() {
        loadNextDriver();
        return nextDriver != null;
    }

    @Override
    public Driver next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Driver localDriver = nextDriver;
        nextDriver = null;
        return localDriver;
    }
}