package fortune;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PhraseGrid extends VBox {

    private static final int ROW_MAX_LEN = 10;

    private Set<Character> unopened = new TreeSet<>();
    private Set<Character> opened = new TreeSet<>();

    boolean tryGuess() {
        String s;
        char letter;
        while (true) {
            if ((s = JOptionPane.showInputDialog("Enter one new letter")) != null && !s.equals("") && s.length() <= 1 && (letter = s.toUpperCase().charAt(0)) >= 'A' && letter <= 'Z' && !opened.contains(letter))
                break;
        }
        if (unopened.contains(letter)) {
            opened.add(letter);
            unopened.remove(letter);
            openCells(letter);
            return true;
        }
        return false;
    }

    boolean hasWon() {
        return unopened.size() == 0;
    }

    private void openCells(char letter) {
        for (Node line : getChildren()) {
            HBox box = (HBox) line;
            for (Node cell : box.getChildren()) {
                LetterCell c = (LetterCell) cell;
                c.check(letter);
            }
        }
    }

    PhraseGrid(String phrase) {
        phrase = phrase.toUpperCase();
        Pattern p = Pattern.compile("\\S+");
        Matcher m = p.matcher(phrase);
        String line = "";
        String word;
        int currLen = 0;

        while (m.find()) {
            word = m.group();
            if (currLen + word.length() <= ROW_MAX_LEN) {
                line += word + " ";
                currLen = line.length();
            } else {
                line = line.substring(0, line.length() - 1);
                HBox newCellsRow = new HBox();
                for (char ch : line.toCharArray()) {
                    newCellsRow.getChildren().add(new LetterCell(ch));
                    if (ch >= 'A' && ch <= 'Z') unopened.add(ch);
                }
                newCellsRow.setAlignment(Pos.BASELINE_CENTER);
                newCellsRow.setMinWidth(LetterCell.getWid() * ROW_MAX_LEN);
                getChildren().add(newCellsRow);
                line = word + " ";
                currLen = line.length();
            }
        }
        if (!line.equals("")) {
            line = line.substring(0, line.length() - 1);
            HBox newCellsRow = new HBox();
            for (char ch : line.toCharArray()) {
                newCellsRow.getChildren().add(new LetterCell(ch));
                if (ch >= 'A' && ch <= 'Z') unopened.add(ch);
            }
            newCellsRow.setAlignment(Pos.BASELINE_CENTER);
            newCellsRow.setMinWidth(LetterCell.getWid() * ROW_MAX_LEN);
            getChildren().add(newCellsRow);
        }
    }

}
