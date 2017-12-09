package presentation.visual.view;

import com.jfoenix.controls.JFXButton;

public class ColorPeg extends JFXButton {
    public ColorPeg(String color) {
        getStylesheets().add(getClass().getResource("/resources/css/Board.css").toExternalForm());
        getStyleClass().add("color-peg");

        setStyle("-fx-background-color: " + color);
    }
}
