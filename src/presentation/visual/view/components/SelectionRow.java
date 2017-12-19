package presentation.visual.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class SelectionRow extends HBox {
    private ArrayList<SelectionPeg> selectionPegs = new ArrayList<>();

    public SelectionRow(int pegs, ArrayList<String> availableColors) {
        setAlignment(Pos.CENTER);
        setSpacing(10);

        GridPane.setMargin(this, new Insets(10, 10, 10, 10));

        setStyle("-fx-spacing: 25; -fx-padding: 0 10;");

        for (int i = 0; i < pegs; i++) {
            SelectionPeg peg = new SelectionPeg(availableColors);
            selectionPegs.add(peg);
            getChildren().add(peg);
        }
    }

    public ArrayList<String> getColors() {
        ArrayList<String> result = new ArrayList<>();
        for (SelectionPeg peg : selectionPegs) result.add(peg.getColor());
        return result;
    }
}
