package presentation.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import presentation.utils.ColorManager;

public class ColorPeg extends JFXButton {
    private int mColorId;
    private String mColor;

    ColorPeg() {
        this(-1, "TRANSPARENT");
    }

    ColorPeg(int colorId) {
        this(colorId + 1, ColorManager.getColor(colorId + 1));
    }

    private ColorPeg(int colorId, String color) {
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
        setStyle("-fx-font-weight: bold; -fx-background-color: " + color);
        mColor = color;
        mColorId = colorId;
        setText(String.valueOf(colorId));
        setTextFill(Color.web(ColorManager.getContrast(colorId)));
    }
}
