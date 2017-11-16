package domain.test.drivers;

import domain.controller.DomainController;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.computer.DummyComputer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

import java.util.Random;

public class DummyComputerDriver {
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
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Menu DummyComputerDriver:");
        terminalMenuBuilder.addOption("Executar n cops amb secretCode aleatori", DummyComputerDriver::case1);
        terminalMenuBuilder.addOption("Executar amb un secret code introduit per teclat", DummyComputerDriver::case2);
        terminalMenuBuilder.addOption("Probar correccio", DummyComputerDriver::case3);
        terminalMenuBuilder.addOption("Sortir", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case3() {
        initializeGameInfo();
        DummyComputer dc = new DummyComputer(Player.Role.MAKER);
        correctGuess = dc.makerGuess(pegs, colors);
        terminalUtils.printLine("El secretCode es " + correctGuess.toString());

        int[] inputColors = new int[0];
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalReceiver());
        try {
            inputColors = domainController.getGameInterface().inputColorRow(pegs, colors);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ColorRow guess = new ColorRow(inputColors);
        ControlRow control = dc.scoreGuess(guess);
        terminalUtils.printLine("La correccio obtinguda es (Blacks, Whites): " + control.getBlacks() + ", " + control.getWhites() + ".\n");
    }

    private static void case2() {
        initializeGameInfo();
        int[] inputColors = new int[0];
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalReceiver());
        try {
            inputColors = domainController.getGameInterface().inputColorRow(pegs, colors);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        correctGuess = new ColorRow(inputColors);
        boolean hasWin = executeOneGame(true);
        if (hasWin) terminalUtils.printLine("L'algoritme ha resolt el joc.");
        else terminalUtils.printLine("L'algoritme no ha resolt el joc.");
    }

    private static void case1() {
        initializeGameInfo();
        terminalUtils.printLine("Introdueixi el numero de cops que vols executar l'algoritme:");
        Integer n = terminalUtils.readInteger();
        Integer resoltes = 0;
        Integer noResoltes = 0;
        for (int i = 1; i <= n; ++i) {
            correctGuess = randomRow(pegs, colors);
            boolean hasWin = executeOneGame(false);
            if (hasWin) {
                ++resoltes;
                terminalUtils.printLine("Ha resolt la execucio " + i + ", el SecretCode era " + correctGuess.toString() + ".");
            } else {
                ++noResoltes;
                terminalUtils.printLine("No ha resolt la execucio " + i + ", el SecretCode era " + correctGuess.toString() + ".");
            }
        }
        terminalUtils.printLine("\nHa resolt " + resoltes + " jocs, i no ha pogut resoldre " + noResoltes + "\n");
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
                if (showGuess) terminalUtils.printLine(guess.toString());
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

}