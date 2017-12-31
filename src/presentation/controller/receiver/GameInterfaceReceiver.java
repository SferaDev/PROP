package presentation.controller.receiver;

import domain.model.Receiver;
import presentation.controller.PresentationController;
import presentation.utils.ComponentUtils;
import presentation.utils.ThreadUtils;
import presentation.view.components.ColorInput;
import presentation.view.components.ColorRow;
import presentation.view.components.ControlInput;

/**
 * The type Game Interface Receiver
 *
 * @author Alexis Rico Carreto
 */
public class  GameInterfaceReceiver implements Receiver {

    @Override
    public int inputControlBlacks() {
        return requestControl("Input Blacks"); // TODO: Strings
    }

    @Override
    public int inputControlWhites() {
        return requestControl("Input Whites"); // TODO: Strings
    }

    private int requestControl(String title) {
        ControlInput controlInput = new ControlInput(title);
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().addActionPane(controlInput));
        ThreadUtils.runAndWait(controlInput::requestFocus);
        synchronized (controlInput) {
            try {
                controlInput.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().removeActionPane());
        int result = 0;
        try {
            result = Integer.parseInt(controlInput.getResult());
        } catch (NumberFormatException e) {
            outputMessage("NumberFormatException"); // TODO: Strings
        }
        return result;
    }

    /**
     * Reads a combination of color pegs from the terminal
     *
     * @param pegs   is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the combination of color pegs
     */
    @Override
    public int[] inputColorRow(int pegs, int colors) {
        return requestColorRow(pegs, colors).toIntArray();
    }

    @Override
    public int[] inputCorrectColorRow(int pegs, int colors) {
        ColorRow row = requestColorRow(pegs, colors);
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().addCorrectRow(new ColorRow(row.toString())));
        return row.toIntArray();
    }

    private ColorRow requestColorRow(int pegs, int colors) {
        ColorInput colorInput = new ColorInput("Input Guess", pegs, colors);
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().addActionPane(colorInput));
        synchronized (colorInput) {
            try {
                colorInput.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().removeActionPane());
        return colorInput.getResult();
    }

    /**
     * Writes in the terminal the number of blacks and whites of the control combination
     *
     * @param blacks the number of blacks in the combination
     * @param whites the number of whites int he combination
     */

    @Override
    public void outputControlRow(int blacks, int whites) {
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().addControlLastTurn(blacks, whites));
    }

    /**
     * Writes in the terminal a color pegs' combination
     *
     * @param row the row
     */
    @Override
    public void outputColorRow(String row) {
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().addTurn(new ColorRow(row)));
    }

    /**
     * Writes in the terminal a hint to know the correct control peg
     *
     * @param blacks the number of blacks
     * @param whites the number of whites
     */
    @Override
    public void outputHintControlRow(int blacks, int whites) {
        // TODO
    }

    /**
     * Writes in the terminal a color combination to help the Breaker
     *
     * @param row the combination to help de Breaker
     */
    @Override
    public void outputHintColorRow(String row) {
        // TODO
    }

    /**
     * Writes in the terminal the message
     *
     * @param message the message
     */
    @Override
    public void outputMessage(String message) {
        ThreadUtils.runAndWait(() -> ComponentUtils.showErrorDialog("Mastermind", message));
    }

    /**
     * Notifies an invalid input
     */
    @Override
    public void notifyInvalidInput() {
        ThreadUtils.runAndWait(() -> ComponentUtils.showErrorDialog("Entrada invalida", "Has fet algun error!")); // TODO: Strings
    }

    /**
     * Notifies the game is finished because there are no more turns
     */
    @Override
    public void finishGame() {
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().finishGame());
    }

    /**
     * Notifies the Breaker's victory and notifies his score
     *
     * @param score the score obtained in the game
     */
    public void finishGame(int score) {
        outputMessage("You won, with score: " + score);
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().finishGame());
    }

    /**
     * Notifies an invalid control
     */
    @Override
    public void notifyInvalidControl() {
        ThreadUtils.runAndWait(() -> ComponentUtils.showErrorDialog("Això és mentida!", "Tranqui tots ho fem :)")); // TODO: Strings
    }

    /**
     * Starts the game
     *
     * @param title the title
     */
    @Override
    public void startGame(String title) {
        String[] gameInfo = title.split("-");
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().startGame(gameInfo[1],
                Integer.parseInt(gameInfo[2]), Integer.parseInt(gameInfo[3])));
    }
}
