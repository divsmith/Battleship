package edu.weberstate.cs3230;

/**
 * Created by parker on 2/12/17.
 */
public class Destroyer extends Ship {
    private static String NAME = "Destroyer";
    private static int LENGTH = 3;

    public Destroyer() {
        super(NAME, LENGTH);
    }

    public Destroyer(Destroyer ship)
    {
        super(ship);
    }

    public Destroyer copy()
    {
        return new Destroyer(this);
    }
}
