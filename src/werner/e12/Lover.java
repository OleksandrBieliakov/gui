package werner.e12;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

class Lover extends VBox {

    private static String dialog = "";

    private String name;
    private Lover partner;
    private TextArea history = new TextArea();
    private BorderPane inputPane = new BorderPane();
    private Label nameLabel = new Label();
    private TextField message = new TextField();
    private Button sendB = new Button("Send");
    private EventHandler<ActionEvent> sending;


    Lover(String name) {
        this.name = name;
        history.setPrefRowCount(25);
        history.setPrefColumnCount(30);

        message.setPrefColumnCount(30);

        sending = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog = dialog + name + ": " + message.getText() + "\n";
                message.setText("");
                history.setText(dialog);
                partner.recieveMessage();
            }
        };

        message.setOnKeyPressed(e-> {
            if(e.getCode() == KeyCode.ESCAPE) {
                message.setText("");
            }
        });

        message.setOnAction(sending);
        sendB.setOnAction(sending);

        nameLabel.setText(name + ":");
        nameLabel.setPadding(new Insets(5));

        inputPane.setLeft(nameLabel);
        inputPane.setCenter(message);
        inputPane.setRight(sendB);

        getChildren().addAll(history, inputPane);
    }

    void setPartner(Lover partner) {
        this.partner = partner;
    }

    void recieveMessage() {
        history.setText(dialog);
    }



}
