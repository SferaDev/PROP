package domain.model;

import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

public interface InputOutput {
    // Inputs
    Row<ControlPeg> inputControlRow(int size);

    Row<ColorPeg> inputColorRow(int size);

    // Outputs
    void outputControlRow(Row<ControlPeg> row);

    void outputColorRow(Row<ColorPeg> row);
}
