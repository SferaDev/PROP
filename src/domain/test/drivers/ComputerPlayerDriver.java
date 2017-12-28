package domain.test.drivers;

import domain.controller.DomainController;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.terminal.utils.TerminalMenuBuilder;
import presentation.terminal.utils.TerminalUtils;

/**
 * The type Computer player driver.
 *
 * @author Oriol Borrell Roig
 */
public class ComputerPlayerDriver {
    private static final TerminalUtils terminalUtils = TerminalUtils.getInstance();
    private static int pegs, colors;

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Mastermind: ComputerPlayerDriver");
        terminalMenuBuilder.addOption("Provar creació correcta d'un ComputerPlayer", ComputerPlayerDriver::testComputerCreation);
        terminalMenuBuilder.addOption("Provar comparació entre dos ColorRows", ComputerPlayerDriver::testCompare);
        terminalMenuBuilder.addOption("Demanar una Ajuda de ColorRow", ComputerPlayerDriver::testColorRowHelp);
        terminalMenuBuilder.addOption("Enrere", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void testColorRowHelp() {
        ComputerPlayer cp;
        initializeGameInfo();
        cp = ComputerPlayer.newComputerByName("Dummy", Player.Role.Maker);
        ColorRow correctGuessHelp = new ColorRow(cp.makerGuess(pegs, colors));
        terminalUtils.printLine("El secretCode es " + correctGuessHelp.toString());
        int[] inputColorsHelp = new int[0];
        try {
            inputColorsHelp = DomainController.getInstance().getGameInterface().inputColorRow(pegs, colors);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ColorRow attempt = new ColorRow(inputColorsHelp);
        ControlRow controlAttempt = cp.scoreGuess(attempt);
        terminalUtils.printLine("La correccio obtinguda es (Blacks, Whites): " + controlAttempt.getBlacks() + ", " + controlAttempt.getWhites() + ".\n");
        ColorRow guessHelp = ComputerPlayer.guessHelp(correctGuessHelp, controlAttempt, pegs, colors);
        ControlRow controlHelp = cp.scoreGuess(guessHelp);
        terminalUtils.printLine("L'algoritme ens recomana " + guessHelp.toString() + " que obté (Blacks, Whites): " + controlHelp.getBlacks() + ", " + controlHelp.getWhites() + ".\n");
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testComputerCreation() {
        TerminalMenuBuilder submenu = new TerminalMenuBuilder();
        submenu.addTitle("Mastermind: Creació ComputerPlayerDriver");
        submenu.addOption("DummyComputer com a Maker", ComputerPlayerDriver::testDummyMaker);
        submenu.addOption("DummyComputer com a Breaker", ComputerPlayerDriver::testDummyBreaker);
        submenu.addOption("FiveGuessComputer com a Maker", ComputerPlayerDriver::testFiveMaker);
        submenu.addOption("FiveGuessComputer com a Breaker", ComputerPlayerDriver::testFiveBreaker);
        submenu.addOption("GeneticComputer com a Maker", ComputerPlayerDriver::testGeneticMaker);
        submenu.addOption("GeneticComputer com a Breaker", ComputerPlayerDriver::testGeneticBreaker);
        submenu.addOption("Enrere", submenu::finishExecution);
        submenu.execute();
    }

    private static void testGeneticBreaker() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("GeneticComputer", Player.Role.Breaker);
        if (!cp.getName().equals("Genetic") || !cp.getPlayerRole().equals(Player.Role.Breaker))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testGeneticMaker() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("GeneticComputer", Player.Role.Maker);
        if (!cp.getName().equals("Genetic") || !cp.getPlayerRole().equals(Player.Role.Maker))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testFiveBreaker() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("FiveGuessComputer", Player.Role.Breaker);
        if (!cp.getName().equals("FiveGuess") || !cp.getPlayerRole().equals(Player.Role.Breaker))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testFiveMaker() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("FiveGuessComputer", Player.Role.Maker);
        if (!cp.getName().equals("FiveGuess") || !cp.getPlayerRole().equals(Player.Role.Maker))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testDummyBreaker() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("DummyComputer", Player.Role.Breaker);
        if (!cp.getName().equals("Dummy") || !cp.getPlayerRole().equals(Player.Role.Breaker))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testDummyMaker() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("DummyComputer", Player.Role.Maker);
        if (!cp.getName().equals("Dummy") || !cp.getPlayerRole().equals(Player.Role.Maker))
            terminalUtils.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalUtils.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testCompare() {
        ComputerPlayer cp;
        initializeGameInfo();
        cp = ComputerPlayer.newComputerByName("Dummy", Player.Role.Maker);
        ColorRow correctGuess = new ColorRow(cp.makerGuess(pegs, colors));
        terminalUtils.printLine("El secretCode es " + correctGuess.toString());
        int[] inputColors1 = new int[0];
        try {
            inputColors1 = DomainController.getInstance().getGameInterface().inputColorRow(pegs, colors);
        } catch (FinishGameException | CommandInterruptException ignored) {
        }
        ColorRow guess = new ColorRow(inputColors1);
        ControlRow control = cp.scoreGuess(guess);
        terminalUtils.printLine("La correccio obtinguda es (Blacks, Whites): " + control.getBlacks() + ", " + control.getWhites() + ".\n");
        TerminalUtils.getInstance().pressEnterToContinue();
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