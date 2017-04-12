package edu.weberstate.cs3230.engine;

/**
 * Created by parker on 2/6/17.
 */
public class Cell<T extends IShip> {
    private boolean hasShip;
    private T ship;
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

    public Cell(Cell cell)
    {
        this.status = cell.status;
    }

    public boolean hasShip()
    {
        return this.hasShip;
    }

    public void setShip(T ship)
    {
        this.ship = ship;
        this.hasShip = true;
    }

    public T getShip()
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

                Ship.ShipState state = this.ship.hit();

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
