package domain.controller;

import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

public interface InputOutput {
    // Inputs
    Row<ControlPeg> inputControlRow(int pegs);

    Row<ColorPeg> inputColorRow(int pegs, int colors);

    // Outputs
    void outputControlRow(Row<ControlPeg> row);

    void outputColorRow(Row<ColorPeg> row);
}
