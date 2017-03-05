package edu.weberstate.cs3230;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parker on 3/4/17.
 */
public class Player {
    private static String DEFAULT_NAME = "default";
    private static int nextPlayerNumber = 1;

    private Grid grid;
    private List<Ship> ships = new ArrayList<Ship>();
    private String name;
    private int playerNumber;

    public Player()
    {
        this(DEFAULT_NAME);
    }

    public Player(String name)
    {
        setName(name);

        grid = new Grid();
        this.playerNumber = Player.nextPlayerNumber;
        Player.nextPlayerNumber++;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean placeShip(Ship ship, Placement placement)
    {
        boolean placed = this.grid.placeShip(ship, placement);

        if (placed)
        {
            ships.add(ship);
        }

        return placed;
    }

    public List<Ship> getShips()
    {
        return this.ships;
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }
}
