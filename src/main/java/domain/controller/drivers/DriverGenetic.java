package domain.controller.drivers;


import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.computer.GeneticComputer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.TerminalController;
import presentation.model.TerminalInputOutput;

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

    }
    public static void main (String args[]) throws FinishGameException, InterruptedException {
        terminalController.printLine("****GeneticComputer Drivers:*****\n");
        Integer option;
        do {
            option = printInitialMenu();
            switch (option){
                case 1:
                    initializeGameInfo();
                    terminalController.printLine("Introdueixi el numero de cops que vols executar l'algoritme:");
                    Integer n = terminalController.readInteger();
                    boolean allOK = true;
                    for (int i = 1; i <= n; ++i) {
                        correctGuess = randomRow(pegs, colors);
                        boolean showGuess = false;
                        boolean hasWin = executeOneGame(showGuess);
                        if (hasWin) terminalController.printLine("La execucio "+ i + " es correcte, el SecretCode era " + correctGuess.toString() + ".");
                        else  {
                            terminalController.printLine("La execucio "+ i + " es INCORRECT, el SecretCode era " + correctGuess.toString() + ".");
                            allOK = false;
                        }
                    }
                    if (allOK) terminalController.printLine("**Totes les execucions son correctes.\n");
                    else terminalController.printLine("**Hi han execucions incorrectes.\n");
                    break;

                case 2:
                    initializeGameInfo();
                    TerminalInputOutput inputOutput = new TerminalInputOutput();
                    int[] inputColors =  inputOutput.inputColorRow(pegs, colors);
                    correctGuess = new ColorRow(inputColors);
                    boolean showGuess = true;
                    boolean hasWin = executeOneGame(showGuess);
                    if (hasWin) terminalController.printLine("La execucio es correcte.");
                    else terminalController.printLine("La execucio es INCORRECTE.");
                    break;

                case 3:
                    break;

            }

        }
        while (option != 3);
        terminalController.printLine("Adeu!.\n");

    }

    private static boolean executeOneGame(boolean showGuess) {
        GeneticComputer gc = new GeneticComputer(Player.Role.BREAKER);
        ColorRow guess = new ColorRow();
        boolean validTurn = true;
        boolean hasWin = false;
        Integer actualTurn = 0;
        Integer aux = 0;
        do {
            if (aux % 2 == 0) {
                guess = gc.breakerGuess(pegs, colors);
                if (showGuess)terminalController.printLine(guess.toString());
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
        return hasWin;
    }

    private static Integer printInitialMenu() {
        Integer option;
        terminalController.printLine("Escolleixi una de les seguents opcions per probar el GeneticComputer:");
        terminalController.printLine("  1- Executar n cops amb secretCode aleatori");
        terminalController.printLine("  2- Executar amb un secret code introduit per teclat");
        terminalController.printLine("  3- Sortir");
        option = terminalController.readInteger();
        return option;
    }

}
