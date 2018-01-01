package presentation.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.utils.ColorManager;

public class ColorRow extends VBox {
    private ColorPeg[] mPegs;

    public ColorRow(String row) {
        this(stringToArray(row));
    }

    public ColorRow(ColorPeg... pegs) {
        mPegs = pegs;
        setAlignment(Pos.CENTER);
        setSpacing(10);

        GridPane.setMargin(this, new Insets(10, 10, 10, 10));

        HBox firstLayer = createRowBox();
        HBox secondLayer = createRowBox();
        HBox thirdLayer = createRowBox();

        for (int i = 0; i < mPegs.length; i++) {
            if (mPegs.length < 5) firstLayer.getChildren().add(mPegs[i]);
            else if (mPegs.length < 8) {
                if (i % 2 == 0) firstLayer.getChildren().add(mPegs[i]);
                else secondLayer.getChildren().add(mPegs[i]);
            } else {
                if (i % 3 == 0) firstLayer.getChildren().add(mPegs[i]);
                else if (i % 3 == 1) secondLayer.getChildren().add(mPegs[i]);
                else thirdLayer.getChildren().add(mPegs[i]);
            }
        }

        getChildren().addAll(firstLayer, secondLayer, thirdLayer);
    }

    private static ColorPeg[] stringToArray(String row) {
        String[] pegsString = row.split(" ");
        ColorPeg[] pegs = new ColorPeg[pegsString.length];
        for (int i = 0; i < pegsString.length; ++i) {
            pegs[i] = new ColorPeg(Integer.parseInt(pegsString[i]) - 1);
        }
        return pegs;
    }

    @Override
    public String toString() {
        StringBuilder row = new StringBuilder();
        for (ColorPeg peg : mPegs) row.append(peg.getColorId()).append(" ");
        return row.toString().trim();
    }

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

    public void setSelectionActionListener(int colors) {
        for (ColorPeg peg : mPegs) {
            peg.setOnMouseClicked(event -> {
                int color = (peg.getColorId() % colors) + 1;
                peg.setColor(color, ColorManager.getColor(color));
            });
        }
    }
}
