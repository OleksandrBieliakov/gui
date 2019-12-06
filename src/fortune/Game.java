package fortune;

import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.swing.*;

class Game extends BorderPane {

    private SimpleIntegerProperty score;
    private SimpleIntegerProperty lives;
    private PhraseGrid grid;
    private Button playB;

    Game(Main main, String draw) {

        score = new SimpleIntegerProperty(0);
        lives = new SimpleIntegerProperty(3);

        grid = new PhraseGrid(draw);
        Wheel wheel = new Wheel(this);
        HBox buttonsAndStats = new HBox();

        Button settingsB = new Button("Settings");
        playB = new Button("Play");
        Label showLives = new Label();
        Label showScore = new Label();

        EventHandler<ActionEvent> openSettings = event -> main.openSettings();
        settingsB.setOnAction(openSettings);

        EventHandler<ActionEvent> play = event -> {
            wheel.roll();
        };
        playB.setOnAction(play);

        buttonsAndStats.getChildren().addAll(settingsB, showLives, showScore, playB);
        buttonsAndStats.setSpacing(10);
        buttonsAndStats.setAlignment(Pos.CENTER);

        StringBinding scoreBinding = new StringBinding() {
            {
                super.bind(score);
            }

            @Override
            protected String computeValue() {
                return "Score: " + score.get();
            }
        };
        StringBinding livesBinding = new StringBinding() {
            {
                super.bind(lives);
            }

            @Override
            protected String computeValue() {
                return "Opportunities: " + lives.get();
            }
        };

        showLives.textProperty().bind(scoreBinding);
        showScore.textProperty().bind(livesBinding);

        setPadding(new Insets(10));
        setMargin(wheel, new Insets(20));
        setTop(grid);
        setCenter(wheel);
        setBottom(buttonsAndStats);

    }

    void newGuess(int prise) {
        Thread thread = new Thread(() -> {
            if (!grid.tryGuess()) {
                lives.setValue(lives.get() - 1);
                if (lives.get() <= 0) {
                    JOptionPane.showMessageDialog(null, "GAME OVER! YOUR SCORE: " + score.get());
                    playB.setDisable(true);
                }
            } else {
                score.setValue(score.get() + prise);
                if (grid.hasWon()) {
                    JOptionPane.showMessageDialog(null, "YOU WON! YOUR SCORE: " + score.get());
                    playB.setDisable(true);
                }
            }
        });
        Platform.runLater(thread);
    }

}
