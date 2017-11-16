package domain.test.drivers;


import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.computer.GeneticComputer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.TerminalUtils;

import java.util.Random;


public class DriverGeneticComputer {
    private static final TerminalUtils terminalUtils = TerminalUtils.getInstance();
    private static Integer pegs;
    private static Integer colors;
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
            terminalUtils.printLine("Introdueixi el nombre de fitxes d'una combinació");
            pegs = terminalUtils.readInteger();
        } while (pegs == -1);

        do {
            terminalUtils.printLine("Introdueixi el nombre de colors possibles");
            colors = terminalUtils.readInteger();
        } while (colors == -1);

    }

    public static void main(String args[]) throws FinishGameException, CommandInterruptException {
        terminalUtils.printLine("****GeneticComputer Drivers:*****\n");
        Integer option;
        do {
            option = printInitialMenu();
            switch (option) {
                case 1:
                    initializeGameInfo();
                    terminalUtils.printLine("Introdueixi el numero de cops que vols executar l'algoritme:");
                    Integer n = terminalUtils.readInteger();
                    boolean allOK = true;
                    for (int i = 1; i <= n; ++i) {
                        correctGuess = randomRow(pegs, colors);
                        boolean hasWin = executeOneGame(false);
                        if (hasWin)
                            terminalUtils.printLine("La execucio " + i + " es correcte, el SecretCode era " + correctGuess.toString() + ".");
                        else {
                            terminalUtils.printLine("La execucio " + i + " es INCORRECT, el SecretCode era " + correctGuess.toString() + ".");
                            allOK = false;
                        }
                    }
                    if (allOK) terminalUtils.printLine("**Totes les execucions son correctes.\n");
                    else terminalUtils.printLine("**Hi han execucions incorrectes.\n");
                    break;

                case 2:
                    initializeGameInfo();
                    TerminalReceiver inputOutput = new TerminalReceiver();
                    int[] inputColors = inputOutput.inputColorRow(pegs, colors);
                    correctGuess = new ColorRow(inputColors);
                    boolean hasWin = executeOneGame(true);
                    if (hasWin) terminalUtils.printLine("La execucio es correcte.");
                    else terminalUtils.printLine("La execucio es INCORRECTE.");
                    break;

                case 3:
                    break;

            }

        }
        while (option != 3);
        terminalUtils.printLine("Adeu!.\n");

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
                if (showGuess) terminalUtils.printLine(guess.toString());
                Integer maxturns = 12;
                if (actualTurn.equals(maxturns)) validTurn = false;
                else ++actualTurn;
            } else {
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
        terminalUtils.printLine("Escolleixi una de les seguents opcions per probar el GeneticComputer:");
        terminalUtils.printLine("  1- Executar n cops amb secretCode aleatori");
        terminalUtils.printLine("  2- Executar amb un secret code introduit per teclat");
        terminalUtils.printLine("  3- Sortir");
        option = terminalUtils.readInteger();
        return option;
    }

}
