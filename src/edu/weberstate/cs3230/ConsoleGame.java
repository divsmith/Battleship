package edu.weberstate.cs3230;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by parker on 2/6/17.
 */
public class ConsoleGame {

    Scanner scanner;
    List<Player> players = new ArrayList<Player>();

    public ConsoleGame()
    {
        this(new Scanner(System.in));
    }

    public ConsoleGame(Scanner scanner)
    {
        this.scanner = scanner;
        players.add(new Player());
        players.add(new Player());
    }

    public void play()
    {
        // Get names
        getPlayerNames();

        // Place ships
        for (Player player : players)
        {
            placeShips(player);
        }
    }

    private void placeShips(Player player) throws Exception {
        Battleship battleship = new Battleship();
        Carrier carrier = new Carrier();
        Destroyer destroyer = new Destroyer();
        Patrol patrol = new Patrol();
        Submarine submarine = new Submarine();

        List<Ship> ships = new ArrayList<Ship>();
        ships.add(battleship);
        ships.add(carrier);
        ships.add(destroyer);
        ships.add(patrol);
        ships.add(submarine);

        while (ships.size() > 0)
        {
            boolean validShip = false;
            Character selection = '\0';
            String regex = "";

            // Get a valid selection for a remaining ship to place.
            while (!validShip)
            {
                System.out.println("Player " + player.getPlayerNumber() + ": Select ship to place.\n");
                regex = "";
                regex = regex.concat("(");

                for (Ship ship : ships)
                {
                    String name = ship.getName();

                    Character shipLabel = Character.toLowerCase(name.charAt(0));

                    // Add the lowercase and uppercase first letter of the ship name to the regex string
                    regex = regex.concat(shipLabel + "|" + Character.toUpperCase(shipLabel) + "|");

                    // Add the lowercase first letter of the ship name to the matching
                    System.out.println("(" + shipLabel + ") - " + name);
                }

                // Remove last | from regex string.
                regex = regex.substring(0, regex.length() - 1);
                regex = regex.concat(")");

                if (scanner.hasNext(regex))
                {
                    // Grab the first character from the input and lowercase it.
                    selection = Character.toLowerCase(scanner.next(regex).charAt(0));
                    validShip = true;
                }
                else
                {
                    scanner.next();
                    System.out.println("Invalid input. Please choose a valid option below.");
                }
            }

            // Find the matching ship and index from the list.
            int index = 0;
            boolean found = false;
            for (Ship ship : ships)
            {
                String name = ship.getName();
                Character shipLabel = Character.toLowerCase(name.charAt(0));

                if (shipLabel == selection)
                {
                    found = true;
                    break;
                }

                index++;
            }

            if (!found)
            {
                throw new Exception("Ship (" + selection + ") was selected but not found in the ship list.");
            }

            // Have the user place the ship
            valid = false;
            while (!valid)
            {
                System.out.print("Enter coordinate of ship as rowcolumn, i.e. \"a0\": ");
                regex = "([a-j][0-9])";
                String input = "";

                if (scanner.hasNext(regex))
                {
                    // Grab the first character from the input and lowercase it.
                    input = scanner.next(regex);


                    valid = true;
                }
                else
                {
                    scanner.next();
                    System.out.println("Invalid input. Please choose a valid option below.");
                }
            }
        }
    }

    private void getPlayerNames()
    {
        System.out.print("Player 1 name: ");
        players.get(0).setName(scanner.next());

        System.out.print("Player 2 name: ");
        players.get(1).setName(scanner.next());
    }
}
