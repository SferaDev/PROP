package presentation.visual.view;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class ControlRow extends GridPane {
    public ControlRow(int size, int blacks, int whites) {
        GridPane.setMargin(this, new Insets(10, 10, 10, 10));

        int position = 0;

        // Add Black pegs
        for (int i = 0; i < blacks; i++, position++) {
            ControlPeg peg = new ControlPeg(ControlPeg.Type.black);
            add(peg, position%(size/2 + size%2), position/(size/2 + size%2));
        }

        // Add White pegs
        for (int i = 0; i < whites; i++, position++) {
            ControlPeg peg = new ControlPeg(ControlPeg.Type.white);
            add(peg, position%(size/2 + size%2), position/(size/2 + size%2));
        }

        // Fill None pegs
        for (int i = position; i < size; i++, position++) {
            ControlPeg peg = new ControlPeg(ControlPeg.Type.none);
            add(peg, position%(size/2 + size%2), position/(size/2 + size%2));
        }
    }
}
