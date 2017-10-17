package domain.model;

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
