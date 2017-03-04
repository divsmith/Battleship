package edu.weberstate.cs3230;

/**
 * Created by parker on 2/12/17.
 */
public class Battleship extends Ship {
    private static String NAME = "Battleship";
    private static int LENGTH = 4;

    public Battleship() {
        super(NAME, LENGTH);
    }

    public Battleship(Battleship ship)
    {
        super(ship);
    }

    public Battleship copy()
    {
        return new Battleship(this);
    }
}
