package presentation.visual.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ControlInput extends VBox {
    private TextField textField = new TextField();
    private boolean done = false;

    public ControlInput(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(new Font(18));
        textField.setPrefSize(40, 40);
        JFXButton button = new JFXButton("Send");
        button.setOnMouseClicked(event -> {
            done = true;
        });
        getChildren().add(titleLabel);
        getChildren().add(textField);
        getChildren().add(button);
    }

    public String getResult() {
        while (!done) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return textField.getText();
    }
}
