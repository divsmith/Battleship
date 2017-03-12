package edu.weberstate.cs3230;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by parker on 3/4/17.
 */
class ConsoleGameTest extends ConsoleGame{

    @Test
    void play_game_test()
    {
        try {
            File file = new File("test/resources/ConsoleInput.txt");
            ConsoleGame game = new ConsoleGame(new Scanner(file), 10);

            game.play();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void ship_selection_output_is_correct()
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));

        ConsoleGame game = new ConsoleGame();
        Player player = new Player();

        game.printShipOptions(player.getUnplacedShips());

        Assertions.assertEquals("(b) - Battleship\n" +
                "(c) - Carrier\n" +
                "(d) - Destroyer\n" +
                "(p) - Patrol\n" +
                "(s) - Submarine\n\n", output.toString());

        System.setOut(System.out);
    }

    @Test
    void get_ship_selection_regex_returns_correct_regex_string()
    {
        List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Battleship());
        ships.add(new Patrol());
        ships.add(new Destroyer());

        ConsoleGame game = new ConsoleGame();

        Assertions.assertEquals("(b|B|p|P|d|D)", game.getShipSelectionRegex(ships));
    }

    @Test
    void get_ship_selection_correctly_filters_invalid_input()
    {
        ByteArrayInputStream yInputBytes = new ByteArrayInputStream("y".getBytes());

        List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Battleship());
        ships.add(new Patrol());
        ships.add(new Destroyer());

        System.setIn(yInputBytes);

        ConsoleGame game = new ConsoleGame();

        Assertions.assertEquals(-1, game.getShipSelectionIndex(ships, game.getShipSelectionRegex(ships)));
    }

    @Test
    void get_ship_selection_correctly_filters_multicharacter_invalid_input()
    {
        ByteArrayInputStream asdfInputBytes = new ByteArrayInputStream("asdf".getBytes());

        List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Battleship());
        ships.add(new Patrol());
        ships.add(new Destroyer());

        System.setIn(asdfInputBytes);

        ConsoleGame game = new ConsoleGame();

        Assertions.assertEquals(-1, game.getShipSelectionIndex(ships, game.getShipSelectionRegex(ships)));
    }

    @Test
    void get_ship_selection_returns_correct_index_from_valid_input()
    {
        ByteArrayInputStream pInputBytes = new ByteArrayInputStream("p".getBytes());

        List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Battleship());
        ships.add(new Patrol());
        ships.add(new Destroyer());

        System.setIn(pInputBytes);

        ConsoleGame game = new ConsoleGame();

        Assertions.assertEquals(1, game.getShipSelectionIndex(ships, game.getShipSelectionRegex(ships)));
    }

    @Test
    void get_ship_selection_returns_correct_index_from_valid_uppercase_input()
    {
        ByteArrayInputStream pInputBytes = new ByteArrayInputStream("D".getBytes());

        List<Ship> ships = new ArrayList<Ship>();
        ships.add(new Battleship());
        ships.add(new Patrol());
        ships.add(new Destroyer());

        System.setIn(pInputBytes);

        ConsoleGame game = new ConsoleGame();

        Assertions.assertEquals(2, game.getShipSelectionIndex(ships, game.getShipSelectionRegex(ships)));

        System.setIn(System.in);
    }

    @Test
    void coordinate_regex_is_correctly_generated()
    {
        ConsoleGame game = new ConsoleGame();

        Assertions.assertEquals("([a-j][0-9])", game.getCoordinateRegex());
    }

    @Test
    void get_coordinate_returns_null_when_invalid_input_is_given()
    {
        ByteArrayInputStream badInput = new ByteArrayInputStream("a".getBytes());
        System.setIn(badInput);
        ConsoleGame game = new ConsoleGame();

        Assertions.assertNull(game.getUserCoordinateSelection(game.getCoordinateRegex()));

        System.setIn(System.in);
    }

    @Test
    void get_coordinate_returns_null_when_swapped_input_is_given()
    {
        ByteArrayInputStream badInput = new ByteArrayInputStream("2b".getBytes());
        System.setIn(badInput);
        ConsoleGame game = new ConsoleGame();

        Assertions.assertNull(game.getUserCoordinateSelection(game.getCoordinateRegex()));

        System.setIn(System.in);
    }

    @Test
    void get_coordinate_returns_null_when_long_input_is_given()
    {
        ByteArrayInputStream badInput = new ByteArrayInputStream("a0234bdie".getBytes());
        System.setIn(badInput);
        ConsoleGame game = new ConsoleGame();

        Assertions.assertNull(game.getUserCoordinateSelection(game.getCoordinateRegex()));

        System.setIn(System.in);
    }

    @Test
    void get_coordinate_returns_coordinate_when_valid_input_is_given()
    {
        ByteArrayInputStream goodInput = new ByteArrayInputStream("a0".getBytes());
        System.setIn(goodInput);
        ConsoleGame game = new ConsoleGame();

        Coordinate coord = game.getUserCoordinateSelection(game.getCoordinateRegex());

        Assertions.assertTrue(coord instanceof Coordinate);
        System.setIn(System.in);
    }

    @Test
    void get_coordinate_returns_correct_coordinate_when_valid_input_is_given()
    {
        ByteArrayInputStream goodInput = new ByteArrayInputStream("a0".getBytes());
        System.setIn(goodInput);
        ConsoleGame game = new ConsoleGame();

        Coordinate coord = game.getUserCoordinateSelection(game.getCoordinateRegex());

        Assertions.assertEquals(0, coord.getRow());
        Assertions.assertEquals(0, coord.getCol());
        System.setIn(System.in);
    }

    @Test
    void get_orientation_returns_null_when_invalid_input_is_given()
    {
        ByteArrayInputStream badInput = new ByteArrayInputStream("a".getBytes());
        System.setIn(badInput);
        ConsoleGame game = new ConsoleGame();

        Assertions.assertNull(game.getOrientation());
        System.setIn(System.in);
    }

    @Test
    void get_orientation_returns_null_when_long_invalid_input_is_given()
    {
        ByteArrayInputStream badInput = new ByteArrayInputStream("abd23ds".getBytes());
        System.setIn(badInput);
        ConsoleGame game = new ConsoleGame();

        Assertions.assertNull(game.getOrientation());
        System.setIn(System.in);
    }

    @Test
    void get_orientation_returns_character_when_v_input_is_given()
    {
        ByteArrayInputStream goodInput = new ByteArrayInputStream("v".getBytes());
        System.setIn(goodInput);
        ConsoleGame game = new ConsoleGame();

        Assertions.assertEquals(new Character('v'), game.getOrientation());
        System.setIn(System.in);
    }

    @Test
    void get_orientation_returns_character_when_h_input_is_given()
    {
        ByteArrayInputStream goodInput = new ByteArrayInputStream("h".getBytes());
        System.setIn(goodInput);
        ConsoleGame game = new ConsoleGame();

        Assertions.assertEquals(new Character('h'), game.getOrientation());
        System.setIn(System.in);
    }
}