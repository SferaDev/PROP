package presentation.view.components;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import presentation.utils.ColorUtils;

public class ColorPeg extends JFXButton {
    private int mColorId;

    ColorPeg() {
        this(-1, "TRANSPARENT");
    }

    ColorPeg(int colorId) {
        this(colorId + 1, ColorUtils.getColor(colorId + 1));
    }

    private ColorPeg(int colorId, String color) {
        getStylesheets().add(getClass().getResource("/resources/css/Board.css").toExternalForm());
        getStyleClass().add("color-peg");

        setColor(colorId, color);

        GridPane.setMargin(this, new Insets(5, 5, 5, 5));

        setMinSize(35, 35);
    }

    /**
     * Gets the color id of a peg.
     *
     * @return the color id
     */
    public int getColorId() {
        return mColorId;
    }

    /**
     * Sets the color id of a peg.
     *
     * @param colorId the new color id
     * @param color   the color that will be set on background
     */
    public void setColor(int colorId, String color) {
        setStyle("-fx-font-weight: bold; -fx-background-color: " + color);
        mColorId = colorId;
        setText(String.valueOf(colorId));
        setTextFill(Color.web(ColorUtils.getContrast(colorId)));
    }
}
