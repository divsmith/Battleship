package edu.weberstate.cs3230;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by parker on 2/6/17.
 */
public class ConsoleGame {
    private static int DEFAULT_SIZE = 10;

    Scanner scanner;
    List<Player> players = new ArrayList<Player>();
    int gridSize;

    public ConsoleGame()
    {
        this(new Scanner(System.in), DEFAULT_SIZE);
    }

    public ConsoleGame(Scanner scanner, int gridSize)
    {
        this.scanner = scanner;
        this.gridSize = gridSize;

        // Add grid size to players.
        players.add(new Player());
        players.add(new Player());
    }

    public void play()
    {
        // Get names
        getPlayerNames();

        // Place ships
        placeShips();

        Player firingPlayer = players.get(0);
        Player receivingPlayer = players.get(1);
        Player tempPlayer;

        // Check whether the firing player lost
        // because the firing and receiving player
        // will have swapped before this comparison occurs.
        while (!firingPlayer.lost())
        {
            Coordinate coord = getCoordinate(firingPlayer.getName() +
                    ", choose a coordinate to hit (i.e. 'a0'): ", receivingPlayer.getGrid(), true);

            Cell.HitResult result = receivingPlayer.hit(coord);

            System.out.println();
            printOpponentGrid(receivingPlayer.getGrid());

            if (result == Cell.HitResult.hit)
            {
                System.out.println("\nHIT!");
            }
            else
            {
                System.out.println("\nMiss");
            }

            System.out.println("----------------------");

            tempPlayer = firingPlayer;
            firingPlayer = receivingPlayer;
            receivingPlayer = tempPlayer;
        }
    }

    private Coordinate getCoordinate(String prompt, Cell[][] grid, boolean opponent)
    {
        Coordinate coord = null;
        String coordinateSelectionRegex = getCoordinateRegex();

        // Need to find a cleaner way of doing this.
        if (opponent)
        {
            printOpponentGrid(grid);
        }
        else
        {
            printPlayerGrid(grid);
        }

        System.out.print("\n" + prompt);
        do {
            coord = getUserCoordinateSelection(coordinateSelectionRegex);

            if (coord == null)
            {
                System.out.print("Invalid coordinate. Please enter coordinate a-j0-9: ");
            }
        } while (coord == null);

        return coord;
    }

