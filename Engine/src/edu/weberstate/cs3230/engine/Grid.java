package edu.weberstate.cs3230.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parker on 2/13/17.
 */
public class Grid<T extends IShip> implements IGrid<T> {

    private int size;
    private Cell<T>[][] grid;
    private List<T> shipList = new ArrayList<T>();
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
        Cell[][] cells = new Cell[size][size];

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                cells[i][j] = new Cell(grid[i][j]);
            }
        }

        for (int x = 0; x < shipList.size(); x++)
        {
            T ship = (T) shipList.get(x).copy();
            Placement placement = placementList.get(x);
            placeShip(ship, placement, cells, false);
        }

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

    public boolean placeShip(T ship, Placement placement, Cell[][] grid, boolean addToLists)
    {
        int row = placement.getRow();
        int col = placement.getCol();
        char orientation = placement.getOrientation();
        int length = ship.getLength();

        if (shipCanFit(row, col, length, orientation, grid))
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

            if (addToLists)
            {
                shipList.add(ship);
                placementList.add(placement);
            }

            return true;
        }

        return false;
    }

    public boolean placeShip(T ship, Placement placement)
    {
        return this.placeShip(ship, placement, this.grid, true);
    }

    protected boolean shipCanFit(int row, int col, int length, char orientation, Cell[][] grid)
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

    public T getShip(Coordinate coord)
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
