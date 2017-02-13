package edu.weberstate.cs3230;

/**
 * Created by parker on 2/12/17.
 */
public class Submarine extends Ship {
    private static String NAME = "Submarine";
    private static int MAXHITS = 3;

    protected Submarine() {
        super(NAME, MAXHITS);
    }
}
