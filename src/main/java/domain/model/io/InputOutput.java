package domain.model.io;

import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

public interface InputOutput {
    Row<ControlPeg> requestControlRow();
    Row<ColorPeg> requestColorRow();
    void outputControlRow(Row<ControlPeg> row);
    void outputColorRow(Row<ColorPeg> row);
}
