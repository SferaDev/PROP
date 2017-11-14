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

    /**
     * Is the correct guess of the game
     */
    private ColorRow makerCorrectGuess;

    /**
     * Instantiates a new Computer player
     * @param role the role of the Computer player
     */
    public ComputerPlayer(Role role) {
        super(role);
    }

    /**
     * Compare guess control row in order to generate the number of blacks and whites of the attempt
     * @param correct is the correct code
     * @param guess   is the guess attempt that we want to correct
     * @return the control row of the attempt
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
     * Generates a random ColorRow.
     * @param pegs is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the random color row generated
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
     * Creates a new computer by the name, and assigns to the computer the role.
     * @param computerName the computer name
     * @param computerRole the computer role
     * @return the new computer player
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

    /**
     * Calls to compareGuess in order to generate the number of blacks and whites of the attempt.
     * @param guess the guess that we want to correct
     * @return the number of blacks ano whitse of the attempt
     * @see #compareGuess(ColorRow, ColorRow)
     */
    @Override
    public ControlRow scoreGuess(ColorRow guess) {
        return compareGuess(makerCorrectGuess, guess);
    }

    /**
     * Generates a random secret code
     * @param pegs is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the secret code
     */
    @Override
    public ColorRow makerGuess(int pegs, int colors) {
        makerCorrectGuess = randomRow(pegs, colors);
        return makerCorrectGuess;
    }

    /**
     * Generates a help attempt. A help attempt is an attempt with more blacks or whites than you had in the last attempt
     * @param correctGuess is the correct ColorRow guess
     * @param status is the ControlRow of the last attempt
     * @param pegs is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the help attempt generated
     */
    public static ColorRow guessHelp(ColorRow correctGuess, ControlRow status, int pegs, int colors) {
        ColorRow helpAttempt;
        ControlRow controlRow;
        do {
            helpAttempt = randomRow(pegs, colors);
            controlRow = compareGuess(correctGuess, helpAttempt);
        } while (controlRow.getBlacks() < status.getBlacks() || controlRow.getWhites() < status.getWhites());
        return helpAttempt;
    }
}
