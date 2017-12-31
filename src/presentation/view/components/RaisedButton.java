package presentation.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;

public class RaisedButton extends JFXButton {
    public RaisedButton(String text) {
        setButtonType(ButtonType.RAISED);
        setContentDisplay(ContentDisplay.CENTER);
        setMnemonicParsing(false);
        setStyle("-fx-font-size:14px; -fx-background-color:WHITE;");

        HBox.setMargin(this, new Insets(10, 10, 10, 10));

        setText(text);
    }
}