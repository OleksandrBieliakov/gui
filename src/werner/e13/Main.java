package werner.e13;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Destination dest;
    private Discount disc;
    private Label output = new Label();

    private void calculate() {
        try {
            output.setText("Destination " + dest.getPlace() + ", discount \"" + disc.getTitle() + "\": you pay " + dest.getPrice() * disc.getPercent());
        } catch (NullPointerException e) {
            output.setText("Select destination and discount type");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        output.setPadding(new Insets(15));

        ComboBox<Destination> destinations = new ComboBox<>();
        destinations.getItems().addAll(
                new Destination("Madrid", 1000),
                new Destination("Praga", 500),
                new Destination("London", 1500)
        );

        ComboBox<Discount> discounts = new ComboBox<>();
        discounts.getItems().addAll(
                new Discount("Full price", 1),
                new Discount("Senior", 0.6),
                new Discount("Student", 0.5)
        );


        destinations.getSelectionModel().selectedItemProperty().addListener(e -> {
            dest = destinations.getSelectionModel().getSelectedItem();
            calculate();
        });


        discounts.getSelectionModel().selectedItemProperty().addListener(e -> {
            disc = discounts.getSelectionModel().getSelectedItem();
            calculate();
        });

        discounts.getSelectionModel().selectFirst();
        destinations.getSelectionModel().selectFirst();


        HBox boxes = new HBox();
        boxes.setAlignment(Pos.BASELINE_CENTER);
        boxes.setPrefHeight(100);
        boxes.setPadding(new Insets(0, 20, 20, 20));
        boxes.setSpacing(20);
        boxes.getChildren().addAll(
          new Label("Destination"),
          destinations,
          new Label("Discount"),
          discounts
        );

        BorderPane root = new BorderPane();
        BorderPane.setAlignment(output, Pos.CENTER);
        root.setTop(output);
        root.setCenter(boxes);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
