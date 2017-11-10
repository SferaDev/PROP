package presentation.model;

import domain.InputOutput;
import presentation.utils.TerminalUtils;

import java.util.Scanner;

/**
 * The type Terminal input output.
 */
public class TerminalInputOutput implements InputOutput {

    @Override
    public int inputControlBlacks(int pegs) {
        TerminalUtils.printLine("Introdueixi el numero de Negres");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    @Override
    public int inputControlWhites(int pegs) {
        TerminalUtils.printLine("Introdueixi el numero de Blanques");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    @Override
    public int[] inputColorRow(int pegs, int colors) {
        int[] result = new int[pegs];
        TerminalUtils.printLine("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors [1 2 2 1]");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < pegs; ++i) {
            result[i] = scanner.nextInt();
        }
        return result;
    }

    @Override
    public void outputControlRow(int blacks, int whites) {
        TerminalUtils.printLine("Negres: " + blacks + " | Blanques: " + whites);
    }

    @Override
    public void outputColorRow(String row) {
        TerminalUtils.printLine(row);
    }

    @Override
    public void outputMessage(String message) {
        TerminalUtils.printLine(message);
    }

    @Override
    public void notifyInvalidInput() {
        TerminalUtils.printLine("Això és mentida! Tranqui tots ho fem :)");
    }

    public void notifyScore(int score) {
        TerminalUtils.printLine("Enhorabona, has guanyat!");
        TerminalUtils.printLine("La teva puntuació és: " + score);

    }
}
