package fortune;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class LetterCell extends Label {

    private char letter;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 60;

    static int getWid() {
        return WIDTH;
    }

    LetterCell(char letter) {
        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
        setAlignment(Pos.CENTER);
        setFont(new Font(30));

        if (letter >= 'A' && letter <= 'Z') {
            setStyle("-fx-background-color: #e0603d; -fx-background-radius: 5 5 5 5;");
        } else {
            setStyle("-fx-background-color: #32a852; -fx-background-radius: 5 5 5 5;");
            setText(letter + "");
        }
        this.letter = letter;
    }

    void check(char letter) {
        if (this.letter == letter) {
            setStyle("-fx-background-color: #32a852; -fx-background-radius: 5 5 5 5;");
            setText(letter + "");
        }
    }

}
