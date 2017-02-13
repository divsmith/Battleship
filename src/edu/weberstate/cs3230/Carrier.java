package edu.weberstate.cs3230;

/**
 * Created by parker on 2/12/17.
 */
public class Carrier extends Ship {
    private static String NAME = "Carrier";
    private static int MAXHITS = 5;

    protected Carrier() {
        super(NAME, MAXHITS);
    }
}
