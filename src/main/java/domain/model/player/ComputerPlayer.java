package domain.model.player;

import domain.model.Role;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

import java.util.Random;

public abstract class ComputerPlayer extends Player {
    public ComputerPlayer(Role role) {
        super(role);
    }

    public static Row<ControlPeg> compareGuess(Row<ColorPeg> correct, Row<ColorPeg> guess) {
        Row<ControlPeg> result = new Row<>();
        Row<ColorPeg> correctCopy = (Row<ColorPeg>) correct.clone();
        Row<ColorPeg> guessCopy = (Row<ColorPeg>) guess.clone();

        // Calculate Blacks
        for (int i = 0; i < correctCopy.size(); ++i) {
            if (correct.get(i).equals(guess.get(i))) {
                result.add(new ControlPeg(ControlPeg.Type.BLACK));
                correctCopy.set(i, null);
                guessCopy.set(i, null);
            }
        }

        // Calculate Whites
        for (ColorPeg colorPeg : correctCopy) {
            boolean found = false;
            for (int j = 0; j < guessCopy.size(); ++j) {
                if (!found && guessCopy.get(j) != null) {
                    if (guessCopy.get(j).equals(colorPeg)) {
                        result.add(new ControlPeg(ControlPeg.Type.WHITE));
                        found = true;
                        guessCopy.set(j, null);
                    }
                }
            }
        }

        // Add missing emptys
        for (int i = result.size(); i < correct.size(); ++i)
            result.add(new ControlPeg(ControlPeg.Type.EMPTY));
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
