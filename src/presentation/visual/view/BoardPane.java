package presentation.visual.view;

import javafx.scene.layout.GridPane;

public class BoardPane extends ViewItem {
    private int posColorRow, posControlRow;
    public BoardPane() {
        super(BoardPane.class.getSimpleName());
        posColorRow = posControlRow = 0;
    }

    public void addColorRow(ColorRow... rows) {
        for (ColorRow row : rows) {
            GridPane.setColumnIndex(row, 1);
            GridPane.setRowIndex(row, posColorRow);
            getChildren().add(row);
            posColorRow++;
        }
    }

    public void addControlRow(ControlRow... rows) {
        for (ControlRow row : rows) {
            GridPane.setColumnIndex(row, 2);
            GridPane.setRowIndex(row, posControlRow);
            getChildren().add(row);
            posControlRow++;
        }
    }
}
