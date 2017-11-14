package domain.model.player.computer;

import domain.InputOutput;
import domain.controller.DomainController;
import domain.model.player.ComputerPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

/**
 * The type Dummy computer.
 */
public class DummyComputer extends ComputerPlayer implements java.io.Serializable {

    /**
     * Instantiates a new Dummy computer.
     *
     * @param role the role
     */
    public DummyComputer(Role role) {
        super(role);
    }

    /**
     * Return the name of the algorithm
     */
    @Override
    public String getName() {
        return "Dummy";
    }

    /**
     * Generates the next attempt. The guess is generated randomly.
     * @param pegs   is the number of pegs accepted in the game
     * @param colors is the number of colors accepted in the game
     * @return the combination that is going to be tried
     */
    @Override
    public ColorRow breakerGuess(int pegs, int colors) {
        return randomRow(pegs, colors);
    }

    /**
     * Recives the correction of the previous attempt. As each attempts is independent from the previous,
     * we do nothing.
     * @param control is the number of black and white pegs of the last attempt
     */
    @Override
    public void receiveControl(ControlRow control) {
        // I don't care, I love it!
    }

    @Override
    public void notifyInvalidInput() {
        // Should never happen
    }

    /**
     * Generates the correction of one guess
     * @param guess is the attempt that will be corrected
     * @return the number of blacks and whites of that guess
     */
    @Override
    public ControlRow scoreGuess(ColorRow guess) {
        ControlRow score = super.scoreGuess(guess);
        DomainController domainController = DomainController.getInstance();
        if (domainController.isDebugBuild()) {
            InputOutput gameInterface = domainController.getGameInterface();
            gameInterface.outputColorRow(guess.toString());
            gameInterface.outputControlRow(score.getBlacks(), score.getWhites());
            gameInterface.outputMessage("");
        }
        return score;
    }

    /**
     *Generates a random secret code
     * @param pegs is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the secret code
     */
    @Override
    public ColorRow makerGuess(int pegs, int colors) {
        ColorRow correctGuess = super.makerGuess(pegs, colors);
        DomainController domainController = DomainController.getInstance();
        if (DomainController.getInstance().isDebugBuild()) {
            InputOutput gameInterface = domainController.getGameInterface();
            gameInterface.outputMessage("Pegs: " + pegs);
            gameInterface.outputMessage("Colors: " + colors);
            gameInterface.outputMessage("Correct Guess is:");
            gameInterface.outputColorRow(correctGuess.toString());
            gameInterface.outputMessage("");
        }
        return correctGuess;
    }
}
