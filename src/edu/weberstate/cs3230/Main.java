package edu.weberstate.cs3230;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IGame game = new ConsoleGame();

        game.play();
    }
}
