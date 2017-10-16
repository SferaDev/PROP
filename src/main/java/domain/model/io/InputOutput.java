package domain.model.io;

import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

public interface InputOutput {
    // Inputs
    Row<ControlPeg> inputControlRow();
    Row<ColorPeg> inputColorRow();

    // Outputs
    void outputControlRow(Row<ControlPeg> row);
    void outputColorRow(Row<ColorPeg> row);
}
