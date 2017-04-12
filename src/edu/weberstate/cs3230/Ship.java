package edu.weberstate.cs3230;

/**
 * Created by parker on 2/6/17.
 */
abstract public class Ship implements IShip{
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

    protected Ship(Ship ship)
    {
        this.name = ship.getName();
        this.length = ship.getLength();
        this.hits = ship.hits;
        this.shipState = ship.shipState;
    }

    abstract public Ship copy();

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

    public ShipState getShipState()
    {
        return this.shipState;
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
