package domain.model.player;

import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.Random;

public abstract class ComputerPlayer extends Player {
    protected ColorRow makerCorrectGuess;

    public ComputerPlayer(Role role) {
        super(role);
    }

    public static ControlRow compareGuess(ColorRow correct, ColorRow guess) {
        int blacks, whites;
        blacks = whites = 0;

        ColorRow correctCopy = new ColorRow(correct);
        ColorRow guessCopy = new ColorRow(guess);

        // Calculate Blacks
        for (int i = 0; i < correctCopy.size(); ++i) {
            if (correct.get(i).equals(guess.get(i))) {
                blacks += 1;
                correctCopy.set(i, null);
                guessCopy.set(i, null);
            }
        }

        // Calculate Whites
        for (ColorRow.ColorPeg colorPeg : correctCopy) {
            boolean found = false;
            for (int j = 0; j < guessCopy.size(); ++j) {
                if (!found && guessCopy.get(j) != null) {
                    if (guessCopy.get(j).equals(colorPeg)) {
                        whites += 1;
                        found = true;
                        guessCopy.set(j, null);
                    }
                }
            }
        }
        return new ControlRow(blacks, whites);
    }

    protected static ColorRow randomRow(int pegs, int colors) {
        ColorRow row = new ColorRow();
        Random rand = new Random();
        for (int i = 0; i < pegs; ++i) {
            row.add(new ColorRow.ColorPeg(rand.nextInt(colors) + 1));
        }
        return row;
    }

    @Override
    public ControlRow scoreGuess(ColorRow guess) {
        return ComputerPlayer.compareGuess(makerCorrectGuess, guess);
    }

    @Override
    public ColorRow makerGuess(int pegs, int colors) {
        makerCorrectGuess = randomRow(pegs, colors);
        return makerCorrectGuess;
    }
}
