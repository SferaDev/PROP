package presentation.visual.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.visual.utils.ColorManager;

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

        HBox top = createRowBox();
        HBox bottom = createRowBox();

        for (int i = 0; i < mPegs.length; i++) {
            if (mPegs.length < 5) top.getChildren().add(mPegs[i]);
            else if (i%2 == 0) top.getChildren().add(mPegs[i]);
            else bottom.getChildren().add(mPegs[i]);
        }

        getChildren().addAll(top, bottom);
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

    private static ColorPeg[] stringToArray(String row) {
        String[] pegsString = row.split(" ");
        ColorPeg[] pegs = new ColorPeg[pegsString.length];
        for (int i = 0; i < pegsString.length; ++i) {
            pegs[i] = new ColorPeg(Integer.parseInt(pegsString[i]));
        }
        return pegs;
    }

    public void setSelectionActionListener(int colors) {
        for (ColorPeg peg : mPegs) {
            peg.setOnMouseClicked(event -> {
                int color = (peg.getColorId() + 1)%colors;
                peg.setColor(color, ColorManager.getColor(color));
            });
        }
    }
}
