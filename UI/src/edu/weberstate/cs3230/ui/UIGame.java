package edu.weberstate.cs3230.ui;

import edu.weberstate.cs3230.engine.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UIGame extends Application {

    Stage window;
    TextField input;
    TextArea output;
    Button enterButton;
    private static String path = null;

    Player player1;
    Player player2;

    GridPane leftPlayerGrid;
    GridPane rightPlayerGrid;

    EventHandler<ActionEvent> clear = event -> {
        input.clear();
    };

    public static void main(String[] args) {
        if (args.length > 0)
        {
            path = args[0];
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Battleship");
        window.setResizable(false);

        BorderPane pane = new BorderPane();
        leftPlayerGrid = new GridPane();
        rightPlayerGrid = new GridPane();

        List<GridPane> grids = new ArrayList<GridPane>();
        grids.add(leftPlayerGrid);
        grids.add(rightPlayerGrid);


        for (GridPane grid : grids)
        {
            for (int i = 0; i < 11; i++)
            {
                grid.addRow(i);
                grid.addColumn(i);

                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPercentWidth(12);

                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setPercentHeight(10);

                grid.getColumnConstraints().add(columnConstraints);
                grid.getRowConstraints().add(rowConstraints);
            }

            for (int col = 0; col < 11; col++)
            {
                for (int row = 0; row < 11; row++)
                {
                    StackPane cellPane = new StackPane();
                    cellPane.setBorder(new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID, new CornerRadii(2.0), BorderWidths.DEFAULT)));

                    if (col == 0 && row > 0)
                    {
                        cellPane.getChildren().add(new Label(Character.toString((char) (row - 1 + 'A'))));
                    }

                    if (row == 0 && col > 0)
                    {
                        cellPane.getChildren().add(new Label(Integer.toString(col - 1)));
                    }

                    grid.add(cellPane, col, row);
                }
            }

            grid.setPrefWidth(400);
            grid.setPrefHeight(250);
        }

        pane.setLeft(grids.get(0));
        pane.setRight(grids.get(1));

        VBox inputArea = new VBox();

        output = new TextArea();

        if (path == null)
        {
            // Set the text area to be inaccessible
            // unless a test is running.
            output.setEditable(false);
            output.setMouseTransparent(true);
            output.setFocusTraversable(false);
        }

        inputArea.setSpacing(10);
        inputArea.setAlignment(Pos.CENTER);

        input = new TextField();
        input.setPromptText("Input");

        enterButton = new Button("Enter");
        enterButton.prefWidth(50);

        setHandlers(clear);

        inputArea.getChildren().addAll(output, input, enterButton);

        pane.setCenter(inputArea);

        Scene scene = new Scene(pane, 1200, 500);

        window.setScene(scene);
        window.show();

        play();
    }

    private void setHandlers(EventHandler<ActionEvent> handler)
    {
        enterButton.setOnAction(handler);
        input.setOnAction(handler);
    }

    public void play()
    {
        player1 = new Player();
        player2 = new Player();

        getPlayerNames();

        if (path != null)
        {
            startFileInput();
        }
    }

    private void startFileInput()
    {
        try {
            FileReader fileReader = new FileReader(new File(path));
            BufferedReader buffer = new BufferedReader(fileReader);
            String line;

            while ((line = buffer.readLine()) != null)
            {
                input.setText(line);
                enterButton.fire();
            }

            fileReader.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void drawShips(Player player)
    {
        GridPane displayGrid = player.equals(player1) ? leftPlayerGrid : rightPlayerGrid;

        Cell[][] grid = player.getGrid();

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (grid[i][j].hasShip())
                {
                    StackPane pane = (StackPane) displayGrid.getChildren().get(((j + 1) * 11) + 1 + i);
                    String label = Character.toString(Character.toUpperCase(grid[i][j].getShip().getName().charAt(0)));
                    pane.getChildren().clear();
                    pane.getChildren().add(new Label(label));
                }
            }
        }
    }

    protected void drawGridForOpponent(Player player)
    {
        GridPane displayGrid = player.equals(player1) ? leftPlayerGrid : rightPlayerGrid;

        Cell[][] grid = player.getGrid();

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (grid[i][j].getStatus() == Cell.CellStatus.hit)
                {
                    StackPane pane = (StackPane) displayGrid.getChildren().get(((j + 1) * 11) + 1 + i);
                    pane.getChildren().clear();
                    pane.getChildren().add(new Label("X"));
                }
                else if (grid[i][j].getStatus() == Cell.CellStatus.miss)
                {
                    StackPane pane = (StackPane) displayGrid.getChildren().get(((j + 1) * 11) + 1 + i);
                    pane.getChildren().clear();
                    pane.getChildren().add(new Label("O"));
                }
            }
        }
    }

    protected void clearGrid(Player player)
    {
        GridPane displayGrid = player.equals(player1) ? leftPlayerGrid : rightPlayerGrid;

        Cell[][] grid = player.getGrid();

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                StackPane pane = (StackPane) displayGrid.getChildren().get(((j + 1) * 11) + 1 + i);
                pane.getChildren().clear();
            }
        }
    }

    protected void playerSelectOrientation(Player player, Coordinate coord, int index)
    {
        write("Enter the ship orientation (either 'v' or 'h'): ");

        setHandlers(event -> {
            Character orientation = getOrientation(input.getText());

            if (orientation == null)
            {
                write("Invalid orientation. Please enter 'v' or 'h': ");
            }
            else
            {
                if (!player.placeShip(index, new Placement(coord, orientation)))
                {
                    write(player.getUnplacedShips().get(index).getName() + " cannot be placed " +
                            (orientation == 'v' ? "vertically" : "horizontally") + " at " + coord.getRowChar() + coord.getCol());

                    Logger.getLogger().warning("Invalid ship placement: " + player.getUnplacedShips().get(index).getName() + " at " + coord.getRowChar() + coord.getCol() + " " + orientation);
                    playerSelectShip(player);
                }
                else
                {
                    drawShips(player);

                    if (player.hasShipsToPlace())
                    {
                        playerSelectShip(player);
                    }
                    else if (player.equals(player1))
                    {
                        clearGrid(player1);
                        playerSelectShip(player2);
                    }
                    else
                    {
                        clearGrid(player2);
                        playerFire(player1);
                    }
                }

                clear();
            }

            clear();
        });
    }

    protected void playerFire(Player firingPlayer)
    {
        write(firingPlayer.getName() + ", choose a coordinate to hit (i.e. 'a0'): ");
        setHandlers(event -> {
            String coordinateSelectionRegex = getCoordinateRegex();
            Coordinate coord = null;
            coord = getUserCoordinateSelection(input.getText(), coordinateSelectionRegex);

            if (coord == null)
            {
                write("\nInvalid coordinate");
                write(firingPlayer.getName() + ", choose a coordinate to hit (i.e. 'a0'): ");
            }
            else
            {
                Player receivingPlayer = firingPlayer.equals(player1) ? player2 : player1;
                Cell.HitResult result = receivingPlayer.hit(coord);

                Boolean valid = false;

                drawGridForOpponent(receivingPlayer);

                if (result == Cell.HitResult.hit)
                {
                    write("\nHIT!");
                    valid = true;
                    Logger.getLogger().info(firingPlayer.getName() + " fired at " + coord.getRowChar() + coord.getCol() + " and HIT");
                }
                else if (result == Cell.HitResult.miss)
                {
                    write("\nMiss");
                    valid = true;
                    Logger.getLogger().info(firingPlayer.getName() + " fired at " + coord.getRowChar() + coord.getCol() + " and MISSED");
                }
                else if (result == Cell.HitResult.sunk)
                {
                    write("\nSUNK!");
                    valid = true;
                    Logger.getLogger().info(firingPlayer.getName() + " fired at " + coord.getRowChar() + coord.getCol() + " and SUNK");
                }
                else if (result == Cell.HitResult.alreadyMarked)
                {
                    write("\nThat coordinate has already been hit. Please pick a different one.");
                    Logger.getLogger().info(firingPlayer.getName() + " fired at " + coord.getRowChar() + coord.getCol() + " which was already fired at");
                }

                if (valid && !receivingPlayer.lost())
                {
                    clear();
                    playerFire(receivingPlayer);
                }
                else if (valid && receivingPlayer.lost())
                {
                    write(firingPlayer.getName() + " won the game!");
                    setHandlers(null);
                }
            }

            clear();
        });
    }

    protected void playerSelectCoordinate(Player player, int index)
    {
        List<Ship> ships = player.getUnplacedShips();
        write("Enter a coordinate for your " + ships.get(index).getName() + " (i.e. 'a0'): ");

        setHandlers(event -> {
            List<Ship> ships2 = player.getUnplacedShips();

            String coordinateSelectionRegex = getCoordinateRegex();

            Coordinate coord = null;
            coord = getUserCoordinateSelection(input.getText(), coordinateSelectionRegex);

            if (coord == null)
            {
                write("\nInvalid coordinate");
                write("Enter a coordinate for your " + ships2.get(index).getName() + " (i.e. 'a0'): ");
            }
            else
            {
                playerSelectOrientation(player, coord, index);
            }

            clear();
        });
    }

    protected void playerSelectShip(Player player)
    {
        write("\n" + player.getName() + ", select a ship to place.\n");

        List<Ship> initialShips = player.getUnplacedShips();
        printShipOptions(initialShips);

        setHandlers(event -> {
            List<Ship> ships = player.getUnplacedShips();

            // Select the ship to place.
            String shipSelectionRegex = getShipSelectionRegex(ships);
            int index = -1;
            index = getShipSelectionIndex(input.getText(), ships, shipSelectionRegex);

            if (index < 0)
            {
                write("\nInvalid ship. Please choose a valid selection below.\n");

                printShipOptions(ships);
            }
            else
            {
                playerSelectCoordinate(player, index);
            }

            clear();
        });
    }

    protected Character getOrientation(String string)
    {
        if (string.matches("(h|v)"))
        {
            return new Character(string.charAt(0));
        }

        return null;
    }

    protected String getCoordinateRegex()
    {
        // This should be dynamically generated based on grid size;
        return "([a-j][0-9])";
    }

    protected int getShipSelectionIndex(String string, List<Ship> ships, String regex)
    {
        if (string.matches(regex))
        {
            int index = 0;
            Character input = Character.toLowerCase(string.charAt(0));

            for (Ship ship : ships)
            {
                if (Character.toLowerCase(ship.getName().charAt(0)) == input)
                {
                    return index;
                }

                index++;
            }
        }

        return -1;
    }

    protected Coordinate getUserCoordinateSelection(String string, String regex)
    {
        if (string.matches(regex))
        {
            String input = string;

            return new Coordinate(input.charAt(0), Character.getNumericValue(input.charAt(1)));
        }

        return null;
    }

    protected void printShipOptions(List<Ship> ships)
    {
        for (Ship ship : ships)
        {
            String name = ship.getName();

            Character shipLabel = Character.toLowerCase(name.charAt(0));

            write("(" + shipLabel + ") - " + name);
        }
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
        write("Player 1 name: ");
        setHandlers(event -> {
            player1.setName(input.getText());
            clear();

            write("Player 2 name: ");
            setHandlers(e -> {
                player2.setName(input.getText());
                clear();
                playerSelectShip(player1);
            });
        });
    }

    protected void write()
    {
        write("");
    }

    protected void write(String message)
    {
        output.appendText(message + "\n");
    }

    protected void clear()
    {
        input.clear();
    }
}
