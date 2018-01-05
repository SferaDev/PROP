package presentation.controller.receiver;

import domain.model.Receiver;
import javafx.geometry.Insets;
import presentation.controller.LocaleController;
import presentation.controller.PresentationController;
import presentation.utils.ColorManager;
import presentation.utils.ComponentUtils;
import presentation.utils.ThreadUtils;
import presentation.view.components.ColorInput;
import presentation.view.components.ColorRow;
import presentation.view.components.ControlInput;
import presentation.view.components.ControlRow;

/**
 * The type Game Interface Receiver
 *
 * @author Alexis Rico Carreto
 */
public class GameInterfaceReceiver implements Receiver {

    /**
     * Request the number of blacks
     *
     * @return Returns the number of blacks.
     */
    @Override
    public int inputControlBlacks() {
        return requestControl(LocaleController.getInstance().getString("INPUT_BLACKS"));
    }

    /**
     * Request the number of whites
     *
     * @return Returns the number of whites.
     */
    @Override
    public int inputControlWhites() {
        return requestControl(LocaleController.getInstance().getString("INPUT_WHITES"));
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
            outputMessage(LocaleController.getInstance().getString("NUMBER_FORMAT_EX"));
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


    /**
     * Adds the correct control row.
     *
     * @param pegs   is the number of pegs in the combination.
     * @param colors is the number of different possible colors in a combination.
     * @return Returns the correct control row as a array of ints.
     */
    @Override
    public int[] inputCorrectColorRow(int pegs, int colors) {
        ColorRow row = requestColorRow(pegs, colors);
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().addCorrectRow(new ColorRow(row.toString())));
        return row.toIntArray();
    }

    private ColorRow requestColorRow(int pegs, int colors) {
        ColorInput colorInput = new ColorInput(LocaleController.getInstance().getString("INPUT_GUESS"), pegs, colors);
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
        ControlRow controlRow = new ControlRow(blacks, whites);
        controlRow.setStyle("-fx-background-color: " + ColorManager.getColor("MAIN_COLOR"));
        controlRow.setPadding(new Insets(15));
        ThreadUtils.runAndWait(() -> ComponentUtils.showCustomDialog("Correct control", controlRow));
    }

    /**
     * Writes in the terminal a color combination to help the Breaker
     *
     * @param row the combination to help de Breaker
     */
    @Override
    public void outputHintColorRow(String row) {
        ColorRow colorRow = new ColorRow(row);
        colorRow.setStyle("-fx-background-color: " + ColorManager.getColor("MAIN_COLOR"));
        colorRow.setPadding(new Insets(15));
        ThreadUtils.runAndWait(() -> ComponentUtils.showCustomDialog("Possible Guess", colorRow));
    }

    /**
     * Writes in the terminal the message
     *
     * @param message the message
     */
    @Override
    public void outputMessage(String message) {
        ThreadUtils.runAndWait(() -> ComponentUtils.showErrorDialog(LocaleController.getInstance().getString("APP_TITLE"), message));
    }

    /**
     * Notifies an invalid input
     */
    @Override
    public void notifyInvalidInput() {
        ThreadUtils.runAndWait(() -> ComponentUtils.showErrorDialog(LocaleController.getInstance().getString("INVALID_INPUT"), LocaleController.getInstance().getString("MISTAKE")));
    }

    /**
     * Notifies the game is finished because there are no more turns
     */
    @Override
    public void finishGame() {
        outputMessage(LocaleController.getInstance().getString("GAME_FINISHED"));
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().finishGame());
    }

    /**
     * Notifies the Breaker's victory and notifies his score
     *
     * @param score the score obtained in the game
     */
    public void finishGame(int score) {
        outputMessage(LocaleController.getInstance().getString("YOU_WON") + score);
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().finishGame());
    }

    /**
     * Notifies an invalid control
     */
    @Override
    public void notifyInvalidControl() {
        ThreadUtils.runAndWait(() -> ComponentUtils.showErrorDialog(LocaleController.getInstance().getString("LIE"), LocaleController.getInstance().getString("KEEP_CALM")));
    }

    /**
     * Starts the game
     *
     * @param title is the name of the game
     */
    @Override
    public void startGame(String title) {
        String[] gameInfo = title.split("-");
        ThreadUtils.runAndWait(() -> PresentationController.getInstance().getNebulaController().startGame(gameInfo[1],
                gameInfo[2], Integer.parseInt(gameInfo[3]), Integer.parseInt(gameInfo[4])));
    }
}
