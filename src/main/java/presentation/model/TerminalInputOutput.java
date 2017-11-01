package presentation.model;

import domain.controller.InputOutput;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.utils.TerminalUtils;

import java.util.Scanner;

// TODO: EXIT and SAVE commands

public class TerminalInputOutput implements InputOutput {
    @Override
    public ControlRow inputControlRow(int pegs) {
        TerminalUtils.println("Introdueixi el numero de Negres i Blanques [3 1]");
        Scanner scanner = new Scanner(System.in);

        return new ControlRow(scanner.nextInt(), scanner.nextInt());
    }

    @Override
    public ColorRow inputColorRow(int pegs, int colors) {
        ColorRow result = new ColorRow();
        TerminalUtils.println("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors [1 2 2 1]");
        Scanner scanner = new Scanner(System.in);
        while (result.size() < pegs) {
            result.add(new ColorRow.ColorPeg(scanner.nextInt()));
        }
        return result;
    }

    @Override
    public void outputControlRow(ControlRow row) {
        TerminalUtils.println("Blacks: " + row.getBlacks() + " | Whites: " + row.getWhites());
    }

    @Override
    public void outputColorRow(ColorRow row) {
        StringBuilder output = new StringBuilder();
        for (ColorRow.ColorPeg peg : row) {
            output.append(peg.getColor()).append(" ");
        }
        TerminalUtils.println(output.toString());
    }

    @Override
    public void outputMessage(String message) {
        TerminalUtils.println(message);
    }
}
