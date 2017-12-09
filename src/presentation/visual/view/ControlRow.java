package presentation.visual.view;

import javafx.scene.layout.GridPane;

public class ControlRow extends ViewItem {
    public ControlRow(int size, int blacks, int whites) {
        super(ControlRow.class.getSimpleName());

        int position = 0;

        // Add Black pegs
        for (int i = 0; i < blacks; i++, position++) {
            ControlPeg peg = new ControlPeg(ControlPeg.Type.black);
            GridPane.setColumnIndex(peg, position/2);
            GridPane.setRowIndex(peg, position%2);
            getChildren().add(peg);
        }

        // Add White pegs
        for (int i = 0; i < whites; i++, position++) {
            ControlPeg peg = new ControlPeg(ControlPeg.Type.white);
            GridPane.setColumnIndex(peg, position/2);
            GridPane.setRowIndex(peg, position%2);
            getChildren().add(peg);
        }

        // Fill None pegs
        for (int i = position; i < size; i++) {
            ControlPeg peg = new ControlPeg(ControlPeg.Type.none);
            GridPane.setColumnIndex(peg, position/2);
            GridPane.setRowIndex(peg, position%2);
            getChildren().add(peg);
        }
    }
}
