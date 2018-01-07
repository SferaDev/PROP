package presentation.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;

public class RaisedButton extends JFXButton {
    /**
     * Creates a JFX raised button.
     * @param text the text of the button.
     */
    public RaisedButton(String text) {
        setButtonType(ButtonType.RAISED);
        setContentDisplay(ContentDisplay.CENTER);
        setMnemonicParsing(false);
        setStyle("-fx-font-size:14px; -fx-background-color:WHITE;");

        HBox.setMargin(this, new Insets(10, 10, 10, 10));

        setText(text);
    }
}