package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by parker on 2/12/17.
 */
class BattleshipTest {
    private Battleship ship;
    private static String NAME = "Battleship";
    private static int LENGTH = 4;

    @BeforeEach
    void setUp() {
        ship = new Battleship();
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
        Assertions.assertEquals(ship.hit(), Ship.ShipState.sunk);
    }

    @Test
    void ship_reports_sunk_if_hit_after_sunk()
    {
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
    }

    @Test
    void battleship_copy_is_correct_type()
    {
        Battleship battleship = new Battleship(ship);
        Assertions.assertTrue(battleship instanceof Battleship);
    }

    @Test
    void battleship_copy_is_returns_different_object()
    {
        Battleship battleship = new Battleship(ship);
        Assertions.assertNotSame(ship, battleship);
    }

    @Test
    void battleship_copy_constructor_copies_all_properties()
    {
        ship.hit();
        Battleship battleship = new Battleship(ship);

        Assertions.assertEquals(ship.getHits(), battleship.getHits());
        Assertions.assertEquals(ship.getShipState(), battleship.getShipState());
        Assertions.assertEquals(ship.getLength(), battleship.getLength());
        Assertions.assertEquals(ship.getName(), battleship.getName());
        Assertions.assertEquals(ship.getClass(), battleship.getClass());
    }

    @Test
    void battleship_copy_is_deep_copy()
    {
        Battleship battleship = new Battleship(ship);
        ship.hit();
        ship.hit();
        ship.hit();
        ship.hit();

        Assertions.assertNotEquals(ship.getHits(), battleship.getHits());
        Assertions.assertNotEquals(ship.getShipState(), battleship.getShipState());
    }
}