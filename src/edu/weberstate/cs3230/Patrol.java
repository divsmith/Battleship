package edu.weberstate.cs3230;

/**
 * Created by parker on 2/12/17.
 */
public class Patrol extends Ship implements IShip{
    private static String NAME = "Patrol";
    private static int LENGTH = 2;

    public Patrol() {
        super(NAME, LENGTH);
    }

    public Patrol(Patrol ship)
    {
        super(ship);
    }

    public Patrol copy()
    {
        return new Patrol(this);
    }
}
