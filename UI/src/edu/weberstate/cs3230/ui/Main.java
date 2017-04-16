package edu.weberstate.cs3230.ui;

import edu.weberstate.cs3230.engine.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import java.util.Scanner;

public class Main extends Application {

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

        players.add(new Player());
        players.add(new Player());

        start();
    }

    private void setHandlers(EventHandler<ActionEvent> handler)
    {
        enterButton.setOnAction(handler);
        input.setOnAction(handler);
    }

    private void start()
    {
        getPlayerNames();
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
            });
        });
    }

    private void write(String message)
    {
        output.appendText(message + "\n");
    }

    private void clear()
    {
        input.clear();
    }
}
