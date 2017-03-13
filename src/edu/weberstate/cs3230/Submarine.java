package edu.weberstate.cs3230;

/**
 * Created by parker on 2/12/17.
 */
public class Submarine extends Ship implements IShip{
    private static String NAME = "Submarine";
    private static int LENGTH = 3;

    public Submarine() {
        super(NAME, LENGTH);
    }

    public Submarine(Submarine ship)
    {
        super(ship);
    }

    public Submarine copy()
    {
        return new Submarine(this);
    }
}
