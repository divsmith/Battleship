package edu.weberstate.cs3230.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by parker on 2/12/17.
 */
class SubmarineTest {
    private Submarine ship;
    private static String NAME = "Submarine";
    private static int LENGTH = 3;

    @BeforeEach
    void setUp() {
        ship = new Submarine();
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
        Assertions.assertEquals(ship.hit(), Ship.ShipState.sunk);
    }

    @Test
    void ship_reports_sunk_if_hit_after_sunk()
    {
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
    }

    @Test
    void submarine_copy_is_correct_type()
    {
        Submarine submarine = ship.copy();
        Assertions.assertTrue(submarine instanceof Submarine);
    }

    @Test
    void submarine_copy_is_returns_different_object()
    {
        Submarine submarine = ship.copy();
        Assertions.assertNotSame(ship, submarine);
    }

    @Test
    void submarine_copy_constructor_copies_all_properties()
    {
        ship.hit();
        Submarine submarine = ship.copy();

        Assertions.assertEquals(ship.getHits(), submarine.getHits());
        Assertions.assertEquals(ship.getShipState(), submarine.getShipState());
        Assertions.assertEquals(ship.getLength(), submarine.getLength());
        Assertions.assertEquals(ship.getName(), submarine.getName());
        Assertions.assertEquals(ship.getClass(), submarine.getClass());
    }

    @Test
    void submarine_copy_is_deep_copy()
    {
        Submarine submarine = ship.copy();
        ship.hit();
        ship.hit();
        ship.hit();
        ship.hit();
        ship.hit();

        Assertions.assertNotEquals(ship.getHits(), submarine.getHits());
        Assertions.assertNotEquals(ship.getShipState(), submarine.getShipState());
    }
}