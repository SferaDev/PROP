package domain.test.drivers;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.computer.DummyComputer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.TerminalController;
import presentation.model.TerminalInputOutput;

import java.util.Random;

public class DriverDummyComputer {
    private static final TerminalController terminalController = TerminalController.getInstance();
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
            terminalController.printLine("Introdueixi el nombre de fitxes d'una combinació");
            pegs = terminalController.readInteger();
        } while (pegs == -1);

        do {
            terminalController.printLine("Introdueixi el nombre de colors possibles");
            colors = terminalController.readInteger();
        } while (colors == -1);

    }

    public static void main(String args[]) throws FinishGameException, CommandInterruptException {
        terminalController.printLine("****DummyComputer Drivers:*****\n");
        Integer option;
        TerminalInputOutput inputOutput = new TerminalInputOutput();
        do {
            option = printInitialMenu();
            switch (option) {
                case 1:
                    initializeGameInfo();
                    terminalController.printLine("Introdueixi el numero de cops que vols executar l'algoritme:");
                    Integer n = terminalController.readInteger();
                    Integer resoltes = 0;
                    Integer noResoltes = 0;
                    for (int i = 1; i <= n; ++i) {
                        correctGuess = randomRow(pegs, colors);
                        boolean hasWin = executeOneGame(false);
                        if (hasWin) {
                            ++resoltes;
                            terminalController.printLine("Ha resolt la execucio " + i + ", el SecretCode era " + correctGuess.toString() + ".");
                        } else {
                            ++noResoltes;
                            terminalController.printLine("No ha resolt la execucio " + i + ", el SecretCode era " + correctGuess.toString() + ".");
                        }
                    }
                    terminalController.printLine("\nHa resolt " + resoltes + " jocs, i no ha pogut resoldre " + noResoltes + "\n");

                    break;

                case 2:
                    initializeGameInfo();
                    int[] inputColors = inputOutput.inputColorRow(pegs, colors);
                    correctGuess = new ColorRow(inputColors);
                    boolean hasWin = executeOneGame(true);
                    if (hasWin) terminalController.printLine("L'algoritme ha resolt el joc.");
                    else terminalController.printLine("L'algoritme no ha resolt el joc.");
                    break;

                case 3:
                    initializeGameInfo();
                    DummyComputer dc = new DummyComputer(Player.Role.MAKER);
                    correctGuess = dc.makerGuess(pegs, colors);
                    terminalController.printLine("El secretCode es " + correctGuess.toString());

                    int[] inputColors1 = inputOutput.inputColorRow(pegs, colors);
                    ColorRow guess = new ColorRow(inputColors1);
                    ControlRow control = dc.scoreGuess(guess);
                    terminalController.printLine("La correccio obtinguda es (Blacks, Whites): " + control.getBlacks() + ", " + control.getWhites() + ".\n");
                    break;

                case 4:
                    break;

            }

        }
        while (option != 4);
        terminalController.printLine("Adeu!.\n");

    }

    private static boolean executeOneGame(boolean showGuess) {
        DummyComputer dc = new DummyComputer(Player.Role.BREAKER);
        ColorRow guess = new ColorRow();
        boolean validTurn = true;
        boolean hasWon = false;
        Integer actualTurn = 0;
        Integer aux = 0;
        do {
            if (aux % 2 == 0) {
                guess = dc.breakerGuess(pegs, colors);
                if (showGuess) terminalController.printLine(guess.toString());
                Integer maxturns = 12;
                if (actualTurn.equals(maxturns)) validTurn = false;
                else ++actualTurn;
            } else {
                ControlRow control = ComputerPlayer.compareGuess(correctGuess, guess);
                //dc.receiveControl(control);
                if (control.getBlacks() == pegs) hasWon = true;

            }
            ++aux;

        }
        while (!hasWon && validTurn);
        return hasWon;
    }

    private static Integer printInitialMenu() {
        Integer option;
        terminalController.printLine("Escolleixi una de les seguents opcions per probar el DummyComputer:");
        terminalController.printLine("  1- Executar n cops amb secretCode aleatori");
        terminalController.printLine("  2- Executar amb un secret code introduit per teclat");
        terminalController.printLine("  3- Probar correccio");
        terminalController.printLine("  4- Sortir");
        option = terminalController.readInteger();
        return option;
    }

}
