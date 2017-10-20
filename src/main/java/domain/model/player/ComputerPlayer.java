package domain.model.player;

import domain.model.Role;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

import java.util.ArrayList;
import java.util.Random;

public abstract class ComputerPlayer extends Player {
    public ComputerPlayer(Role role) {
        super(role);
    }

    public static Row<ControlPeg> compareGuess(Row<ColorPeg> correct, Row<ColorPeg> guess) {
        Row<ControlPeg> result = new Row<>();
        // TODO
        return result;
    }

    protected static Row<ColorPeg> randomRow(int pegs, int colors) {
        Row<ColorPeg> row = new Row<>();
        Random rand = new Random();
        for (int i = 0; i < pegs; ++i) {
            row.add(new ColorPeg(rand.nextInt(colors) + 1));
        }
        return row;
    }
}
