package domain.test.drivers;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.TerminalController;
import presentation.model.TerminalInputOutput;
import presentation.utils.TerminalMenuBuilder;

public class DriverComputerPlayer {
    private static TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
    private static final TerminalController terminalController = TerminalController.getInstance();
    private static final TerminalInputOutput inputOutput = new TerminalInputOutput();
    private static Integer pegs;
    private static Integer colors;



    public static void main(String args[]) throws FinishGameException, CommandInterruptException {
        terminalMenuBuilder.addTitle("Menu DriverComputerPlayer:");
        terminalMenuBuilder.addOption("Probar comparacio entre dos ColorRows", DriverComputerPlayer::case1);
        terminalMenuBuilder.addOption("Probar que crea correctament un ComputerPlayer", DriverComputerPlayer::case2);
        terminalMenuBuilder.addOption("Demanar una GuessHelp", DriverComputerPlayer::case3);
        terminalMenuBuilder.addOption("Sortir",terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();


    }

    private static void case3() {
        ComputerPlayer cp;
        initializeGameInfo();
        cp = ComputerPlayer.newComputerByName("Dummy", Player.Role.MAKER);
        ColorRow correctGuessHelp = new ColorRow(cp.makerGuess(pegs, colors));
        terminalController.printLine("El secretCode es " + correctGuessHelp.toString());
        //Todo: No se de que son aquestes excepcions ni que fer amb elles
        int[] inputColorsHelp = new int[0];
        try {
            inputColorsHelp = inputOutput.inputColorRow(pegs, colors);
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        ColorRow attempt = new ColorRow(inputColorsHelp);
        ControlRow controlAttempt = cp.scoreGuess(attempt);
        terminalController.printLine("La correccio obtinguda es (Blacks, Whites): " + controlAttempt.getBlacks() + ", " + controlAttempt.getWhites() + ".\n");
        ColorRow guessHelp = ComputerPlayer.guessHelp(correctGuessHelp, controlAttempt, pegs, colors);
        ControlRow controlHelp = cp.scoreGuess(guessHelp);
        terminalController.printLine("L'algoritme ens recomana " + guessHelp.toString() + " que obté (Blacks, Whites): " + controlHelp.getBlacks() + ", " + controlHelp.getWhites() + ".\n");
    }


    private static void case2() {
        TerminalMenuBuilder submenu = new TerminalMenuBuilder();
        submenu.addTitle("Menu DriverComputerPlayer Creation Test:");
        submenu.addOption("DummyComputer, with maker role", DriverComputerPlayer::subcase21);
        submenu.addOption("DummyComputer, with breaker role", DriverComputerPlayer::subcase22);
        submenu.addOption("FiveGuessComputer, with maker role", DriverComputerPlayer::subcase23);
        submenu.addOption("FiveGuessComputer, with breaker role", DriverComputerPlayer::subcase24);
        submenu.addOption("GeneticComputer, with maker role", DriverComputerPlayer::subcase25);
        submenu.addOption("GeneticComputer, with breaker role", DriverComputerPlayer::subcase26);
        submenu.addOption("Sortir",submenu::finishExecution);
        submenu.execute();

    }

    private static void subcase26() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("GeneticComputer", Player.Role.BREAKER);
        if (!cp.getName().equals("Genetic") || !cp.getPlayerRole().equals(Player.Role.BREAKER))
            terminalController.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalController.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase25() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("GeneticComputer", Player.Role.MAKER);
        if (!cp.getName().equals("Genetic") || !cp.getPlayerRole().equals(Player.Role.MAKER))
            terminalController.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalController.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase24() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("FiveGuessComputer", Player.Role.BREAKER);
        if (!cp.getName().equals("FiveGuess") || !cp.getPlayerRole().equals(Player.Role.BREAKER))
            terminalController.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalController.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase23() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("FiveGuessComputer", Player.Role.MAKER);
        if (!cp.getName().equals("FiveGuess") || !cp.getPlayerRole().equals(Player.Role.MAKER))
            terminalController.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalController.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase22() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("DummyComputer", Player.Role.BREAKER);
        if (!cp.getName().equals("Dummy") || !cp.getPlayerRole().equals(Player.Role.BREAKER))
            terminalController.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalController.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void subcase21() {
        ComputerPlayer cp;
        cp = ComputerPlayer.newComputerByName("DummyComputer", Player.Role.MAKER);
        if (!cp.getName().equals("Dummy") || !cp.getPlayerRole().equals(Player.Role.MAKER))
            terminalController.printLine("The creation is incorrect (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
        else
            terminalController.printLine("The creation is correct (name, role): " + cp.getName() + ", " + cp.getPlayerRole().toString() + "\n");
    }

    private static void case1(){
        ComputerPlayer cp;
        initializeGameInfo();
        cp = ComputerPlayer.newComputerByName("Dummy", Player.Role.MAKER);
        ColorRow correctGuess = new ColorRow(cp.makerGuess(pegs, colors));
        terminalController.printLine("El secretCode es " + correctGuess.toString());
        //Todo: No se de que son aquestes excepcions ni que fer amb elles
        int[] inputColors1 = new int[0];
        try {
            inputColors1 = inputOutput.inputColorRow(pegs, colors);
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        ColorRow guess = new ColorRow(inputColors1);
        ControlRow control = cp.scoreGuess(guess);
        terminalController.printLine("La correccio obtinguda es (Blacks, Whites): " + control.getBlacks() + ", " + control.getWhites() + ".\n");
        return;
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

}