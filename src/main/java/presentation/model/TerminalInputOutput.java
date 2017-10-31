package presentation.model;

import domain.controller.InputOutput;
import domain.controller.MainController;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import presentation.utils.TerminalUtils;

import java.util.Scanner;

public class TerminalInputOutput implements InputOutput {
    @Override
    public Row<ControlPeg> inputControlRow(int pegs) {
        Row<ControlPeg> result = new Row<>();
        TerminalUtils.println("Introdueixi combinació de " + pegs + " fitxes de control [B B W -]");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        // TODO: Improve
        if (input.equals("exit") || input.equals("quit")) {
            //currentGame.endGame();
        }
        if (input.equals("save")) {
            MainController.getInstance().saveCurrentGame();
            TerminalUtils.println("Saving game!");
        }
        input.replace(" ", "");
        for (int i = 0; i < pegs; ++i) {
            if (i < input.length() && input.charAt(i) == 'B') result.add(new ControlPeg(ControlPeg.Type.BLACK));
            else if (i < input.length() && input.charAt(i) == 'W')
                result.add(new ControlPeg(ControlPeg.Type.WHITE));
            else result.add(new ControlPeg(ControlPeg.Type.EMPTY));
        }
        return result;
    }

    @Override
    public Row<ColorPeg> inputColorRow(int pegs, int colors) {
        Row<ColorPeg> result = new Row<>();
        TerminalUtils.println("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors [1 2 2 1]");
        Scanner scanner = new Scanner(System.in);
        while (result.size() < pegs) {
            String input = scanner.next();
            // TODO: Improve
            if (input.equals("exit") || input.equals("quit")) {
                //currentGame.endGame();
            }
            if (input.equals("save")) {
                MainController.getInstance().saveCurrentGame();
                TerminalUtils.println("Saving game!");
            }
            input.replaceAll("[^1-" + colors + "]", "");
            for (int i = 0; i < input.length(); i++) {
                result.add(new ColorPeg(Integer.parseInt(String.valueOf(input.charAt(i)))));
            }
        }
        return result;
    }

    @Override
    public void outputControlRow(Row<ControlPeg> row) {
        StringBuilder output = new StringBuilder();
        for (ControlPeg peg : row) {
            if (peg.getType() == ControlPeg.Type.BLACK) output.append("B ");
            if (peg.getType() == ControlPeg.Type.WHITE) output.append("W ");
            if (peg.getType() == ControlPeg.Type.EMPTY) output.append("- ");
        }
        TerminalUtils.println(output.toString());
    }

    @Override
    public void outputColorRow(Row<ColorPeg> row) {
        StringBuilder output = new StringBuilder();
        for (ColorPeg peg : row) {
            output.append(peg.getColor()).append(" ");
        }
        TerminalUtils.println(output.toString());
    }
}
