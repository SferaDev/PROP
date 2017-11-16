package domain.model.player;

import domain.controller.DomainController;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

/**
 * The type User player.
 */
public class UserPlayer extends Player implements java.io.Serializable {

    private final String parentUser;

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


    /**
     * Gets the name of the user
     *
     * @return the name of the user
     */
    @Override
    public String getName() {
        return parentUser;
    }

    /**
     * The Maker user player introduces the correct combination
     *
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the maker's combination
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    @Override
    public ColorRow makerGuess(int pegs, int colors) throws FinishGameException, CommandInterruptException {
        return new ColorRow(DomainController.getInstance().getGameInterface().inputColorRow(pegs, colors));
    }

    /**
     * The Breaker user player introduces a guess
     *
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    @Override
    public ColorRow breakerGuess(int pegs, int colors) throws FinishGameException, CommandInterruptException {
        return new ColorRow(DomainController.getInstance().getGameInterface().inputColorRow(pegs, colors));
    }

    /**
     * The Maker user player gives a controlRow to answer the Breaker's guess
     *
     * @param guess is the combination introduced by the Breaker
     * @return
     * @throws FinishGameException
     * @throws CommandInterruptException
     */
    @Override
    public ControlRow scoreGuess(ColorRow guess) throws FinishGameException, CommandInterruptException {
        receiveColor(guess);
        int blacks = DomainController.getInstance().getGameInterface().inputControlBlacks();
        int whites = DomainController.getInstance().getGameInterface().inputControlWhites();
        return new ControlRow(blacks, whites);
    }

    /**
     * The Breaker user player receives the controlRow
     *
     * @param control the control row
     */
    @Override
    public void receiveColor(ColorRow guess) {
        DomainController.getInstance().getGameInterface().outputColorRow(guess.toString());
    }

    @Override
    public void receiveControl(ControlRow control) {
        DomainController.getInstance().getGameInterface().outputControlRow(control.getBlacks(), control.getWhites());
    }

    /**
     * Notifies invalid input
     */
    @Override
    public void notifyInvalidInput() {
        DomainController.getInstance().getGameInterface().notifyInvalidInput();
    }

    /**
     * Notifies invalid control
     */
    @Override
    public void notifyInvalidControl() {
        DomainController.getInstance().getGameInterface().notifyInvalidControl();
    }

    /**
     * Notifies a hint to the Maker
     *
     * @param row the correct ControlRow
     */
    @Override
    public void notifyHint(ControlRow row) {
        DomainController.getInstance().getGameInterface().outputHintControlRow(row.getBlacks(), row.getWhites());
    }

    /**
     * Notifies a hint to the Breaker
     *
     * @param row a combination to help the breaker
     */
    @Override
    public void notifyHint(ColorRow row) {
        DomainController.getInstance().getGameInterface().outputHintColorRow(row.toString());
    }

    /**
     * Notifies the score to the Breaker
     *
     * @param score the score of the game
     */
    @Override
    public void finishGame() {
        DomainController.getInstance().getGameInterface().finishGame();
    }

    @Override
    public void finishGame(int score) {
        DomainController.getInstance().getGameInterface().finishGame(score);
    }

    @Override
    public void startGame(String gameTitle) {
        DomainController.getInstance().getGameInterface().startGame(gameTitle);
    }
}
