package presentation.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.utils.LocaleUtils;

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
        textField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                synchronized (this) {
                    notify();
                }
            }
        });
        setMargin(textField, new Insets(0, 30, 0, 30));
        getChildren().add(textField);

        RaisedButton button = new RaisedButton(LocaleUtils.getInstance().getString("SEND"));
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

    public void requestFocus() {
        textField.requestFocus();
    }
}
