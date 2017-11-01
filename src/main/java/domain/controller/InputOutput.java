package domain.controller;

import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

public interface InputOutput {
    // Inputs
    ControlRow inputControlRow(int pegs);

    ColorRow inputColorRow(int pegs, int colors);

    // Outputs
    void outputControlRow(ControlRow row);

    void outputColorRow(ColorRow row);
}
