package edu.weberstate.cs3230;

/**
 * Created by parker on 2/6/17.
 */
abstract public class Ship {
    private String name;

    public enum Hitstate
    {
        Hit,
        Sunk,
        Invalid
    }

    protected Ship(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
