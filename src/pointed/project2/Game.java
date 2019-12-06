package pointed.project2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        btnBT.setOnAction(event -> {
                    primaryStage.setTitle("Records");
                    primaryStage.setScene(setRecordsScene(primaryStage));
                    primaryStage.show();
                }
        );

        Button btnEX = new Button("EXIT");
        btnEX.setOnAction(e -> System.exit(0));

        btnPL.setFont(Font.font("System", FontWeight.BOLD, 50));
        btnBT.setFont(Font.font("System", FontWeight.BOLD, 50));
        btnEX.setFont(Font.font("System", FontWeight.BOLD, 50));

        VBox root1 = new VBox(20);
        root1.setAlignment(Pos.CENTER);

        root1.getChildren().addAll(btnPL, btnBT, btnEX);

        return new Scene(root1, 1024, 768);
    }

    private static Scene setRecordsScene(Stage primaryStage) {

        VBox root1 = new VBox(20);

        Button exit = new Button("EXIT TO MENU");
        exit.setOnAction(event -> {
                    primaryStage.setTitle("Menu");
                    primaryStage.setScene(setMenuScene(primaryStage));
                    primaryStage.show();
                }
        );

        exit.setFont(Font.font("System", FontWeight.BOLD, 30));

        root1.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();

        int next = 1;
        File file = new File("data/pr2_board");
        boolean newRec = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            int record;
            String name;
            while ((s = br.readLine()) != null) {
                name = s;
                record = Integer.parseInt(br.readLine());
                Label names = new Label(next + ". " + name);
                names.setFont(Font.font("System", FontWeight.BOLD, 30));
                grid.add(names, 0, next);
                int min = (int) (record) / 60000;
                int sec = (int) ((record - min * 60000) / 1000);
                Label records = new Label(min + ":" + sec);
                records.setFont(Font.font("System", FontWeight.BOLD, 30));
                grid.add(records, 1, next++);
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        root1.getChildren().addAll(exit, grid);

        return new Scene(root1, 1024, 768);
    }

    private static Scene setPuzzleScene(Stage primaryStage) {
        Image image = null;
        try {
            image = new Image(new FileInputStream("data/pr2.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = 4;
        int m = 4;
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

        AtomicInteger missingN = new AtomicInteger(r.nextInt(n));
        AtomicInteger missingM = new AtomicInteger(r.nextInt(m));
        AtomicInteger missing = new AtomicInteger(m * (missingN.get()) + missingM.get());

        for (int i = 1; i > 0; --i) {

            ArrayList<Integer> arr = new ArrayList<>();

            if ((missingN.get() + 1) < n) {
                arr.add(m * (missingN.get() + 1) + missingM.get());
            }
            if ((missingN.get() - 1) >= 0) {
                arr.add(m * (missingN.get() - 1) + missingM.get());
            }
            if ((missingM.get() + 1) < m) {
                arr.add(m * missingN.get() + missingM.get() + 1);
            }
            if ((missingM.get() - 1) >= 0) {
                arr.add(m * missingN.get() + missingM.get() - 1);
            }

            int with = r.nextInt(arr.size());
            Collections.swap(partsMix, missing.get(), arr.get(with));

            missingN.set(arr.get(with) / m);
            missingM.set(arr.get(with) % m);
            missing.set(arr.get(with));

        }

        missingN.getAndIncrement();

        Button exit = new Button("EXIT TO MENU");
        exit.setOnAction(event -> {
                    primaryStage.setTitle("Menu");
                    primaryStage.setScene(setMenuScene(primaryStage));
                    primaryStage.show();
                }
        );

        exit.setFont(Font.font("System", FontWeight.BOLD, 20));

        GridPane root = new GridPane();

        root.add(exit, 0, 0);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                root.add(partsMix.get(j * m + i), i, j + 1, 1, 1);
            }
        }

        root.getChildren().remove(partsMix.get(missing.get()));

        Label timer = new Label("00:00");
        GridPane.setHalignment(timer, HPos.CENTER);
        timer.setFont(Font.font("System", FontWeight.BOLD, 30));
        root.add(timer, m - 1, 0);

        long start = System.currentTimeMillis();

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> {
                    long cur = System.currentTimeMillis();
                    long time = cur - start;
                    int min = (int) (time) / 60000;
                    int sec = (int) ((time - min * 60000) / 1000);
                    timer.setText(min + ":" + sec);
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

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
                    timeline.stop();

                    Label completed = new Label("COMPLETED!");
                    GridPane.setHalignment(completed, HPos.CENTER);
                    root.add(completed, m - 2, 0);

                    checkBoard(System.currentTimeMillis() - start);
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

    private static void checkBoard(long time) {
        File file = new File("data/pr2_board");
        ArrayList<GameRecord> board = new ArrayList<>();
        boolean newRec = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            int record;
            String name;
            while ((s = br.readLine()) != null) {
                name = s;
                record = Integer.parseInt(br.readLine());
                if (time > record) newRec = true;
                board.add(new GameRecord(name, record));
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!newRec && board.size() == 10) return;

        String name = JOptionPane.showInputDialog("NEW RECORD! Enter your name:");

        board.add(new GameRecord(name, (int) time));

        board.sort(Comparator.naturalOrder());

        if (board.size() > 10)
            board.remove(board.size() - 1);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            StringBuilder sb = new StringBuilder();
            for (GameRecord gr : board) {
                sb.append(gr.toString());
            }
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.sizeToScene();

        primaryStage.setTitle("Menu");

        primaryStage.setScene(setMenuScene(primaryStage));

        primaryStage.show();

    }
}