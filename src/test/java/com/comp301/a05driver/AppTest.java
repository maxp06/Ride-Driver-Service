package com.comp301.a05driver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testExpand() {
        Driver d1 = new DriverImpl("max", "peng", 1, new VehicleImpl("", "", "", new PositionImpl(1, 2)));
        Driver d2 = new DriverImpl("max", "peng", 2, new VehicleImpl("", "", "", new PositionImpl(2, 2)));
        Driver d3 = new DriverImpl("max", "peng", 1, new VehicleImpl("", "", "", new PositionImpl(1, 3)));
        List<Driver> driverPool = new ArrayList<>();
        driverPool.add(d1);
        driverPool.add(d2);
        driverPool.add(d3);

        Position client = new PositionImpl(1, 1);

        ExpandingProximityIterator exi = new ExpandingProximityIterator(driverPool, client, 2);

        assertTrue(exi.hasNext());
        assertEquals(d1, exi.next());
        assertTrue(exi.hasNext());
        assertEquals(d2, exi.next());
        //assertTrue(exi.hasNext());
        //assertEquals(d3, exi.next());
        //assertFalse(exi.hasNext());
    }

    @Test
    public void testSnake() {
        Driver d1 = new DriverImpl("", "", 1, new VehicleImpl("", "", "", new PositionImpl(1, 2)));
        Driver d2 = new DriverImpl("", "", 2, new VehicleImpl("", "", "", new PositionImpl(10, 10)));
        Driver d3 = new DriverImpl("", "", 1, new VehicleImpl("", "", "", new PositionImpl(1, 3)));

        Driver d4 = new DriverImpl("", "", 1, new VehicleImpl("", "", "", new PositionImpl(1, 3)));
        Driver d5 = new DriverImpl("", "", 1, new VehicleImpl("", "", "", new PositionImpl(2, 3)));

        Driver d6 = new DriverImpl("", "", 1, new VehicleImpl("", "", "", new PositionImpl(12, 2)));
        Driver d7 = new DriverImpl("", "", 2, new VehicleImpl("", "", "", new PositionImpl(10, 8)));
        Driver d8 = new DriverImpl("", "", 1, new VehicleImpl("", "", "", new PositionImpl(1, 5)));

        List<Driver> driverPool = new ArrayList<>();
        driverPool.add(d1);
        driverPool.add(d2);
        driverPool.add(d3);
        System.out.println(driverPool);

        List<Driver> driverPool2 = new ArrayList<>();
        driverPool2.add(d4);
        driverPool2.add(d5);
        System.out.println(driverPool2);

        List<Driver> driverPool3 = new ArrayList<>();
        driverPool3.add(d6);
        driverPool3.add(d7);
        driverPool3.add(d8);
        System.out.println(driverPool3);

        List<Iterable<Driver>> pools = new ArrayList<>();
        pools.add(driverPool);
        pools.add(driverPool2);
        pools.add(driverPool3);

        SnakeOrderAcrossPoolsIterator soap = new SnakeOrderAcrossPoolsIterator(pools);
        assertEquals(soap.next(), d1);
        assertEquals(soap.next(), d4);
        assertEquals(soap.next(), d6);
        assertEquals(soap.next(), d7);
        assertEquals(soap.next(), d5);
        assertEquals(soap.next(), d2);
        assertEquals(soap.next(), d3);
        assertEquals(soap.next(), d8);

    }
}
