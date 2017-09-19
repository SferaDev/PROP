package domain.model.io;

import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

public class TerminalIO implements InputOutput {
    @Override
    public Row<ControlPeg> requestControlRow() {
        return null;
    }

    @Override
    public Row<ColorPeg> requestColorRow() {
        return null;
    }

    @Override
    public void outputControlRow(Row<ControlPeg> row) {

    }

    @Override
    public void outputColorRow(Row<ColorPeg> row) {

    }
}
