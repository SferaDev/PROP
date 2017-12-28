package presentation.visual.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import presentation.visual.utils.ColorManager;

public class ColorPeg extends JFXButton {
    private int mColorId;
    private String mColor;

    public ColorPeg(int colorId) {
        this(colorId, ColorManager.getColor(colorId));
    }

    public ColorPeg(int colorId, String color) {
        getStylesheets().add(getClass().getResource("/resources/css/Board.css").toExternalForm());
        getStyleClass().add("color-peg");

        setColor(colorId, color);

        GridPane.setMargin(this, new Insets(5, 5, 5, 5));

        setMinSize(35, 35);
    }

    public String getColor() {
        return mColor;
    }

    public int getColorId() {
        return mColorId;
    }

    public void setColor(int colorId, String color) {
        setStyle("-fx-background-color: " + color);
        mColor = color;
        mColorId = colorId;
    }
}
