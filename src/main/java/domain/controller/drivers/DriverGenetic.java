package domain.controller.drivers;


import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.computer.GeneticComputer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.TerminalController;

import java.util.Random;


public class DriverGenetic {
    private static TerminalController terminalController = TerminalController.getInstance();
    private static Integer pegs;
    private static Integer colors;
    private static Integer maxturns = 12;
    private static ColorRow correctGuess;

    private static ColorRow randomRow(int pegs, int colors) {
        ColorRow row = new ColorRow();
        Random rand = new Random();
        for (int i = 0; i < pegs; ++i) {
            row.add(new ColorRow.ColorPeg(rand.nextInt(colors) + 1));
        }
        return row;
    }

    private static void initializeGameInfo() {
        do {
            terminalController.printLine("Introdueixi el nombre de fitxes d'una combinació");
            pegs = terminalController.readInteger();
        } while (pegs == -1);

        do {
            terminalController.printLine("Introdueixi el nombre de colors possibles");
            colors = terminalController.readInteger();
        } while (colors == -1);

        correctGuess = randomRow(pegs, colors);

    }
    public static void main (String args[]) {

        terminalController.printLine("Starting GeneticComputer Drivers:\n");
        initializeGameInfo();
        GeneticComputer gc = new GeneticComputer(Player.Role.BREAKER);
        ColorRow guess = new ColorRow();
        boolean validTurn = true;
        boolean hasWin = false;
        Integer actualTurn = 0;
        Integer aux = 0;

        do {
            if (aux % 2 == 0) {
                guess = gc.breakerGuess(pegs, colors);
                terminalController.printLine(guess.toString());
                if (actualTurn.equals(maxturns)) validTurn = false;
                else ++actualTurn;
            }
            else {
                ControlRow control = ComputerPlayer.compareGuess(correctGuess, guess);
                gc.receiveControl(control);
                if (control.getBlacks() == pegs) hasWin = true;

            }
            ++aux;

        }
        while (!hasWin && validTurn);
        if (hasWin) terminalController.printLine("GeneticComputer Won\n");
        else terminalController.printLine("You won\n");
    }
}
