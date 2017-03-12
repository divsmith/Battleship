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
    void validly_placed_ships_are_added_to_list()
    {
        Player player = new Player();
        Ship ship = player.getUnplacedShips().get(0);
        player.placeShip(0, new Placement('a', 0, 'v'));

        Assertions.assertFalse(player.getUnplacedShips().contains(ship));
        Assertions.assertTrue(player.getShips().contains(ship));
    }

    @Test
    void invalidly_placed_ships_are_not_added_to_list()
    {
        Player player = new Player();
        Ship ship = player.getUnplacedShips().get(0);
        player.placeShip(0, new Placement('w', 0, 'h'));

        Assertions.assertTrue(player.getUnplacedShips().contains(ship));
        Assertions.assertFalse(player.getShips().contains(ship));
    }

    @Test
    void default_ship_list_is_not_empty()
    {
        Player player = new Player();

        Assertions.assertNotEquals(0, player.getUnplacedShips().size());
    }

    @Test
    void hasShipsToPlace_returns_correctly()
    {
        Player player = new Player();

        Assertions.assertTrue(player.hasShipsToPlace());
        player.placeShip(0, new Placement('a', 0, 'h'));
        player.placeShip(0, new Placement('b', 0, 'h'));
        player.placeShip(0, new Placement('c', 0, 'h'));
        player.placeShip(0, new Placement('d', 0, 'h'));
        player.placeShip(0, new Placement('e', 0, 'h'));
        Assertions.assertFalse(player.hasShipsToPlace());
    }

    @Test
    void cannot_place_invalid_ship()
    {
        Player player = new Player();

        Assertions.assertFalse(player.placeShip(5, new Placement('a', 0, 'h')));
    }

    @Test
    void get_grid_returns_multidimensional_array_of_cells()
    {
        Player player = new Player();

        Assertions.assertTrue(player.getGrid() instanceof Cell[][]);
    }

    @Test
    void player_has_not_lost_before_placing_ships()
    {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Patrol());
        ships.add(new Submarine());

        Player player = new Player(ships);

        Assertions.assertFalse(player.lost());
    }

    @Test
    void player_has_not_lost_before_any_ships_sunk()
    {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Patrol());
        ships.add(new Submarine());

        Player player = new Player(ships);
        player.placeShip(0, new Placement('a', 0, 'h'));
        player.placeShip(0, new Placement('b', 0, 'h'));

        Assertions.assertFalse(player.lost());
    }

    @Test
    void player_has_not_lost_before_all_ships_sunk()
    {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Patrol());
        ships.add(new Submarine());

        Player player = new Player(ships);
        player.placeShip(0, new Placement('a', 0, 'h'));
        player.placeShip(0, new Placement('b', 0, 'h'));
        player.hit(new Coordinate('a', 0));
        player.hit(new Coordinate('b', 0));

        Assertions.assertFalse(player.lost());
    }

    @Test
    void player_loses_after_all_ships_sunk()
    {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Patrol());
        ships.add(new Submarine());

        Player player = new Player(ships);
        player.placeShip(0, new Placement('a', 0, 'h'));
        player.hit(new Coordinate('a', 0));
        player.hit(new Coordinate('b', 0));
        player.hit(new Coordinate('a', 1));
        player.hit(new Coordinate('b', 1));
        player.hit(new Coordinate('c', 1));

        Assertions.assertTrue(player.lost());
    }

    @Test
    void players_do_not_have_shared_default_ship_objects()
    {
        Player player1 = new Player();
        Player player2 = new Player();

        Assertions.assertNotSame(player1.getUnplacedShips().get(0), player2.getUnplacedShips().get(0));
    }
}