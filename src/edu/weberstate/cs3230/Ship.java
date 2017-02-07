package edu.weberstate.cs3230;

/**
 * Created by parker on 2/6/17.
 */
abstract public class Ship {
    private String name;
    private int maxHits;
    private int hits;

    protected Ship(String name, int maxHits)
    {
        this.name = name;
        this.maxHits = maxHits;
        this.hits = 0;
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
}
