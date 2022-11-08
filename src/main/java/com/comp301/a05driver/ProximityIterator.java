package com.comp301.a05driver;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {
    private Position clientPosition;
    private int proximityRange;
    private Iterator<Driver> iterator;
    private Driver nextDriver;

    public ProximityIterator(Iterable<Driver> driverPool, Position clientPosition, int proximityRange) {
        if (driverPool == null || clientPosition == null) {
            throw new IllegalArgumentException();
        }
        iterator = driverPool.iterator();
        this.clientPosition = clientPosition;
        this.proximityRange = proximityRange;
        nextDriver = null;
    }

    private void loadNextDriver() {
        if (nextDriver != null) {
            return;
        } else {
            while (iterator.hasNext()) {
                Driver potential = iterator.next();
                if (clientPosition.getManhattanDistanceTo(potential.getVehicle().getPosition()) <= proximityRange) {
                    nextDriver = potential;
                    return;
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        loadNextDriver();
        if (nextDriver == null) {
            return false;
        }
        return true;
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
