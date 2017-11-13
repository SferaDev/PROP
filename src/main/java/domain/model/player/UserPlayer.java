package domain.model.player;

import domain.InputOutput;
import domain.controller.DomainController;
import domain.model.exceptions.FinishGameException;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

/**
 * The type User player.
 */
public class UserPlayer extends Player implements java.io.Serializable {

    private String parentUser;

    private InputOutput gameInterface = DomainController.getInstance().getGameInterface();

    /**
     * Instantiates a new User player.
     *
     * @param user the user
     * @param role the role
     */
    public UserPlayer(String user, Role role) {
        super(role);
        parentUser = user;
    }

    @Override
    public String getName() {
        return parentUser;
    }

    @Override
    public ColorRow makerGuess(int pegs, int colors) throws FinishGameException {
        return new ColorRow(gameInterface.inputColorRow(pegs, colors));
    }

    @Override
    public ColorRow breakerGuess(int pegs, int colors) throws FinishGameException {
        return new ColorRow(gameInterface.inputColorRow(pegs, colors));
    }

    @Override
    public ControlRow scoreGuess(ColorRow guess) throws FinishGameException {
        gameInterface.outputColorRow(guess.toString());
        int blacks = gameInterface.inputControlBlacks(guess.size());
        int whites = gameInterface.inputControlWhites(guess.size());
        return new ControlRow(blacks, whites);
    }

    @Override
    public void receiveControl(ControlRow control) {
        gameInterface.outputControlRow(control.getBlacks(), control.getWhites());
    }

    @Override
    public void notifyInvalidInput() {
        gameInterface.notifyInvalidInput();
    }

    @Override
    public void notifyHint(ControlRow row) {
        gameInterface.outputHintControlRow(row.getBlacks(), row.getWhites());
    }

    @Override
    public void notifyHint(ColorRow row) {
        gameInterface.outputHintColorRow(row.toString());
    }

    @Override
    public void notifyScore(int score) {
        gameInterface.notifyScore(score);
    }
}
