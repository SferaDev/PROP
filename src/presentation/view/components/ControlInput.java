package presentation.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import presentation.utils.LocaleUtils;

/**
 * The Control Input
 *
 * @author Alexis Rico Carreto
 */
public class ControlInput extends VBox {
    private final TextField textField = new TextField();

    /**
     * Creates a controlInput with th given parameters.
     *
     * @param title the title of the label in the controlInput.
     */
    public ControlInput(String title) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label titleLabel = new Label(title);
        titleLabel.setWrapText(true);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setFont(new Font(18));
        titleLabel.setTextFill(Color.WHITE);
        getChildren().add(titleLabel);

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

    /**
     * The textFielf getter.
     *
     * @return returns a TextField.
     */
    public String getResult() {
        return textField.getText();
    }

    /**
     * Request focus on the textField
     */
    public void requestFocus() {
        textField.requestFocus();
    }
}
