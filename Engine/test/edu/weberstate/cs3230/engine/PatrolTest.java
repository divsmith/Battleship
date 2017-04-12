package edu.weberstate.cs3230.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by parker on 2/12/17.
 */
class PatrolTest {
    private Patrol ship;
    private static String NAME = "Patrol";
    private static int LENGTH = 2;

    @BeforeEach
    void setUp() {
        ship = new Patrol();
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
        Assertions.assertEquals(ship.hit(), Ship.ShipState.sunk);
    }

    @Test
    void ship_reports_sunk_if_hit_after_sunk()
    {
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
    }

    @Test
    void patrol_copy_is_correct_type()
    {
        Patrol patrol = ship.copy();
        Assertions.assertTrue(patrol instanceof Patrol);
    }

    @Test
    void patrol_copy_is_returns_different_object()
    {
        Patrol patrol = ship.copy();
        Assertions.assertNotSame(ship, patrol);
    }

    @Test
    void patrol_copy_constructor_copies_all_properties()
    {
        ship.hit();
        Patrol patrol = ship.copy();

        Assertions.assertEquals(ship.getHits(), patrol.getHits());
        Assertions.assertEquals(ship.getShipState(), patrol.getShipState());
        Assertions.assertEquals(ship.getLength(), patrol.getLength());
        Assertions.assertEquals(ship.getName(), patrol.getName());
        Assertions.assertEquals(ship.getClass(), patrol.getClass());
    }

    @Test
    void patrol_copy_is_deep_copy()
    {
        Patrol patrol = ship.copy();
        ship.hit();
        ship.hit();

        Assertions.assertNotEquals(ship.getHits(), patrol.getHits());
        Assertions.assertNotEquals(ship.getShipState(), patrol.getShipState());
    }
}