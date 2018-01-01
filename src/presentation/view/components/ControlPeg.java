package presentation.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class ControlPeg extends JFXButton {
    ControlPeg(Type type) {
        getStylesheets().add(getClass().getResource("/resources/css/Board.css").toExternalForm());
        getStyleClass().add("control-peg");
        getStyleClass().add("control-peg-" + type);

        GridPane.setMargin(this, new Insets(5, 5, 5, 5));
    }

    public enum Type {
        black,
        white
    }
}
