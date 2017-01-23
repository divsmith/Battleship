package edu.weberstate.cs3230;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = "";
        Tile[][] grid = new Tile[10][10];
        char[] rows = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                grid[i][j] = new Tile();
            }
        }

        do {
            printGrid(grid);
            System.out.println("Enter a position as \"rowcolumn\" or enter \'q\' to quit");
            input = scanner.nextLine();

            if (input.length() != 2 && !input.equals("q"))
            {
                System.out.println("Please enter a row immediately followed by a column");
            }
            else if (!input.equals("q"))
            {
                if (!(new String(rows).contains(String.valueOf(input.charAt(0)))))
                {
                    System.out.println("Please enter a valid row");
                }
                else if (Character.getNumericValue(input.charAt(1)) > 9 || Character.getNumericValue(input.charAt(1)) < 0)
                {
                    System.out.println("Please enter a valid column");
                }
                else if (grid[new String(rows).indexOf((input.charAt(0)))][Character.getNumericValue(input.charAt(1))].marked)
                {
                    System.out.println("That tile is already marked");
                }
                else
                {
                    grid[new String(rows).indexOf((input.charAt(0)))][Character.getNumericValue(input.charAt(1))].marked = true;
                }
            }

        } while (!input.equals("q"));

    }

    public static void printGrid(Tile[][] grid)
    {
        char[] rows = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        System.out.print("  0 1 2 3 4 5 6 7 8 9\n");
        int count = 0;

        for (char row : rows)
        {
            System.out.print(row + " ");

            for (int i = 0; i < 10; i++)
            {
                if (grid[count][i].marked)
                {
                    System.out.print("X ");
                }
                else
                {
                    System.out.print("_ ");
                }
            }
            System.out.println();
            count++;
        }

    }
}
