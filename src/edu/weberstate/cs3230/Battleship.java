package edu.weberstate.cs3230;

/**
 * Created by parker on 2/12/17.
 */
public class Battleship extends Ship {
    private static String NAME = "Battleship";
    private static int MAXHITS = 4;

    protected Battleship() {
        super(NAME, MAXHITS);
    }
}
