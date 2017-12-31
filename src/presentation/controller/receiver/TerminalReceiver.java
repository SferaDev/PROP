package presentation.controller.receiver;

import domain.model.Receiver;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import presentation.controller.TerminalController;
import presentation.model.Board;
import presentation.model.Turn;
import presentation.utils.Constants;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

/**
 * The type Terminal input
 *
 * @author Alexis Rico Carreto
 */
public class TerminalReceiver implements Receiver {

    /**
     * Reads the number of blacks from the terminal
     *
     * @return the number of blacks
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    @Override
    public int inputControlBlacks() throws FinishGameException, CommandInterruptException {
        TerminalUtils.getInstance().printLine("Introdueixi el numero de Negres");
        return TerminalUtils.getInstance().readGameInteger();
    }


    /**
     * Reads the number of whites from the terminal
     *
     * @return the number of whites
     * @throws FinishGameException       the finish fame exception
     * @throws CommandInterruptException the command interrupt exception
     */
    @Override
    public int inputControlWhites() throws FinishGameException, CommandInterruptException {
        TerminalUtils.getInstance().printLine("Introdueixi el numero de Blanques");
        return TerminalUtils.getInstance().readGameInteger();
    }


    /**
     * Reads a combination of color pegs from the terminal
     *
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the combination of color pegs
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    @Override
    public int[] inputColorRow(int pegs, int colors) throws FinishGameException, CommandInterruptException {
        int[] result = new int[pegs];
        StringBuilder row = new StringBuilder();
        TerminalUtils.getInstance().printLine("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors");
        for (int i = 0; i < pegs; ++i) {
            result[i] = TerminalUtils.getInstance().readGameInteger();
            row.append(result[i]).append(" ");
        }
        TerminalController.getInstance().getCurrentBoard().addTurn(new Turn(row.toString().trim()));
        return result;
    }

    @Override
    public int[] inputCorrectColorRow(int pegs, int colors) throws FinishGameException, CommandInterruptException {
        int[] result = inputColorRow(pegs, colors);
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < pegs; ++i) {
            row.append(result[i]).append(" ");
        }
        TerminalController.getInstance().getCurrentBoard().setCorrectGuess(new Turn(row.toString().trim()));
        return result;
    }

    /**
     * Writes in the terminal the number of blacks and whites of the control combination
     *
     * @param blacks the number of blacks in the combination
     * @param whites the number of whites int he combination
     */

    @Override
    public void outputControlRow(int blacks, int whites) {
        TerminalController.getInstance().getCurrentBoard().addControlLastTurn(blacks, whites);
        TerminalController.getInstance().getCurrentBoard().printTerminal();
    }


    /**
     * Writes in the terminal a color pegs' combination
     *
     * @param row the row
     */
    @Override
    public void outputColorRow(String row) {
        TerminalController.getInstance().getCurrentBoard().addTurn(new Turn(row.trim()));
        TerminalController.getInstance().getCurrentBoard().printTerminal();
    }

    /**
     * Writes in the terminal a hint to know the correct control peg
     *
     * @param blacks the number of blacks
     * @param whites the number of whites
     */
    @Override
    public void outputHintControlRow(int blacks, int whites) {
        outputMessage("Aquesta es la teva ajuda:");
        outputRow(blacks, whites);
    }

    /**
     * Writes in the terminal a color combination to help the Breaker
     *
     * @param row the combination to help de Breaker
     */
    @Override
    public void outputHintColorRow(String row) {
        outputMessage("Aquesta es la teva ajuda:");
        outputRow(row);
    }

    private void outputRow(int blacks, int whites) {
        TerminalUtils.getInstance().printLine("Negres: " + blacks + " | Blanques: " + whites);
    }

    private void outputRow(String row) {
        TerminalUtils.getInstance().printLine(row);
    }

    /**
     * Writes in the terminal the message
     *
     * @param message the message
     */
    @Override
    public void outputMessage(String message) {
        TerminalUtils.getInstance().printLine(message);
    }

    /**
     * Notifies an invalid input
     */
    @Override
    public void notifyInvalidInput() {
        TerminalUtils.getInstance().printLine("Entrada invalida!");
    }

    /**
     * Notifies the game is finished because there are no more turns
     */
    @Override
    public void finishGame() {
        TerminalController.getInstance().setCurrentBoard(null);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Ho sentim, t'has quedat sense torns!");
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    /**
     * Notifies the Breaker's victory and notifies his score
     *
     * @param score the score obtained in the game
     */
    public void finishGame(int score) {
        TerminalController.getInstance().setCurrentBoard(null);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Enhorabona, has guanyat!");
        builder.addDescription("La teva puntuació és: " + score);
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    /**
     * Notifies an invalid control
     */
    @Override
    public void notifyInvalidControl() {
        TerminalUtils.getInstance().printLine("Això és mentida! Tranqui tots ho fem :)");
    }

    /**
     * Starts the game
     *
     * @param title the title
     */
    @Override
    public void startGame(String title) {
        TerminalController.getInstance().setCurrentBoard(new TerminalController.BoardController(new Board(title)));
        TerminalController.getInstance().getCurrentBoard().printTerminal();
    }
}
