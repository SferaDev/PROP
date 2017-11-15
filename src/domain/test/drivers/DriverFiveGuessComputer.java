package domain.test.drivers;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.computer.FiveGuessComputer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.TerminalController;
import presentation.model.TerminalInputOutput;
import presentation.utils.TerminalMenuBuilder;

import java.util.Random;

public class DriverFiveGuessComputer {
    private static final TerminalController terminalController = TerminalController.getInstance();
    private static Integer pegs;
    private static Integer colors;
    private static ColorRow correctGuess;
    private static TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();

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
        terminalMenuBuilder.addTitle("Menu DriverFiveGuessComputer:");
        terminalMenuBuilder.addOption("Executar n cops amb secretCode aleatori", DriverFiveGuessComputer::case1);
        terminalMenuBuilder.addOption("Executar amb un secret code introduit per teclat", DriverFiveGuessComputer::case2);
        terminalMenuBuilder.addOption("Sortir",terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();

    }

    private static void case2() {
        initializeGameInfo();
        TerminalInputOutput inputOutput = new TerminalInputOutput();
        int[] inputColors = new int[0];
        //Todo: No se de que son aquestes excepcions ni que fer amb elles
        try {
            inputColors = inputOutput.inputColorRow(pegs, colors);
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        correctGuess = new ColorRow(inputColors);
        boolean hasWin = executeOneGame(true);
        if (hasWin) terminalController.printLine("La execucio es correcte.");
        else terminalController.printLine("La execucio es INCORRECTE.");
    }

    private static void case1() {
        initializeGameInfo();
        terminalController.printLine("Introdueixi el numero de cops que vols executar l'algoritme:");
        Integer n = terminalController.readInteger();
        boolean allOK = true;
        for (int i = 1; i <= n; ++i) {
            correctGuess = randomRow(pegs, colors);
            boolean hasWin = executeOneGame(false);
            if (hasWin)
                terminalController.printLine("La execucio " + i + " es correcte, el SecretCode era " + correctGuess.toString() + ".");
            else {
                terminalController.printLine("La execucio " + i + " es INCORRECT, el SecretCode era " + correctGuess.toString() + ".");
                allOK = false;
            }
        }
        if (allOK) terminalController.printLine("**Totes les execucions son correctes.\n");
        else terminalController.printLine("**Hi han execucions incorrectes.\n");
    }

    private static boolean executeOneGame(boolean showGuess) {
        FiveGuessComputer fgc = new FiveGuessComputer(Player.Role.BREAKER);
        ColorRow guess = new ColorRow();
        boolean validTurn = true;
        boolean hasWin = false;
        Integer actualTurn = 0;
        Integer aux = 0;
        do {
            if (aux % 2 == 0) {
                guess = fgc.breakerGuess(pegs, colors);
                if (showGuess) terminalController.printLine(guess.toString());
                Integer maxturns = 12;
                if (actualTurn.equals(maxturns)) validTurn = false;
                else ++actualTurn;
            } else {
                ControlRow control = ComputerPlayer.compareGuess(correctGuess, guess);
                fgc.receiveControl(control);
                if (control.getBlacks() == pegs) hasWin = true;

            }
            ++aux;

        }
        while (!hasWin && validTurn);
        return hasWin;
    }

}
