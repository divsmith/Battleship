package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by parker on 2/12/17.
 */
class CarrierTest {
    private Carrier ship;
    private static String NAME = "Carrier";
    private static int LENGTH = 5;

    @BeforeEach
    void setUp() {
        ship = new Carrier();
    }

    @Test
    void ship_has_correct_name()
    {
        Assertions.assertEquals(ship.getName(), NAME);
    }

    @Test
    void ship_has_correct_length()
    {
        Assertions.assertEquals(ship.getLength(), LENGTH);
    }

    @Test
    void ship_sinks_after_correct_number_of_hits()
    {
        Assertions.assertEquals(ship.hit(), Ship.ShipState.floating);
        Assertions.assertEquals(ship.hit(), Ship.ShipState.floating);
        Assertions.assertEquals(ship.hit(), Ship.ShipState.floating);
        Assertions.assertEquals(ship.hit(), Ship.ShipState.floating);
        Assertions.assertEquals(ship.hit(), Ship.ShipState.sunk);
    }

    @Test
    void ship_reports_sunk_if_hit_after_sunk()
    {
        ship.hit();
        ship.hit();
        ship.hit();
        ship.hit();
        ship.hit();
        Assertions.assertEquals(ship.hit(), Ship.ShipState.sunk);
    }

    @Test
    void ship_reports_correct_number_of_hits()
    {
        Assertions.assertEquals(ship.getHits(), 0);
        ship.hit();
        Assertions.assertEquals(ship.getHits(), 1);
        ship.hit();
        Assertions.assertEquals(ship.getHits(), 2);
        ship.hit();
        Assertions.assertEquals(ship.getHits(), 3);
        ship.hit();
        Assertions.assertEquals(ship.getHits(), 4);
        ship.hit();
        Assertions.assertEquals(ship.getHits(), 5);
        ship.hit();
        Assertions.assertEquals(ship.getHits(), 6);
    }

    @Test
    void carrier_copy_is_correct_type()
    {
        Carrier carrier = new Carrier(ship);
        Assertions.assertTrue(carrier instanceof Carrier);
    }

    @Test
    void carrier_copy_is_returns_different_object()
    {
        Carrier carrier = new Carrier(ship);
        Assertions.assertNotSame(ship, carrier);
    }

    @Test
    void carrier_copy_constructor_copies_all_properties()
    {
        ship.hit();
        Carrier carrier = new Carrier(ship);

        Assertions.assertEquals(ship.getHits(), carrier.getHits());
        Assertions.assertEquals(ship.getShipState(), carrier.getShipState());
        Assertions.assertEquals(ship.getLength(), carrier.getLength());
        Assertions.assertEquals(ship.getName(), carrier.getName());
        Assertions.assertEquals(ship.getClass(), carrier.getClass());
    }

    @Test
    void carrier_copy_is_deep_copy()
    {
        Carrier carrier = new Carrier(ship);
        ship.hit();
        ship.hit();
        ship.hit();
        ship.hit();
        ship.hit();

        Assertions.assertNotEquals(ship.getHits(), carrier.getHits());
        Assertions.assertNotEquals(ship.getShipState(), carrier.getShipState());
    }
}