package fortune;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private Scene gameScene;
    private Scene settingsScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        settingsScene = new Scene(new Settings(this));
        stage.setTitle("Wheel of fortune - Settings");
        stage.setScene(settingsScene);
        stage.show();
    }

    void startNewGame(String s) {
        gameScene = new Scene(new Game(this, s));
        stage.setTitle("Wheel of fortune");
        stage.setScene(gameScene);
    }

    void resumeGame() {
        stage.setTitle("Wheel of fortune");
        stage.setScene(gameScene);
    }

    void openSettings() {
        stage.setTitle("Wheel of fortune - Settings");
        stage.setScene(settingsScene);
    }

}
