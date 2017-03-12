package edu.weberstate.cs3230;

import java.util.ArrayList;
import java.util.Arrays;
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

    private static Ship[] getDefaultShips()
    {
        Ship[] ships =  {new Battleship(), new Carrier(), new Destroyer(), new Patrol(), new Submarine()};
        return ships;
    }

    public Player()
    {
        this(DEFAULT_NAME);
    }

    public Player(String name)
    {
        this(name, new ArrayList<Ship>(Arrays.asList(getDefaultShips())));
    }

    public Player(String name, List<Ship> unplacedShips)
    {
        setName(name);

        grid = new Grid();

        this.unplacedShips = unplacedShips;
    }

    public Player(List<Ship> unplacedShips)
    {
        this(DEFAULT_NAME, unplacedShips);
    }

    public boolean lost()
    {
        if (ships.size() == 0)
        {
            return false;
        }

        for (Ship ship : ships)
        {
            if (ship.getShipState() == Ship.ShipState.floating)
            {
                return false;
            }
        }

        return true;
    }

    public Cell.HitResult hit(Coordinate coord)
    {
        return grid.hit(coord);
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
