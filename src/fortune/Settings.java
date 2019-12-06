package fortune;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.*;
import java.util.Set;
import java.util.TreeSet;

class Settings extends BorderPane {

    private static final String FILE_NAME = "data/gui/fortuneStrings";

    private ObservableList<String> all;
    private ObservableList<String> selected;
    private ListView<String> allView;
    private ListView<String> selectedView;

    Settings(Main main) {
        selected = FXCollections.observableArrayList(new TreeSet<>());
        selectedView = new ListView<>(selected);
        readFile();

        Button rightB = new Button(" \u2192 "); // ->
        Button leftB = new Button(" \u2190 "); // <-
        Button addNewB = new Button("Add new");
        Button removeB = new Button("Remove");
        Button newGameB = new Button("New game");
        Button backB = new Button("Back");
        VBox buttons = new VBox();

        EventHandler<ActionEvent> transfer = event -> {
            ListView<String> fromView;
            ObservableList<String> fromList, toList;
            if (event.getSource().equals(rightB)) {
                fromView = allView;
                fromList = all;
                toList = selected;
            } else if (event.getSource().equals(leftB)) {
                fromView = selectedView;
                fromList = selected;
                toList = all;
            } else return;
            String s = fromView.getSelectionModel().getSelectedItem();
            if (s != null) {
                fromView.getSelectionModel().clearSelection();
                fromList.remove(s);
                toList.add(s);
            }
        };
        rightB.setOnAction(transfer);
        leftB.setOnAction(transfer);
        EventHandler<ActionEvent> addNew = event -> {
            String s = JOptionPane.showInputDialog("Enter new phrase");
            if (s != null) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, true), true)) {
                    pw.println(s);
                    all.add(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        addNewB.setOnAction(addNew);
        EventHandler<ActionEvent> remove = event -> {
            String s = allView.getFocusModel().getFocusedItem();
            if (s != null) {
                int check = JOptionPane.showConfirmDialog(null, "Delete \"" + s + "\"?", "Delete phrase", JOptionPane.OK_CANCEL_OPTION);
                if (check == 0) {
                    removeLine(s);
                }
            }
        };
        removeB.setOnAction(remove);
        EventHandler<ActionEvent> newGame = event -> {
            if (selected.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Select at least one phrase");
            } else {
                main.startNewGame(selected.get((int) (Math.random() * selected.size())));
                try {
                    buttons.getChildren().add(backB);
                } catch (IllegalArgumentException ignored) {
                }
            }
        };
        newGameB.setOnAction(newGame);
        EventHandler<ActionEvent> backToGame = event -> main.resumeGame();
        backB.setOnAction(backToGame);

        buttons.getChildren().addAll(rightB, leftB, addNewB, removeB, newGameB);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(10));

        setLeft(allView);
        setCenter(buttons);
        setRight(selectedView);
    }

    private void removeLine(String s) {
        all.remove(s);
        Set<String> allTmp = new TreeSet<>();
        allTmp.addAll(all);
        allTmp.addAll(selected);
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String i : allTmp) {
                pw.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        Set<String> set = new TreeSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String s;
            while ((s = br.readLine()) != null) {
                set.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        all = FXCollections.observableArrayList(set);
        allView = new ListView<>(all);
    }

}
