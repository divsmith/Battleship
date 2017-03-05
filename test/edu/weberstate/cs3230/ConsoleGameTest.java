package edu.weberstate.cs3230;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by parker on 3/4/17.
 */
class ConsoleGameTest {

    @Test
    void play()
    {
        try {
            File file = new File("test/resources/ConsoleInput.txt");
            ConsoleGame game = new ConsoleGame(new Scanner(file));

            game.play();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}