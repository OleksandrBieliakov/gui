package gui.project2v2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Scene setMenuScene(Stage primaryStage) {
        Button btnPL = new Button("PLAY");
        btnPL.setOnAction(event -> {
                    primaryStage.setTitle("Puzzle");
                    primaryStage.setScene(setPuzzleScene(primaryStage));
                    primaryStage.show();
                }
        );

        Button btnBT = new Button("BEST TIME");
        btnBT.setOnAction(e -> System.out.println("Opening best time board"));

        Button btnEX = new Button("EXIT");
        btnEX.setOnAction(e -> System.exit(0));

        StackPane root1 = new StackPane();

        StackPane.setAlignment(btnPL, Pos.TOP_CENTER);
        StackPane.setAlignment(btnBT, Pos.CENTER);
        StackPane.setAlignment(btnEX, Pos.BOTTOM_CENTER);

        root1.getChildren().addAll(btnPL, btnBT, btnEX);

        return new Scene(root1, 1024, 768);
    }

    @SuppressWarnings("Duplicates")
    private static Scene setPuzzleScene(Stage primaryStage) {
        Image image = null;
        try {
            image = new Image(new FileInputStream("data/pr2.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = 2;
        int m = 3;
        int size = m * n;
        double width = image.getWidth();
        double height = image.getHeight();
        int partW = (int) width / m;
        int partH = (int) height / n;

        PixelReader reader = image.getPixelReader();

        ArrayList<ImageView> parts = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                parts.add(new ImageView(new WritableImage(reader, j * partW, i * partH, partW, partH)));
            }
        }

        ArrayList<ImageView> partsMix = new ArrayList<>(parts);

        Random r = new Random();
        for (int i = 1, a, b; i > 0; --i) {
            a = r.nextInt(size);
            b = r.nextInt(size);
            while (b == a) {
                b = r.nextInt(size);
            }
            Collections.swap(partsMix, a, b);
        }

        Button exit = new Button("EXIT TO MENU");
        exit.setOnAction(event -> {
                    primaryStage.setTitle("Menu");
                    primaryStage.setScene(setMenuScene(primaryStage));
                    primaryStage.show();
                }
        );

        GridPane root = new GridPane();

        root.add(exit, 0, 0);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                root.add(partsMix.get(j * m + i), i, j + 1, 1, 1);
            }
        }

        AtomicInteger missingN = new AtomicInteger(r.nextInt(n) + 1);
        AtomicInteger missingM = new AtomicInteger(r.nextInt(m));
        AtomicInteger missing = new AtomicInteger(m * (missingN.get() - 1) + missingM.get());
        root.getChildren().remove(partsMix.get(missing.get()));

        EventHandler<MouseEvent> handler = e -> {
            ImageView tmp = (ImageView) e.getSource();
            int tmpM = GridPane.getColumnIndex(tmp);
            int tmpN = GridPane.getRowIndex(tmp);

            if ((tmpM == missingM.get() && tmpN == missingN.get() + 1) ||
                    (tmpM == missingM.get() && tmpN == missingN.get() - 1) ||
                    (tmpM == missingM.get() + 1 && tmpN == missingN.get()) ||
                    (tmpM == missingM.get() - 1 && tmpN == missingN.get())) {
                int tmpI = (tmpN - 1) * m + tmpM;
                Collections.swap(partsMix, missing.get(), tmpI);
                missing.set(tmpI);
                root.getChildren().remove(tmp);
                root.add(tmp, missingM.get(), missingN.get(), 1, 1);
                missingM.set(tmpM);
                missingN.set(tmpN);
                if (partsMix.equals(parts)) {
                    System.out.println("YOU WON!");
                }
            }
        };


        for (int i = 0; i < size; ++i) {
            if (i != missing.get())
                partsMix.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        }

        root.setGridLinesVisible(true);

        return new Scene(root);
    }


    @Override
    public void start(Stage primaryStage) {

        primaryStage.sizeToScene();

        primaryStage.setTitle("Menu");

        primaryStage.setScene(setMenuScene(primaryStage));

        primaryStage.show();


    }
}