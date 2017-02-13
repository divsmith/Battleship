package edu.weberstate.cs3230;

/**
 * Created by parker on 2/6/17.
 */
abstract public class Ship {
    private String name;
    private int length;
    private int hits;
    private ShipState shipState;

    public enum ShipState {
        floating,
        sunk
    }

    protected Ship(String name, int length)
    {
        this.name = name;
        this.length = length;
        this.hits = 0;
        this.shipState = ShipState.floating;
    }

    public String getName()
    {
        return this.name;
    }

    public int getLength()
    {
        return this.length;
    }

    public int getHits()
    {
        return this.hits;
    }

    public ShipState hit()
    {
        hits++;

        if (hits == length)
        {
            this.shipState = ShipState.sunk;
        }

        return this.shipState;
    }
}
