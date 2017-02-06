package edu.weberstate.cs3230;

/**
 * Created by parker on 2/6/17.
 */
public class Cell {
    private boolean isMarked;
    private boolean isHit;
    private Ship ship;

    public Cell()
    {
        this.isMarked = false;
        this.isHit = false;
        this.ship = null;
    }

    public boolean hasShip()
    {
        return !(this.ship == null);
    }

    public void setShip(Ship ship)
    {
        this.ship = ship;
    }

    public Ship getShip()
    {
        return this.ship;
    }
}
