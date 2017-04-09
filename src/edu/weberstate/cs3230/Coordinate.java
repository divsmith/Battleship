package edu.weberstate.cs3230;

/**
 * Created by parker on 2/13/17.
 */
public class Coordinate {
    private int row;
    private int col;
    private char rowChar;

    public Coordinate(char row, int col)
    {
        row = Character.toLowerCase(row);
        rowChar = row;
        this.row = row - 'a';
        this.col = col;

        Logger.getLogger().info("Coordinate " + row + col + " created.");
    }

    public Coordinate(int row, int col)
    {
        this((char) (row + 'a'), col);
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public char getRowChar()
    {
        return rowChar;
    }
}
