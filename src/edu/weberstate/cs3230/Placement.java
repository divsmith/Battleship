package edu.weberstate.cs3230;

/**
 * Created by parker on 2/28/17.
 */
public class Placement extends Coordinate {
    private char orientation;

    public Placement(char row, int col, char orientation) {
        super(row, col);

        if (orientation != 'v' && orientation != 'h')
        {
            throw new IllegalArgumentException("Orientation must be \'h\' or \'v\'");
        }
        this.orientation = orientation;
    }

    public char getOrientation()
    {
        return this.orientation;
    }
}
