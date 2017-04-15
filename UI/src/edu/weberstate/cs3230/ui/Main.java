package edu.weberstate.cs3230.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
        GridPane left = new GridPane();
        GridPane right = new GridPane();

        List<GridPane> grids = new ArrayList<GridPane>();
        grids.add(left);
        grids.add(right);

        for (GridPane grid : grids)
        {
            for (int i = 0; i < 10; i++)
            {
                grid.addRow(i);
                grid.addColumn(i);

                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPercentWidth(10);

                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setPercentHeight(10);

                grid.getColumnConstraints().add(columnConstraints);
                grid.getRowConstraints().add(rowConstraints);
            }

            for (int col = 0; col < 10; col++)
            {
                for (int row = 0; row < 10; row++)
                {
                    StackPane cellPane = new StackPane();
                    cellPane.setBorder(new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID, new CornerRadii(2.0), BorderWidths.DEFAULT)));

                    grid.add(cellPane, col, row);
                }
            }

            grid.setPrefWidth(250);
        }


        pane.setLeft(grids.get(0));
        pane.setRight(grids.get(1));

        Scene scene = new Scene(pane, 800, 300);

        window.setScene(scene);
        window.show();

        StackPane update = (StackPane) grids.get(0).getChildren().get(2);

        update.getChildren().add(new Label("1"));
    }
}
