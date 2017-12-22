package presentation.visual.view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * BoardPane Spec:
 * - The BoardPane is the main content placeholder
 * - The BoardPane inherits a GridPane
 * - Each turn adds three things:
 * - A TurnLabel   (Label class)    in position (0, x)
 * - A ColorRow    (HBox class)     in position (1, x)
 * - A ControlRow  (GridPane class) in position (2, x)
 * <p>
 * ColorRow Spec:
 * - The ColorRow inherits a HBox
 * - Each turn contains N ColorPeg(s)
 * <p>
 * ControlRow Spec:
 * - The ControlRow inherits a GridPane
 * - Each turn contains N ControlPeg(s) in position ((i - 1)/2, (i - 1)%2)
 * <p>
 * ColorPeg Spec:
 * - The ColorPeg inherits a JFXButton
 * - The ColorPeg extends styleClass: color-peg
 * - The ColorPeg defines -fx-background-color with PegColor
 * <p>
 * ControlPeg Spec:
 * - The ControlPeg inherits a JFXButton
 * - The ControlPeg extends styleClass: control-peg
 * - The ControlPeg must extend one of the following styleClasses:
 * - control-peg-black
 * - control-peg-white
 * - control-peg-none
 **/

public class BoardPane extends GridPane {
    private int posColorRow, posControlRow, size;

    public BoardPane(int size) {
        getStylesheets().add(getClass().getResource("/resources/css/Board.css").toExternalForm());
        getStyleClass().add("board-background");
        setAlignment(Pos.CENTER);
        posColorRow = posControlRow = 0;
        this.size = size;
    }

    public void addColorRow(ColorRow... rows) {
        for (ColorRow row : rows) {
            add(new TurnLabel("Turn " + (posColorRow + 1)), 0, posColorRow);
            add(row, 1, posColorRow);
            posColorRow++;
        }
    }

    public void addControlRow(ControlRow... rows) {
        for (ControlRow row : rows) {
            add(row, 2, posControlRow);
            posControlRow++;
        }
    }

    public int getSize() {
        return size;
    }
}
