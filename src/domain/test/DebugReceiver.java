package domain.test;

import domain.model.Receiver;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import presentation.terminal.utils.TerminalUtils;

/**
 * The type Debug input output.
 *
 * @author Alexis Rico Carreto
 */
public class DebugReceiver implements Receiver {

    @Override
    public int inputControlBlacks() throws FinishGameException, CommandInterruptException {
        TerminalUtils.getInstance().printLine("Introdueixi el numero de Negres");
        return TerminalUtils.getInstance().readGameInteger();
    }

    @Override
    public int inputControlWhites() throws FinishGameException, CommandInterruptException {
        TerminalUtils.getInstance().printLine("Introdueixi el numero de Blanques");
        return TerminalUtils.getInstance().readGameInteger();
    }

    @Override
    public int[] inputColorRow(int pegs, int colors) throws FinishGameException, CommandInterruptException {
        int[] result = new int[pegs];
        TerminalUtils.getInstance().printLine("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors");
        for (int i = 0; i < pegs; ++i) {
            result[i] = TerminalUtils.getInstance().readGameInteger();
        }
        return result;
    }

    @Override
    public int[] inputCorrectColorRow(int pegs, int colors) throws FinishGameException, CommandInterruptException {
        return inputColorRow(pegs, colors);
    }

    @Override
    public void outputControlRow(int blacks, int whites) {
        TerminalUtils.getInstance().printLine("Blacks: " + blacks + " | Whites: " + whites);
    }

    @Override
    public void outputColorRow(String row) {
        TerminalUtils.getInstance().printLine(row);
    }

    @Override
    public void outputHintControlRow(int blacks, int whites) {
        outputControlRow(blacks, whites);
    }

    @Override
    public void outputHintColorRow(String row) {
        outputColorRow(row);
    }

    @Override
    public void outputMessage(String message) {
        TerminalUtils.getInstance().printLine(message);
    }

    @Override
    public void notifyInvalidInput() {
        TerminalUtils.getInstance().printLine("Invalid input!");
    }

    @Override
    public void finishGame() {
        TerminalUtils.getInstance().printLine("Computer has lost");
    }

    public void finishGame(int score) {
        TerminalUtils.getInstance().printLine("Computer has won, score: " + score);
    }

    @Override
    public void notifyInvalidControl() {
        TerminalUtils.getInstance().printLine("Wrong control");
    }

    @Override
    public void startGame(String title) {
        TerminalUtils.getInstance().printLine("Game started: " + title);
    }
}
