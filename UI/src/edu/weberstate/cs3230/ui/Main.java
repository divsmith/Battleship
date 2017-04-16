package edu.weberstate.cs3230.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {

    Stage window;

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

        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(10, 10, 10, 10));
        inputGrid.setVgap(8);
        inputGrid.setHgap(10);

        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);

        TextField nameInput = new TextField("Test");
        GridPane.setConstraints(nameInput, 1, 0);


        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);

        TextField input = new TextField();
        input.setPromptText("Input");
        GridPane.setConstraints(input, 1, 1);

        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1, 2);

        loginButton.setOnAction(event -> {
            System.out.println(nameInput.getText());
            System.out.println(input.getText());
        });

        inputGrid.getChildren().addAll(nameLabel, nameInput, passLabel, input, loginButton);

        pane.setCenter(inputGrid);

        Scene scene = new Scene(pane, 1200, 500);

        window.setScene(scene);
        window.show();

        StackPane update = (StackPane) grids.get(0).getChildren().get(15);

        update.getChildren().add(new Label("1"));
    }
}
