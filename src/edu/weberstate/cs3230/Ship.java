package edu.weberstate.cs3230;

/**
 * Created by parker on 2/6/17.
 */
abstract public class Ship {
    private String name;
    private int maxHits;
    private int hits;
    private ShipState shipState;

    public enum ShipState {
        floating,
        sunk
    }

    protected Ship(String name, int maxHits)
    {
        this.name = name;
        this.maxHits = maxHits;
        this.hits = 0;
        this.shipState = ShipState.floating;
    }

    public String getName()
    {
        return this.name;
    }

    public int getMaxHits()
    {
        return this.maxHits;
    }

    public int getHits()
    {
        return this.hits;
    }

    public ShipState hit()
    {
        hits++;

        if (hits == maxHits)
        {
            this.shipState = ShipState.sunk;
        }

        return this.shipState;
    }
}
