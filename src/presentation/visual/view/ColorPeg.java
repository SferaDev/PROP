package presentation.visual.view;

import com.jfoenix.controls.JFXButton;

public class ColorPeg extends JFXButton {
    private String mColor;

    public ColorPeg(String color) {
        getStylesheets().add(getClass().getResource("/resources/css/Board.css").toExternalForm());
        getStyleClass().add("color-peg");

        setColor(color);
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        setStyle("-fx-background-color: " + color);
        mColor = color;
    }
}
