package edu.weberstate.cs3230;

/**
 * Created by parker on 2/6/17.
 */
public class Gameboard {

    private Cell[][] grid;

    public Gameboard(int size)
    {
        grid = new Cell[size][size];
    }
}
