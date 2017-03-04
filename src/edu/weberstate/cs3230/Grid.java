package edu.weberstate.cs3230;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parker on 2/13/17.
 */
public class Grid {

    private int size;
    private Cell[][] grid;
    private List<Ship> shipList = new ArrayList<Ship>();
    private List<Placement> placementList = new ArrayList<Placement>();

    public Grid()
    {
        this(10);
    }

    public Grid(int size)
    {
        this.size = size;

        this.grid = initializeCells(this.size);
    }

    public Cell[][] getGrid()
    {
        Cell[][] cells = this.initializeCells(this.size);

        return cells;
    }

    private Cell[][] initializeCells(int size)
    {
        Cell[][] cells = new Cell[size][size];

        for(int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                cells[i][j] = new Cell();
            }
        }

        return cells;
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

    public boolean placeShip(Ship ship, Placement placement, Cell[][] grid)
    {
        int row = placement.getRow();
        int col = placement.getCol();
        char orientation = placement.getOrientation();
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

            shipList.add(ship);
            placementList.add(placement);

            return true;
        }

        return false;
    }

    public boolean placeShip(Ship ship, Placement placement)
    {
        return this.placeShip(ship, placement, this.grid);
    }

    protected boolean shipCanFit(int row, int col, int length, char orientation)
    {
        for (int i = 0; i < length; i++)
        {
            if (!coordinatesAreValid(row, col))
            {
                return false;
            }

            if (grid[row][col].hasShip())
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
