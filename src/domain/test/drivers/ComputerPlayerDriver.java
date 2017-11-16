package domain.test.drivers;

import domain.model.Receiver;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

public class ComputerPlayerDriver {
    private static final TerminalUtils terminalUtils = TerminalUtils.getInstance();
    private static final Receiver terminalReceiver = new TerminalReceiver();
    private static Integer pegs;
    private static Integer colors;

    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Menu ComputerPlayerDriver:");
        terminalMenuBuilder.addOption("Probar comparacio entre dos ColorRows", ComputerPlayerDriver::case1);
        terminalMenuBuilder.addOption("Probar que crea correctament un ComputerPlayer", ComputerPlayerDriver::case2);
        terminalMenuBuilder.addOption("Demanar una GuessHelp", ComputerPlayerDriver::case3);
        terminalMenuBuilder.addOption("Sortir", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case3() {
        ComputerPlayer cp;
        initializeGameInfo();
        cp = ComputerPlayer.newComputerByName("Dummy", Player.Role.MAKER);
        ColorRow correctGuessHelp = new ColorRow(cp.makerGuess(pegs, colors));
        terminalUtils.printLine("El secretCode es " + correctGuessHelp.toString());
        int[] inputColorsHelp = new int[0];
        try {
            inputColorsHelp = terminalReceiver.inputColorRow(pegs, colors);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ColorRow attempt = new ColorRow(inputColorsHelp);
        ControlRow controlAttempt = cp.scoreGuess(attempt);
        terminalUtils.printLine("La correccio obtinguda es (Blacks, Whites): " + controlAttempt.getBlacks() + ", " + controlAttempt.getWhites() + ".\n");
        ColorRow guessHelp = ComputerPlayer.guessHelp(correctGuessHelp, controlAttempt, pegs, colors);
        ControlRow controlHelp = cp.scoreGuess(guessHelp);
        terminalUtils.printLine("L'algoritme ens recomana " + guessHelp.toString() + " que obté (Blacks, Whites): " + controlHelp.getBlacks() + ", " + controlHelp.getWhites() + ".\n");
    }


    private static void case2() {
        TerminalMenuBuilder submenu = new TerminalMenuBuilder();
        submenu.addTitle("Menu ComputerPlayerDriver Creation Test:");
        submenu.addOption("DummyComputer, with maker role", ComputerPlayerDriver::subcase21);
        submenu.addOption("DummyComputer, with breaker role", ComputerPlayerDriver::subcase22);
        submenu.addOption("FiveGuessComputer, with maker role", ComputerPlayerDriver::subcase23);
        submenu.addOption("FiveGuessComputer, with breaker role", ComputerPlayerDriver::subcase24);
        submenu.addOption("GeneticComputer, with maker role", ComputerPlayerDriver::subcase25);
        submenu.addOption("GeneticComputer, with breaker role", ComputerPlayerDriver::subcase26);
        submenu.addOption("Sortir", submenu::finishExecution);
        submenu.execute();

    }

    private static void subcase26() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("GeneticComputer", Player.Role.BREAKER);
        if (!cp.getName().equals("Genetic") || !cp.getPlayerRole().equals(Player.Role.BREAKER))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase25() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("GeneticComputer", Player.Role.MAKER);
        if (!cp.getName().equals("Genetic") || !cp.getPlayerRole().equals(Player.Role.MAKER))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase24() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("FiveGuessComputer", Player.Role.BREAKER);
        if (!cp.getName().equals("FiveGuess") || !cp.getPlayerRole().equals(Player.Role.BREAKER))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase23() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("FiveGuessComputer", Player.Role.MAKER);
        if (!cp.getName().equals("FiveGuess") || !cp.getPlayerRole().equals(Player.Role.MAKER))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase22() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("DummyComputer", Player.Role.BREAKER);
        if (!cp.getName().equals("Dummy") || !cp.getPlayerRole().equals(Player.Role.BREAKER))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase21() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("DummyComputer", Player.Role.MAKER);
        if (!cp.getName().equals("Dummy") || !cp.getPlayerRole().equals(Player.Role.MAKER))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void case1() {
        ComputerPlayer cp;
        initializeGameInfo();
        cp = ComputerPlayer.newComputerByName("Dummy", Player.Role.MAKER);
        ColorRow correctGuess = new ColorRow(cp.makerGuess(pegs, colors));
        terminalUtils.printLine("El secretCode es " + correctGuess.toString());
        //Todo: No se de que son aquestes excepcions ni que fer amb elles
        int[] inputColors1 = new int[0];
        try {
            inputColors1 = terminalReceiver.inputColorRow(pegs, colors);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ColorRow guess = new ColorRow(inputColors1);
        ControlRow control = cp.scoreGuess(guess);
        terminalUtils.printLine("La correccio obtinguda es (Blacks, Whites): " + control.getBlacks() + ", " + control.getWhites() + ".\n");
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

}