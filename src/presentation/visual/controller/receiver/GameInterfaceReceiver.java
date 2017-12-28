package presentation.visual.controller.receiver;

import domain.model.Receiver;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import javafx.application.Platform;
import presentation.terminal.utils.TerminalUtils;
import presentation.visual.controller.PresentationController;
import presentation.visual.utils.ComponentUtils;
import presentation.visual.utils.ThreadUtils;
import presentation.visual.view.components.ColorRow;

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
        final int[] result = {0};
        ThreadUtils.runAndWait(() -> result[0] = PresentationController.getInstance().getNebulaController()
                .requestControl("Input Blacks")); // TODO: String
        return result[0];
    }


    /**
     * Reads the number of whites from the terminal
     *
     * @return the number of whites
     */
    @Override
    public int inputControlWhites() {
        final int[] result = {0};
        ThreadUtils.runAndWait(() -> result[0] = PresentationController.getInstance().getNebulaController()
                .requestControl("Input Whites")); // TODO: String
        return result[0];
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
            result[i] = 1;
            row.append(result[i]).append(" ");
        }
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
        Platform.runLater(() -> PresentationController.getInstance().getNebulaController().addControlLastTurn(blacks, whites));
    }

    /**
     * Writes in the terminal a color pegs' combination
     *
     * @param row the row
     */
    @Override
    public void outputColorRow(String row) {
        Platform.runLater(() -> PresentationController.getInstance().getNebulaController().addTurn(new ColorRow(row)));
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
        ComponentUtils.showErrorDialog("Mastermind", message);
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
        Platform.runLater(() -> PresentationController.getInstance().getNebulaController().finishGame());
    }

    /**
     * Notifies the Breaker's victory and notifies his score
     *
     * @param score the score obtained in the game
     */
    public void finishGame(int score) {
        outputMessage("You won, with score: " + score);
        Platform.runLater(() -> PresentationController.getInstance().getNebulaController().finishGame());
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
        Platform.runLater(() -> PresentationController.getInstance().getNebulaController().startGame(Integer.parseInt(title.split("-")[2])));
    }
}
