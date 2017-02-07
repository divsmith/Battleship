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

    public HitState hit()
    {
        HitState hitstate = HitState.AlreadyMarked;

        if (!this.isMarked)
        {
            this.isMarked = true;

            if (this.hasShip())
            {
                hitstate = this.ship.hit();
            }
            else
            {
                hitstate = HitState.Miss;
            }
        }

        return hitstate;
    }
}
