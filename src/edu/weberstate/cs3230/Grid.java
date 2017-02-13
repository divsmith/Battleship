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

    public int getSize()
    {
        return size;
    }

    public boolean placeShip(Ship ship, Coordinate coord, char orientation)
    {
        int row = coord.getRow();
        int col = coord.getCol();
        int length = ship.getMaxHits();


        if (shipCanFit(row, col, length, orientation))
        {
            for (int i = 0; i < ship.getMaxHits(); i++)
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

    public Ship getShip(Coordinate coord)
    {
        return grid[coord.getRow()][coord.getCol()].getShip();
    }
}
