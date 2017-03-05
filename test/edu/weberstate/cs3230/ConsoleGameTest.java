package edu.weberstate.cs3230;

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

//    @Test
//    void play()
//    {
//        try {
//            File file = new File("test/resources/ConsoleInput.txt");
//            ConsoleGame game = new ConsoleGame(new Scanner(file));
//
//            game.play();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

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
                "(s) - Submarine\n", output.toString());

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

        System.setIn(yInputBytes);

        ConsoleGame game = new ConsoleGame();
        Player player = new Player();

        Assertions.assertEquals(-1, game.getShipSelectionIndex(player.getUnplacedShips()));

    }
}