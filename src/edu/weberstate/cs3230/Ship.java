package edu.weberstate.cs3230;

/**
 * Created by parker on 2/6/17.
 */
abstract public class Ship {
    private String name;
    private int hitCount;
    private int hits;

    protected Ship(String name, int hitCount)
    {
        this.name = name;
        this.hitCount = hitCount;
        this.hits = 0;
    }

    public String getName()
    {
        return this.name;
    }

    public int getHitCount()
    {
        return this.hitCount;
    }

    public int getHits()
    {
        return this.hits;
    }
}
