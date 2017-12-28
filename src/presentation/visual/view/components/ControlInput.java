package presentation.visual.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ControlInput extends VBox {
    private TextField textField = new TextField();

    public ControlInput(String title) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label titleLabel = new Label(title);
        titleLabel.setFont(new Font(18));
        titleLabel.setTextFill(Color.WHITE);
        getChildren().add(titleLabel);

        textField.setPrefSize(40, 40);
        setMargin(textField, new Insets(0, 30, 0, 30));
        getChildren().add(textField);

        RaisedButton button = new RaisedButton("Send"); // TODO: Strings
        button.setOnMouseClicked(event -> {
            synchronized (this) {
                notify();
            }
        });
        getChildren().add(button);
    }

    public String getResult() {
        return textField.getText();
    }
}
