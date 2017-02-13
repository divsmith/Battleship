package edu.weberstate.cs3230;

/**
 * Created by parker on 2/13/17.
 */
public class Grid {

    private int size;
    private Cell[][] grid;

    public Grid()
    {
        this(10);
    }

    public Grid(int size)
    {
        grid = new Cell[size][size];
        this.size = size;

        for(int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                grid[i][j] = new Cell();
            }
        }
    }

    public Cell.HitResult hit(Coordinate coord)
    {
        int row = coord.getRow();
        int col = coord.getCol();

        if (!coordinatesAreValid(row, col))
        {
            return null;
        }

        return grid[row][col].hit();
    }

    public int getSize()
    {
        return size;
    }

    public boolean placeShip(Ship ship, Coordinate coord, char orientation)
    {
        int row = coord.getRow();
        int col = coord.getCol();
        int length = ship.getLength();

        if (shipCanFit(row, col, length, orientation))
        {
            for (int i = 0; i < ship.getLength(); i++)
            {
                grid[row][col].setShip(ship);

                switch(orientation)
                {
                    case 'v':
                        row++;
                        break;

                    case 'h':
                        col++;
                        break;

                    default:
                        throw new IllegalArgumentException("Orientation must be \'h\' or \'v\'");
                }
            }

            return true;
        }

        return false;
    }

    protected boolean shipCanFit(int row, int col, int length, char orientation)
    {
        for (int i = 0; i < length; i++)
        {
            if (!coordinatesAreValid(row, col))
            {
                return false;
            }

            if (grid[row][col].getShip() != null)
            {
                return false;
            }

            switch(orientation)
            {
                case 'v':
                    row++;
                    break;

                case 'h':
                    col++;
                    break;

                default:
                    throw new IllegalArgumentException("Orientation must be \'h\' or \'v\'");
            }
        }

        return true;
    }

    private boolean coordinatesAreValid(int row, int col)
    {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1)
        {
            return false;
        }

        return true;
    }

    public Ship getShip(Coordinate coord)
    {
        int row = coord.getRow();
        int col = coord.getCol();

        if (!coordinatesAreValid(row, col))
        {
            return null;
        }

        return grid[row][col].getShip();
    }
}
