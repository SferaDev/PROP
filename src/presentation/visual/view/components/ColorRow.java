package presentation.visual.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ColorRow extends VBox {

    public ColorRow(ColorPeg... pegs) {
        setAlignment(Pos.CENTER);
        setSpacing(10);

        GridPane.setMargin(this, new Insets(10, 10, 10, 10));

        HBox top = createRowBox();
        HBox bottom = createRowBox();

        for (int i = 0; i < pegs.length; i++) {
            if (pegs.length < 5) top.getChildren().add(pegs[i]);
            else if (i%2 == 0) top.getChildren().add(pegs[i]);
            else bottom.getChildren().add(pegs[i]);
        }

        getChildren().addAll(top, bottom);
    }

    public ColorRow(String row) {
        this(stringToArray(row));
    }

    private static ColorPeg[] stringToArray(String row) {
        String[] pegsString = row.split(" ");
        ColorPeg[] pegs = new ColorPeg[pegsString.length];
        for (int i = 0; i < pegsString.length; ++i) {
            pegs[i] = new ColorPeg(Integer.parseInt(pegsString[i]));
        }
        return pegs;
    }

    private HBox createRowBox() {
        HBox box = new HBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);
        return box;
    }
}
