package edu.weberstate.cs3230;

/**
 * Created by parker on 2/12/17.
 */
public class Patrol extends Ship {
    private static String NAME = "Patrol";
    private static int MAXHITS = 2;

    public Patrol() {
        super(NAME, MAXHITS);
    }
}