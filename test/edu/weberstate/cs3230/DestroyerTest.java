package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by parker on 2/12/17.
 */
class DestroyerTest {
    private Destroyer ship;
    private static String NAME = "Destroyer";
    private static int LENGTH = 3;

    @BeforeEach
    void setUp() {
        ship = new Destroyer();
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
        ship.hit();
        Assertions.assertEquals(ship.getHits(), 4);
    }

    @Test
    void destroyer_copy_is_correct_type()
    {
        Destroyer destroyer = ship.copy();
        Assertions.assertTrue(destroyer instanceof Destroyer);
    }

    @Test
    void destroyer_copy_is_returns_different_object()
    {
        Destroyer destroyer = ship.copy();
        Assertions.assertNotSame(ship, destroyer);
    }

    @Test
    void destroyer_copy_constructor_copies_all_properties()
    {
        ship.hit();
        Destroyer destroyer = ship.copy();

        Assertions.assertEquals(ship.getHits(), destroyer.getHits());
        Assertions.assertEquals(ship.getShipState(), destroyer.getShipState());
        Assertions.assertEquals(ship.getLength(), destroyer.getLength());
        Assertions.assertEquals(ship.getName(), destroyer.getName());
        Assertions.assertEquals(ship.getClass(), destroyer.getClass());
    }

    @Test
    void destroyer_copy_is_deep_copy()
    {
        Destroyer destroyer = ship.copy();
        ship.hit();
        ship.hit();
        ship.hit();

        Assertions.assertNotEquals(ship.getHits(), destroyer.getHits());
        Assertions.assertNotEquals(ship.getShipState(), destroyer.getShipState());
    }

}