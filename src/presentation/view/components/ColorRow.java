package presentation.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.utils.ColorUtils;

/**
 * The Color Row
 *
 * @author Elena Alonso Rodriguez
 */
public class ColorRow extends VBox {
    private final ColorPeg[] mPegs;

    /**
     * Creates a empty color row.
     *
     * @param pegs is the number of pegs of the color row.
     */
    public ColorRow(int pegs) {
        this(emptyColorRow(pegs));
    }

    /**
     * Creates a color row.
     *
     * @param row the colors in string format.
     */
    public ColorRow(String row) {
        this(stringToArray(row));
    }

    /**
     * Creates a color row.
     *
     * @param pegs the pegs that will form the row
     */
    public ColorRow(ColorPeg... pegs) {
        mPegs = pegs;
        setAlignment(Pos.CENTER);
        setSpacing(10);

        GridPane.setMargin(this, new Insets(10, 10, 10, 10));

        HBox firstLayer = createRowBox();
        HBox secondLayer = createRowBox();
        HBox thirdLayer = createRowBox();

        for (int i = 0; i < mPegs.length; i++) {
            if (mPegs.length < 4) firstLayer.getChildren().add(mPegs[i]);
            else if (mPegs.length < 7) {
                if (i % 2 == 0) firstLayer.getChildren().add(mPegs[i]);
                else secondLayer.getChildren().add(mPegs[i]);
            } else {
                switch (i % 3) {
                    case 0:
                        firstLayer.getChildren().add(mPegs[i]);
                        break;
                    case 1:
                        secondLayer.getChildren().add(mPegs[i]);
                        break;
                    default:
                        thirdLayer.getChildren().add(mPegs[i]);
                        break;
                }
            }
        }

        getChildren().addAll(firstLayer, secondLayer, thirdLayer);
    }

    /**
     * Return a array of empty pegs.
     *
     * @param pegs is the number of pegs of the array.
     * @return the empty Colorpeg array.
     */
    private static ColorPeg[] emptyColorRow(int pegs) {
        ColorPeg[] result = new ColorPeg[pegs];
        for (int i = 0; i < pegs; ++i) result[i] = new ColorPeg();
        return result;
    }

    private static ColorPeg[] stringToArray(String row) {
        String[] pegsString = row.split(" ");
        ColorPeg[] pegs = new ColorPeg[pegsString.length];
        for (int i = 0; i < pegsString.length; ++i) {
            pegs[i] = new ColorPeg(Integer.parseInt(pegsString[i]) - 1);
        }
        return pegs;
    }

    /**
     * Converts a ColorRow to a string.
     *
     * @return the ColoRow in string format.
     */
    @Override
    public String toString() {
        StringBuilder row = new StringBuilder();
        for (ColorPeg peg : mPegs) row.append(peg.getColorId()).append(" ");
        return row.toString().trim();
    }

    /**
     * Converts a ColorRow to a array of ints.
     *
     * @return the ColoRow in array of ints.
     */
    public int[] toIntArray() {
        int[] result = new int[mPegs.length];
        for (int i = 0; i < mPegs.length; ++i) result[i] = mPegs[i].getColorId();
        return result;
    }

    private HBox createRowBox() {
        HBox box = new HBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    /**
     * Set the Action listener to the pegs.
     *
     * @param colors the number of colors of the row.
     */
    public void setSelectionActionListener(int colors) {
        for (ColorPeg peg : mPegs) {
            peg.setOnMouseClicked(event -> {
                int color = (peg.getColorId() % colors) + 1;
                peg.setColor(color, ColorUtils.getColor(color));
            });
        }
    }
}
