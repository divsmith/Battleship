package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by parker on 2/12/17.
 */
class CarrierTest {
    private Ship ship;
    private static String NAME = "Carrier";
    private static int MAXHITS = 5;

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
    void ship_has_correct_max_hits()
    {
        Assertions.assertEquals(ship.getLength(), MAXHITS);
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

}