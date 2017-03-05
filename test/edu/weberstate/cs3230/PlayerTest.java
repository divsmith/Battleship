package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by parker on 3/4/17.
 */
class PlayerTest {
    @Test
    void name_is_set_and_returned()
    {
        Player player = new Player("Parker");
        Assertions.assertEquals("Parker", player.getName());
    }

    @Test
    void player_number_is_set_correctly()
    {
        Player player1 = new Player();
        Player player2 = new Player();

        Assertions.assertEquals(1, player1.getPlayerNumber());
        Assertions.assertEquals(2, player2.getPlayerNumber());
    }

    @Test
    void validly_placed_ships_are_added_to_list()
    {
        Player player = new Player();
        Patrol patrol = new Patrol();

        player.placeShip(patrol, new Placement('a', 0, 'h'));

        List<Ship> list = player.getShips();

        Assertions.assertEquals(patrol, list.get(0));
    }

    @Test
    void invalidly_placed_ships_are_not_added_to_list()
    {
        Player player = new Player();
        Patrol patrol = new Patrol();

        player.placeShip(patrol, new Placement('w', 0, 'h'));

        List<Ship> list = player.getShips();

        Assertions.assertEquals(0, list.size());
    }
}