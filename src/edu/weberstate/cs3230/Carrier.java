package edu.weberstate.cs3230;

/**
 * Created by parker on 2/12/17.
 */
public class Carrier extends Ship {
    private static String NAME = "Carrier";
    private static int LENGTH = 5;

    public Carrier() {
        super(NAME, LENGTH);
    }

    public Carrier(Carrier ship)
    {
        super(ship);
    }
}
