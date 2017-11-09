package presentation.model;

import domain.controller.InputOutput;
import presentation.utils.TerminalUtils;

import java.util.Scanner;

// TODO: EXIT and SAVE commands

public class TerminalInputOutput implements InputOutput {
    @Override
    public int inputControlBlacks(int pegs) {
        TerminalUtils.println("Introdueixi el numero de Negres");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    @Override
    public int inputControlWhites(int pegs) {
        TerminalUtils.println("Introdueixi el numero de Blanques");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    @Override
    public int[] inputColorRow(int pegs, int colors) {
        int[] result = new int[pegs];
        TerminalUtils.println("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors [1 2 2 1]");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < pegs; ++i) {
            result[i] = scanner.nextInt();
        }
        return result;
    }

    @Override
    public void outputControlRow(int blacks, int whites) {
        TerminalUtils.println("Negres: " + blacks + " | Blanques: " + whites);
    }

    @Override
    public void outputColorRow(String row) {
        TerminalUtils.println(row);
    }

    @Override
    public void outputMessage(String message) {
        TerminalUtils.println(message);
    }

    @Override
    public void notifyInvalidInput() {
        TerminalUtils.println("Això és mentida! Tranqui tots ho fem :)");
    }

    public void notifyScore(int score) {
        TerminalUtils.println("Enhorabona, has guanyat!");
        TerminalUtils.println("La teva puntuació és: " + score);

    }
}
