package edu.weberstate.cs3230.ui;

import edu.weberstate.cs3230.engine.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
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

import java.util.ArrayList;
import java.util.List;

public class UIGame extends Application {

    Stage window;
    TextField input;
    TextArea output;
    Button enterButton;

    List<Player> players = new ArrayList<Player>();

    EventHandler<ActionEvent> clear = event -> {
        input.clear();
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Battleship");
        window.setResizable(false);

        BorderPane pane = new BorderPane();
        GridPane leftPlayerGrid = new GridPane();
        GridPane rightPlayerGrid = new GridPane();

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
                        cellPane.getChildren().add(new Label(Integer.toString(row - 1)));
                    }

                    if (row == 0 && col > 0)
                    {
                        cellPane.getChildren().add(new Label(Character.toString((char) (col - 1 + 'A'))));
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
        output.setEditable(false);
        output.setMouseTransparent(true);
        output.setFocusTraversable(false);

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

//        StackPane update = (StackPane) grids.get(0).getChildren().get(15);
//
//        update.getChildren().add(new Label("1"));

//        input.setText("testing\n\n\n");
//        enterButton.fire();

        play();
    }

    private void setHandlers(EventHandler<ActionEvent> handler)
    {
        enterButton.setOnAction(handler);
        input.setOnAction(handler);
    }

    public void play()
    {
        players.add(new Player());
        players.add(new Player());

        getPlayerNames();
    }

    protected void placeShips()
    {
        Player player = players.get(0);
        write("\n" + player.getName() + ", select a ship to place.\n");

        setHandlers(event -> {
            List<Ship> ships = player.getUnplacedShips();
            printShipOptions(ships);

            // Select the ship to place.
            String shipSelectionRegex = getShipSelectionRegex(ships);
            int index = -1;
            index = getShipSelectionIndex(input.getText(), ships, shipSelectionRegex);

        });

//        for (Player player : players)
//        {
//            while(player.hasShipsToPlace())
//            {
//                List<Ship> ships = player.getUnplacedShips();
//
//                // Select the ship to place.
//                String shipSelectionRegex = getShipSelectionRegex(ships);
//                int index = -1;
//
//                write("\n" + player.getName() + ", select a ship to place.\n");
//                do {
//                    printShipOptions(ships);
//                    index = getShipSelectionIndex(input.getText(), ships, shipSelectionRegex);
//
//                    if (index < 0)
//                    {
//                        write("\nInvalid ship. Please choose a valid selection below.\n");
//                    }
//                } while ( index < 0);
//
//
//                // Get a coordinate for the ship.
//                Coordinate coord = getCoordinate("Enter a coordinate for your " +
//                        ships.get(index).getName() +
//                        " (i.e. 'a0'): ", player.getGrid(), false);
//
//                // Get an orientation for the ship
//                Character orientation = null;
//
//                System.out.print("Enter the ship orientation (either 'v' or 'h'): ");
//                do {
//                    orientation = getOrientation();
//
//                    if (orientation == null)
//                    {
//                        System.out.print("Invalid orientation. Please enter 'v' or 'h': ");
//                    }
//                } while (orientation == null);
//
//                // Place ship
//                if (!player.placeShip(index, new Placement(coord, orientation)))
//                {
//                    write(ships.get(index).getName() + " cannot be placed " +
//                            (orientation == 'v' ? "vertically" : "horizontally") + " at " + coord.getRowChar() + coord.getCol());
//
//                    Logger.getLogger().warning("Invalid ship placement: " + ships.get(index).getName() + " at " + coord.getRowChar() + coord.getCol() + " " + orientation);
//                }
//            }
//
//            write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
//        }
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

    protected void printShipOptions(List<Ship> ships)
    {
        for (Ship ship : ships)
        {
            String name = ship.getName();

            Character shipLabel = Character.toLowerCase(name.charAt(0));

            write("(" + shipLabel + ") - " + name);
        }

        write();
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
            players.get(0).setName(input.getText());
            clear();

            write("Player 2 name: ");
            setHandlers(e -> {
                players.get(1).setName(input.getText());
                clear();
                placeShips();
            });
        });
    }

    private void write()
    {
        write("");
    }

    private void write(String message)
    {
        output.appendText(message + " ");
    }

    private void clear()
    {
        output.appendText(input.getText() + "\n");
        input.clear();
    }
}