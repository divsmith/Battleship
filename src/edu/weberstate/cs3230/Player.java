package edu.weberstate.cs3230;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parker on 3/4/17.
 */
public class Player {
    private static String DEFAULT_NAME = "default";

    private Grid grid;
    protected List<Ship> ships = new ArrayList<Ship>();
    protected List<Ship> unplacedShips = new ArrayList<Ship>();
    private String name;

    public Player()
    {
        this(DEFAULT_NAME);
    }

    public Player(String name)
    {
        setName(name);

        grid = new Grid();

        unplacedShips.add(new Battleship());
        unplacedShips.add(new Carrier());
        unplacedShips.add(new Destroyer());
        unplacedShips.add(new Patrol());
        unplacedShips.add(new Submarine());
    }

    public boolean hasShipsToPlace()
    {
        return unplacedShips.size() > 0;
    }

    public List<Ship> getUnplacedShips()
    {
        return unplacedShips;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean placeShip(int index, Placement placement)
    {
        if (index > unplacedShips.size() - 1)
        {
            return false;
        }

        boolean placed = this.grid.placeShip(unplacedShips.get(index), placement);

        if (placed)
        {
            ships.add(unplacedShips.get(index));
            unplacedShips.remove(index);
        }

        return placed;
    }

    public List<Ship> getShips()
    {
        return this.ships;
    }

    public Cell[][] getGrid()
    {
        return grid.getGrid();
    }
}
