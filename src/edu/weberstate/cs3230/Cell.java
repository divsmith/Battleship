package edu.weberstate.cs3230;

import edu.weberstate.cs3230.Ship.ShipState;

/**
 * Created by parker on 2/6/17.
 */
public class Cell {
    private boolean hasShip;
    private Ship ship;
    private CellStatus status;

    public enum CellStatus
    {
        hit,
        miss,
        unmarked
    }

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
        this.ship = null;
        this.status = CellStatus.unmarked;
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
        HitResult result = HitResult.alreadyMarked;

        if (status == CellStatus.unmarked)
        {
            if (this.hasShip())
            {
                status = CellStatus.hit;

                ShipState state = this.ship.hit();

                switch(state)
                {
                    case floating:
                        result = HitResult.hit;
                        break;

                    case sunk:
                        result = HitResult.sunk;
                        break;
                }
            }
            else
            {
                status = CellStatus.miss;

                result = HitResult.miss;
            }
        }

        return result;
    }

    public CellStatus getStatus()
    {
        return status;
    }
}