    protected void placeShips()
    {
        for (Player player : players)
        {
            while(player.hasShipsToPlace())
            {
                List<Ship> ships = player.getUnplacedShips();

                // Select the ship to place.
                String shipSelectionRegex = getShipSelectionRegex(ships);
                int index = -1;

                System.out.println("\n" + player.getName() + ", select a ship to place.\n");
                do {
                    printShipOptions(ships);
                    index = getShipSelectionIndex(ships, shipSelectionRegex);

                    if (index < 0)
                    {
                        System.out.println("\nInvalid ship. Please choose a valid selection below.\n");
                    }
                } while ( index < 0);


                // Get a coordinate for the ship.
                Coordinate coord = getCoordinate("Enter a coordinate for your " +
                        ships.get(index).getName() +
                        " (i.e. 'a0'): ", player.getGrid(), false);
//                Coordinate coord = null;
//                String coordinateSelectionRegex = getCoordinateRegex();
//
//                printPlayerGrid(player.getGrid());
//
//                System.out.print("\nEnter a coordinate for your " + ships.get(index).getName() + " (i.e. 'a0'): ");
//                do {
//                    coord = getUserCoordinateSelection(coordinateSelectionRegex);
//
//                    if (coord == null)
//                    {
//                        System.out.print("Invalid coordinate. Please enter coordinate a-j0-9: ");
//                    }
//                } while (coord == null);

                // Get an orientation for the ship
                Character orientation = null;

                System.out.print("Enter the ship orientation (either 'v' or 'h'): ");
                do {
                    orientation = getOrientation();

                    if (orientation == null)
                    {
                        System.out.print("Invalid orientation. Please enter 'v' or 'h': ");
                    }
                } while (orientation == null);

                // Place ship
                if (!player.placeShip(index, new Placement(coord, orientation)))
                {
                    System.out.println(ships.get(index).getName() + " cannot be placed " +
                            (orientation == 'v' ? "vertically" : "horizontally") + " at " + coord.getRowChar() + coord.getCol());
                }
            }

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

    private void printOpponentGrid(Cell[][] grid)
    {
        char row = 'a';

        System.out.println();

        // Print header
        System.out.print("  ");
        for (int i = 0; i < gridSize; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println();

        // Print out rows
        for (int i = 0; i < gridSize; i++)
        {
            System.out.print(row++ + " ");

            for (int j = 0; j < gridSize; j++)
            {
                if (grid[i][j].getStatus() == Cell.CellStatus.hit)
                {
                    System.out.print("X ");
                }
                else if (grid[i][j].getStatus() == Cell.CellStatus.miss)
                {
                    System.out.print("O ");
                }
                else
                {
                    System.out.print("* ");
                }

            }

            System.out.println();
        }
    }

    private void printPlayerGrid(Cell[][] grid) {
        char row = 'a';

        System.out.println();

        // Print header
        System.out.print("  ");
        for (int i = 0; i < gridSize; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println();

        // Print out rows
        for (int i = 0; i < gridSize; i++)
        {
            System.out.print(row++ + " ");

            for (int j = 0; j < gridSize; j++)
            {
                if (grid[i][j].hasShip())
                {
                    System.out.print(Character.toUpperCase(grid[i][j].getShip().getName().charAt(0)) + " ");
                }
                else
                {
                    System.out.print("* ");
                }
            }

            System.out.println();
        }
    }

    protected String getCoordinateRegex()
    {
        // This should be dynamically generated based on grid size;
        return "([a-j][0-9])";
    }

    protected void printShipOptions(List<Ship> ships)
    {
        for (Ship ship : ships)
        {
            String name = ship.getName();

            Character shipLabel = Character.toLowerCase(name.charAt(0));

            System.out.println("(" + shipLabel + ") - " + name);
        }

        System.out.println();
    }

    protected Coordinate getUserCoordinateSelection(String regex)
    {
        if (scanner.hasNext(regex))
        {
            String input = scanner.next(regex);

            return new Coordinate(input.charAt(0), Character.getNumericValue(input.charAt(1)));
        }
        else
        {
            scanner.next();
        }

        return null;
    }

    protected int getShipSelectionIndex(List<Ship> ships, String regex)
    {
        if (scanner.hasNext(regex))
        {
            int index = 0;
            Character input = Character.toLowerCase(scanner.next(regex).charAt(0));

            for (Ship ship : ships)
            {
                if (Character.toLowerCase(ship.getName().charAt(0)) == input)
                {
                    return index;
                }

                index++;
            }
        }
        else
        {
            scanner.next();
        }

        return -1;
    }

    protected Character getOrientation()
    {
        if (scanner.hasNext("(h|v)"))
        {
            return new Character(scanner.next("(h|v)").charAt(0));
        }
        else
        {
            scanner.next();
        }

        return null;
    }

    protected String getShipSelectionRegex(List<Ship> ships)
    {
        String regex = "(";

        for (Ship ship : ships)
        {
            String name = ship.getName();

            Character shipLabel = Character.toLowerCase(name.charAt(0));

            // Add the lowercase and uppercase first letter of the ship name to the regex string
            regex = regex.concat(shipLabel + "|" + Character.toUpperCase(shipLabel) + "|");
        }

        // Remove last | from regex string.
        regex = regex.substring(0, regex.length() - 1);
        regex = regex.concat(")");

        return regex;
    }

    private void getPlayerNames()
    {
        System.out.print("Player 1 name: ");
        players.get(0).setName(scanner.next());

        System.out.print("Player 2 name: ");
        players.get(1).setName(scanner.next());
    }
}
