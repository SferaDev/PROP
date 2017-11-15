package presentation.model;

import domain.InputOutput;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import presentation.controller.TerminalController;

/**
 * The type Terminal input output.
 */
public class TerminalInputOutput implements InputOutput {

    @Override
    public int inputControlBlacks() throws FinishGameException, CommandInterruptException {
        TerminalController.getInstance().printLine("Introdueixi el numero de Negres");
        return TerminalController.getInstance().readGameInteger();
    }

    @Override
    public int inputControlWhites() throws FinishGameException, CommandInterruptException {
        TerminalController.getInstance().printLine("Introdueixi el numero de Blanques");
        return TerminalController.getInstance().readGameInteger();
    }

    @Override
    public int[] inputColorRow(int pegs, int colors) throws FinishGameException, CommandInterruptException {
        int[] result = new int[pegs];
        TerminalController.getInstance().printLine("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors");
        for (int i = 0; i < pegs; ++i) {
            result[i] = TerminalController.getInstance().readGameInteger();
        }
        return result;
    }

    @Override
    public void outputControlRow(int blacks, int whites) {
        outputMessage("Aquest és el meu control:");
        outputRow(blacks, whites);
    }

    @Override
    public void outputColorRow(String row) {
        outputMessage("Jo crec que és aquesta combinació:");
        outputRow(row);
    }

    @Override
    public void outputHintControlRow(int blacks, int whites) {
        outputMessage("Aquesta es la teva ajuda:");
        outputRow(blacks, whites);
    }

    @Override
    public void outputHintColorRow(String row) {
        outputMessage("Aquesta es la teva ajuda:");
        outputRow(row);
    }

    private void outputRow(int blacks, int whites) {
        TerminalController.getInstance().printLine("Negres: " + blacks + " | Blanques: " + whites);
    }

    private void outputRow(String row) {
        TerminalController.getInstance().printLine(row);
    }

    @Override
    public void outputMessage(String message) {
        TerminalController.getInstance().printLine(message);
    }

    @Override
    public void notifyInvalidInput() {
        TerminalController.getInstance().printLine("Entrada invalida!");
    }

    public void notifyScore(int score) {
        TerminalController.getInstance().printLine("Enhorabona, has guanyat!");
        TerminalController.getInstance().printLine("La teva puntuació és: " + score);

    }

    @Override
    public void notifyInvalidControl() {
        TerminalController.getInstance().printLine("Això és mentida! Tranqui tots ho fem :)");
    }
}
