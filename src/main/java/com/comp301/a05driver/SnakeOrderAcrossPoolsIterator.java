package com.comp301.a05driver;

import java.util.Iterator;
import java.util.*;
import java.util.NoSuchElementException;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
    private List<Iterator<Driver>> driverPools;
    private List<Iterator<Driver>> driverPoolsSize;
    private Iterator<Driver> driverIterator2;
    private Driver nextDriver;
    private int total;
    private int num = 0;
    private int i;
    private boolean forward = true;

    public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driverPools) {
        this.driverPools = new ArrayList<>();
        for(Iterable<Driver> drivers : driverPools) {
            this.driverPools.add(drivers.iterator());
        }
        this.driverPoolsSize = new ArrayList<>();
        for(Iterable<Driver> drivers : driverPools) {
            this.driverPoolsSize.add(drivers.iterator());
        }
        nextDriver = null;
        this.i = 0;
    }

    public void totalSize() {
        for(int i = 0; i < driverPoolsSize.size(); i ++) {
            driverIterator2 = driverPoolsSize.get(i);
            while (driverIterator2.hasNext()) {
                driverIterator2.next();
                total++;
            }
        }
    }


    public void loadNextDriver() {
        if(nextDriver != null) {
            return;
        }
        totalSize();
        boolean keepGoing = true;
        while(keepGoing && num < total) {
            if(driverPools.get(i).hasNext()) {
                nextDriver = driverPools.get(i).next();
                num++;
                keepGoing = false;
            }
            if(forward) {
                i++;
                if(i>=driverPools.size()) {
                    i--;
                    forward = false;
                }
            } else {
                i--;
                if(i<0) {
                    i++;
                    forward = true;
                }
            }
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
