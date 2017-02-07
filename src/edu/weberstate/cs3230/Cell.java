package edu.weberstate.cs3230;

/**
 * Created by parker on 2/6/17.
 */
public class Cell {
    private boolean hasShip;
    private boolean isMarked;
    private Ship ship;

    public Cell()
    {
        this.hasShip = false;
        this.isMarked = false;
        this.ship = null;
    }

    public boolean hasShip()
    {
        return this.hasShip;
    }

    public void setShip(Ship ship)
    {
        this.ship = ship;
        this.hasShip = true;
    }

    public Ship getShip()
    {
        return this.ship;
    }

    public void hit()
    {
        // Check if cell is already marked

            // set isMarked to true

            // if there is a ship, hit it

        // Return status
    }
}
