package gui.project2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Image image = null;
        try {
            image = new Image(new FileInputStream("data/pr2.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = 3;
        int m = 4;
        double width = image.getWidth();
        double height = image.getHeight();
        int partW = (int)width/m;
        int partH = (int)height/n;

        PixelReader reader = image.getPixelReader();

        ArrayList<ImageView> parts = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                parts.add(new ImageView(new WritableImage(reader, j*partW, i*partH, partW, partH)));
            }
        }

        //imageView.setFitHeight(768);
        //imageView.setFitWidth(1024);
        //imageView.setPreserveRatio(false);

        Button exit = new Button("Exit");


        GridPane root = new GridPane();

        root.add(exit, 0, 0);

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                root.add(parts.get(j*m+i), i, j + 1, 1, 1);
            }
        }

        root.setGridLinesVisible(true);

        Scene scene = new Scene(root);

        primaryStage.sizeToScene();

        primaryStage.setTitle("Game");

        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
