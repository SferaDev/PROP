package domain.test.drivers;

import domain.controller.DomainController;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

public class UserPlayerDriver {
    private static final TerminalUtils terminalUtils = TerminalUtils.getInstance();
    private static Integer pegs;
    private static Integer colors;

    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Menu UserPlayerDriver:");
        terminalMenuBuilder.addOption("Crear un makerGuess", UserPlayerDriver::case1);
        terminalMenuBuilder.addOption("Crear un breakerGuess", UserPlayerDriver::case2);
        terminalMenuBuilder.addOption("Obtenir control d'un guess", UserPlayerDriver::case3);
        terminalMenuBuilder.addOption("Sortir", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case3() {
        pegs = 4;
        colors = 6;
        UserPlayer up = new UserPlayer("testUser", Player.Role.MAKER);
        ColorRow c = new ColorRow(1, 1, 1, 1);
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalReceiver());

        ControlRow control = new ControlRow(0, 0);
        try {
            control = up.scoreGuess(c);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        up.receiveControl(control);
        // terminalUtils.printLine("S'ha obtingut la correcció (blacks, whites): " + control.getBlacks() + ", " + control.getWhites());

    }

    private static void case2() {
        initializeGameInfo();
        UserPlayer up = new UserPlayer("testUser", Player.Role.MAKER);
        ColorRow c = new ColorRow();
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalReceiver());
        try {
            c = up.breakerGuess(pegs, colors);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        terminalUtils.printLine("S'ha generat el codi " + c.toString());
    }

    private static void case1() {
        initializeGameInfo();
        UserPlayer up = new UserPlayer("testUser", Player.Role.MAKER);
        ColorRow c = new ColorRow();
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalReceiver());
        try {
            c = up.makerGuess(pegs, colors);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        terminalUtils.printLine("S'ha generat el codi " + c.toString());
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