package presentation.model;

import domain.InputOutput;
import domain.model.exceptions.FinishGameException;
import presentation.controller.TerminalController;

/**
 * The type Terminal input output.
 */
public class TerminalInputOutput implements InputOutput {

    @Override
    public int inputControlBlacks(int pegs) throws FinishGameException {
        TerminalController.getInstance().printLine("Introdueixi el numero de Negres");
        return TerminalController.getInstance().readGameInteger();
    }

    @Override
    public int inputControlWhites(int pegs) throws FinishGameException {
        TerminalController.getInstance().printLine("Introdueixi el numero de Blanques");
        return TerminalController.getInstance().readGameInteger();
    }

    @Override
    public int[] inputColorRow(int pegs, int colors) throws FinishGameException {
        int[] result = new int[pegs];
        TerminalController.getInstance().printLine("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors [1 2 2 1]");
        for (int i = 0; i < pegs; ++i) {
            result[i] = TerminalController.getInstance().readGameInteger();
        }
        return result;
    }

    @Override
    public void outputControlRow(int blacks, int whites) {
        TerminalController.getInstance().printLine("Negres: " + blacks + " | Blanques: " + whites);
    }

    @Override
    public void outputColorRow(String row) {
        TerminalController.getInstance().printLine(row);
    }

    @Override
    public void outputMessage(String message) {
        TerminalController.getInstance().printLine(message);
    }

    @Override
    public void notifyInvalidInput() {
        TerminalController.getInstance().printLine("Això és mentida! Tranqui tots ho fem :)");
    }

    public void notifyScore(int score) {
        TerminalController.getInstance().printLine("Enhorabona, has guanyat!");
        TerminalController.getInstance().printLine("La teva puntuació és: " + score);

    }
}
