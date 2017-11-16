package domain.test.drivers;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.TerminalUtils;

public class DriverComputerPlayer {
    private static final TerminalUtils terminalUtils = TerminalUtils.getInstance();
    private static final TerminalReceiver inputOutput = new TerminalReceiver();
    private static Integer pegs;
    private static Integer colors;


    public static void main(String args[]) throws FinishGameException, CommandInterruptException {
        terminalUtils.printLine("****ComputerPlayer Drivers:*****\n");
        Integer option;
        ComputerPlayer cp;
        do {
            option = printInitialMenu();
            switch (option) {
                case 1:
                    initializeGameInfo();
                    cp = ComputerPlayer.newComputerByName("Dummy", Player.Role.MAKER);
                    ColorRow correctGuess = new ColorRow(cp.makerGuess(pegs, colors));
                    terminalUtils.printLine("El secretCode es " + correctGuess.toString());

                    int[] inputColors1 = inputOutput.inputColorRow(pegs, colors);
                    ColorRow guess = new ColorRow(inputColors1);
                    ControlRow control = cp.scoreGuess(guess);
                    terminalUtils.printLine("La correccio obtinguda es (Blacks, Whites): " + control.getBlacks() + ", " + control.getWhites() + ".\n");
                    break;
                case 2:
                    Integer suboption;
                    do {
                        suboption = printSubmenuCreation();
                        switch (suboption) {
                            case 1:
                                cp = ComputerPlayer.newComputerByName("DummyComputer", Player.Role.MAKER);
                                if (!cp.getName().equals("Dummy") || !cp.getPlayerRole().equals(Player.Role.MAKER))
                                    terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                else
                                    terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                break;
                            case 2:
                                cp = ComputerPlayer.newComputerByName("DummyComputer", Player.Role.BREAKER);
                                if (!cp.getName().equals("Dummy") || !cp.getPlayerRole().equals(Player.Role.BREAKER))
                                    terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                else
                                    terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                break;
                            case 3:
                                cp = ComputerPlayer.newComputerByName("FiveGuessComputer", Player.Role.MAKER);
                                if (!cp.getName().equals("FiveGuess") || !cp.getPlayerRole().equals(Player.Role.MAKER))
                                    terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                else
                                    terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                break;
                            case 4:
                                cp = ComputerPlayer.newComputerByName("FiveGuessComputer", Player.Role.BREAKER);
                                if (!cp.getName().equals("FiveGuess") || !cp.getPlayerRole().equals(Player.Role.BREAKER))
                                    terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                else
                                    terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                break;
                            case 5:
                                cp = ComputerPlayer.newComputerByName("GeneticComputer", Player.Role.MAKER);
                                if (!cp.getName().equals("Genetic") || !cp.getPlayerRole().equals(Player.Role.MAKER))
                                    terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                else
                                    terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                break;
                            case 6:
                                cp = ComputerPlayer.newComputerByName("GeneticComputer", Player.Role.BREAKER);
                                if (!cp.getName().equals("Genetic") || !cp.getPlayerRole().equals(Player.Role.BREAKER))
                                    terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                else
                                    terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
                                break;
                            case 7:
                                break;
                        }

                    } while (suboption != 7);
                    break;

                case 3:
                    initializeGameInfo();
                    cp = ComputerPlayer.newComputerByName("Dummy", Player.Role.MAKER);
                    ColorRow correctGuessHelp = new ColorRow(cp.makerGuess(pegs, colors));
                    terminalUtils.printLine("El secretCode es " + correctGuessHelp.toString());

                    int[] inputColorsHelp = inputOutput.inputColorRow(pegs, colors);
                    ColorRow attempt = new ColorRow(inputColorsHelp);
                    ControlRow controlAttempt = cp.scoreGuess(attempt);
                    terminalUtils.printLine("La correccio obtinguda es (Blacks, Whites): " + controlAttempt.getBlacks() + ", " + controlAttempt.getWhites() + ".\n");
                    ColorRow guessHelp = ComputerPlayer.guessHelp(correctGuessHelp, controlAttempt, pegs, colors);
                    ControlRow controlHelp = cp.scoreGuess(guessHelp);
                    terminalUtils.printLine("L'algoritme ens recomana " + guessHelp.toString() + " que obté (Blacks, Whites): " + controlHelp.getBlacks() + ", " + controlHelp.getWhites() + ".\n");
                    break;

                case 4:
                    break;
            }

        } while (option != 4);

    }

    private static Integer printInitialMenu() {
        Integer option;
        terminalUtils.printLine("Escolleixi una de les seguents opcions per probar la classe ComputerPlayer:");
        terminalUtils.printLine("  1- Probar comparacio entre dos ColorRows");
        terminalUtils.printLine("  2- Probar que crea correctament un ComputerPlayer");
        terminalUtils.printLine("  3- Demanar una GuessHelp");
        terminalUtils.printLine("  3- Sortir");
        option = terminalUtils.readInteger();
        return option;
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

    private static Integer printSubmenuCreation() {
        Integer option;
        terminalUtils.printLine("Escolleixi el Computer a crear:");
        terminalUtils.printLine("  1- DummyComputer, with maker role");
        terminalUtils.printLine("  2- DummyComputer, with breaker role");
        terminalUtils.printLine("  3- FiveGuessComputer, with maker role");
        terminalUtils.printLine("  4- FiveGuessComputer, with breaker role");
        terminalUtils.printLine("  5- GeneticComputer, with maker role");
        terminalUtils.printLine("  6- GeneticComputer, with breaker role");
        terminalUtils.printLine("  7- Sortir");
        option = terminalUtils.readInteger();
        return option;
    }

}
