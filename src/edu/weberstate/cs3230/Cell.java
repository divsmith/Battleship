package edu.weberstate.cs3230;

import edu.weberstate.cs3230.Ship.ShipState;

/**
 * Created by parker on 2/6/17.
 */
public class Cell {
    private boolean hasShip;
    private boolean isMarked;
    private Ship ship;

    public enum HitResult
    {
        hit,
        sunk,
        miss,
        alreadyMarked
    }

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

    public HitResult hit()
    {
        HitResult result = HitResult.alreadyMarked

        if (!this.isMarked)
        {
            this.isMarked = true;

            if (this.hasShip())
            {
                ShipState state = this.ship.hit();

                switch(state)
                {
                    case floating:
                        result = HitResult.hit;
                        break;

                    case sunk:
                        result = HitResult.sunk;
                }
            }
            else
            {
                result = HitResult.miss;
            }
        }

        return result;
    }
}
