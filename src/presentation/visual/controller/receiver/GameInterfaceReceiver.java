package presentation.visual.controller.receiver;

import domain.model.Receiver;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import presentation.visual.controller.BoardController;
import presentation.visual.controller.PresentationController;
import presentation.visual.utils.ComponentUtils;
import presentation.visual.view.BoardPane;
import presentation.visual.view.ColorRow;

/**
 * The type Game Interface Receiver
 *
 * @author Alexis Rico Carreto
 */
public class GameInterfaceReceiver implements Receiver {

    /**
     * Reads the number of blacks from the terminal
     *
     * @return the number of blacks
     */
    @Override
    public int inputControlBlacks() {
        // Request Blacks
        int result = 0;
        return result;
    }


    /**
     * Reads the number of whites from the terminal
     *
     * @return the number of whites
     */
    @Override
    public int inputControlWhites() {
        // Request Whites
        int result = 0;
        return result;
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
        /**StringBuilder row = new StringBuilder();
        TerminalUtils.getInstance().printLine("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors");
        for (int i = 0; i < pegs; ++i) {
            result[i] = TerminalUtils.getInstance().readGameInteger();
            row.append(result[i]).append(" ");
        }
        PresentationController.getInstance().getCurrentBoard().addTurn(new Turn(row.toString().trim()));**/
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
        PresentationController.getInstance().getCurrentBoard().addControlLastTurn(blacks, whites);
    }


    /**
     * Writes in the terminal a color pegs' combination
     *
     * @param row the row
     */
    @Override
    public void outputColorRow(String row) {
        PresentationController.getInstance().getCurrentBoard().addTurn(new ColorRow());
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
    }

    /**
     * Writes in the terminal a color combination to help the Breaker
     *
     * @param row the combination to help de Breaker
     */
    @Override
    public void outputHintColorRow(String row) {
    }

    /**
     * Writes in the terminal the message
     *
     * @param message the message
     */
    @Override
    public void outputMessage(String message) {
    }

    /**
     * Notifies an invalid input
     */
    @Override
    public void notifyInvalidInput() {
        ComponentUtils.showErrorDialog("Entrada invalida", "Has fet algun error!"); // TODO: Strings
    }

    /**
     * Notifies the game is finished because there are no more turns
     */
    @Override
    public void finishGame() {
        PresentationController.getInstance().setCurrentBoard(null);
    }

    /**
     * Notifies the Breaker's victory and notifies his score
     *
     * @param score the score obtained in the game
     */
    public void finishGame(int score) {
        PresentationController.getInstance().setCurrentBoard(null);
    }

    /**
     * Notifies an invalid control
     */
    @Override
    public void notifyInvalidControl() {
        ComponentUtils.showErrorDialog("Això és mentida!", "Tranqui tots ho fem :)"); // TODO: Strings
    }

    /**
     * Starts the game
     *
     * @param title the title
     */
    @Override
    public void startGame(String title) {
        PresentationController.getInstance().setCurrentBoard(new BoardController(new BoardPane(Integer.parseInt(title.split("-")[2]))));
    }
}
