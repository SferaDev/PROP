package domain.model.player;

import domain.model.player.computer.DummyComputer;
import domain.model.player.computer.FiveGuessComputer;
import domain.model.player.computer.GeneticComputer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.Random;

/**
 * The type Computer player.
 */
public abstract class ComputerPlayer extends Player implements java.io.Serializable {
    private ColorRow makerCorrectGuess;

    /**
     * Instantiates a new Computer player.
     *
     * @param role the role
     */
    public ComputerPlayer(Role role) {
        super(role);
    }

    /**
     * Compare guess control row.
     *
     * @param correct the correct
     * @param guess   the guess
     * @return the control row
     */
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

    /**
     * Random row color row.
     *
     * @param pegs   the pegs
     * @param colors the colors
     * @return the color row
     */
    protected static ColorRow randomRow(int pegs, int colors) {
        ColorRow row = new ColorRow();
        Random rand = new Random();
        for (int i = 0; i < pegs; ++i) {
            row.add(new ColorRow.ColorPeg(rand.nextInt(colors) + 1));
        }
        return row;
    }

    /**
     * New computer by name computer player.
     *
     * @param computerName the computer name
     * @param computerRole the computer role
     * @return the computer player
     */
    public static ComputerPlayer newComputerByName(String computerName, Role computerRole) {
        // If we could only use Reflection...
        switch (computerName) {
            case "GeneticComputer":
                return new GeneticComputer(computerRole);
            case "FiveGuessComputer":
                return new FiveGuessComputer(computerRole);
            case "DummyComputer":
            default:
                return new DummyComputer(computerRole);
        }
    }

    @Override
    public ControlRow scoreGuess(ColorRow guess) {
        return compareGuess(makerCorrectGuess, guess);
    }

    @Override
    public ColorRow makerGuess(int pegs, int colors) {
        makerCorrectGuess = randomRow(pegs, colors);
        return makerCorrectGuess;
    }

    /**
     * Guess help color row.
     *
     * @param status the status
     * @param pegs   the pegs
     * @param colors the colors
     * @return the color row
     */
    public ColorRow guessHelp(ControlRow status, int pegs, int colors) {
        ColorRow helpAttempt;
        ControlRow controlRow;
        do {
            helpAttempt = randomRow(pegs, colors);
            controlRow = scoreGuess(helpAttempt);
        } while (controlRow.getBlacks() < status.getBlacks() || controlRow.getWhites() < status.getWhites());
        return helpAttempt;
    }
}
