package edu.weberstate.cs3230;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;
        Tile[][] grid = new Tile[10][10];

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                grid[i][j] = new Tile();
            }
        }

        printGrid(grid);
    }

    public static void printGrid(Tile[][] grid)
    {
        char[] rows = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        System.out.print("  1 2 3 4 5 6 7 8 9 10\n");
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
