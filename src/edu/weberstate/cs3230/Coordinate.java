package edu.weberstate.cs3230;

/**
 * Created by parker on 2/13/17.
 */
public class Coordinate {
    int row;
    int col;

    public Coordinate(char row, int col)
    {
        row = Character.toLowerCase(row);
        this.row = row - 'a';
        this.col = col;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }
}
