package presentation.visual.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import presentation.visual.utils.ColorManager;

public class ColorPeg extends JFXButton {
    private String mColor;

    public ColorPeg(int color) {
        this(ColorManager.getColor(color));
    }

    public ColorPeg(String color) {
        getStylesheets().add(getClass().getResource("/resources/css/Board.css").toExternalForm());
        getStyleClass().add("color-peg");

        setColor(color);

        GridPane.setMargin(this, new Insets(5, 5, 5, 5));

        setMinSize(35, 35);
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        setStyle("-fx-background-color: " + color);
        mColor = color;
    }
}
