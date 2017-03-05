package edu.weberstate.cs3230;

import java.util.Scanner;

/**
 * Created by parker on 2/6/17.
 */
public class ConsoleGame {

    Scanner scanner;

    public ConsoleGame()
    {
        this(new Scanner(System.in));
    }

    public ConsoleGame(Scanner scanner)
    {
        this.scanner = scanner;
    }

    public void play()
    {
        System.out.print("Player 1 Name: ");
        String player1Name = scanner.next();
        System.out.print("Player 2 Name: ");
        String player2Name = scanner.next();

        System.out.println(player1Name);
        System.out.println(player2Name);
    }
}
