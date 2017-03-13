package edu.weberstate.cs3230;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Sorry, something unexpected happened. The stacktrace has been logged.");
        });

        IGame game = new ConsoleGame();

        game.play();
    }
}
