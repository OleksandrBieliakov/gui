package werner.e12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        HBox root = new HBox();
        Lover romeo = new Lover("Romeo");
        Lover juliet = new Lover("Juliet");
        romeo.setPartner(juliet);
        juliet.setPartner(romeo);
        root.getChildren().addAll(romeo, juliet);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Lovers' chat");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
