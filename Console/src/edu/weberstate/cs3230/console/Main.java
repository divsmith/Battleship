package edu.weberstate.cs3230.console;

import edu.weberstate.cs3230.console.ConsoleGame;
import edu.weberstate.cs3230.engine.IGame;

public class Main {

    public static void main(String[] args) {

        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Sorry, something unexpected happened. The stacktrace has been logged.");
        });

        IGame game = new ConsoleGame();

        game.play();
    }
}
