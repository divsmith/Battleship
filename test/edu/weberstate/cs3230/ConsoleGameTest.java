package edu.weberstate.cs3230;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
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

        Player player = new Player();

        ConsoleGame game = new ConsoleGame();

        game.printPlayerShips(player);

        Assertions.assertEquals("(b) - Battleship\n" +
                "(c) - Carrier\n" +
                "(d) - Destroyer\n" +
                "(p) - Patrol\n" +
                "(s) - Submarine\n", output.toString());

        System.setOut(null);
    }
}