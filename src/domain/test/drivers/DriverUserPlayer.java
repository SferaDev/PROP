package domain.test.drivers;

import domain.controller.DomainController;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.TerminalController;
import presentation.model.TerminalInputOutput;
import presentation.utils.TerminalMenuBuilder;

public class DriverUserPlayer {
    private static TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
    private static final TerminalController terminalController = TerminalController.getInstance();
    private static Integer pegs;
    private static Integer colors;

    public static void main(String args[]) {
        terminalMenuBuilder.addTitle("Menu DriverUserPlayer:");
        terminalMenuBuilder.addOption("Crear un makerGuess", DriverUserPlayer::case1);
        terminalMenuBuilder.addOption("Crear un breakerGuess", DriverUserPlayer::case2);
        terminalMenuBuilder.addOption("Obtenir control d'un guess", DriverUserPlayer::case3);
        terminalMenuBuilder.addOption("Sortir",terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case3() {
        pegs = 4; colors = 6;
        UserPlayer up = new UserPlayer("testUser", Player.Role.MAKER);
        ColorRow c = new ColorRow(1, 1, 1, 1);
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalInputOutput());

        ControlRow control = new ControlRow(0,0);
        try {
            control = up.scoreGuess(c);
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        up.receiveControl(control);
       // terminalController.printLine("S'ha obtingut la correcció (blacks, whites): " + control.getBlacks() + ", " + control.getWhites());

    }

    private static void case2() {
        initializeGameInfo();
        UserPlayer up = new UserPlayer("testUser", Player.Role.MAKER);
        ColorRow c = new ColorRow();
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalInputOutput());
        try {
            c = up.breakerGuess(pegs, colors);
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        terminalController.printLine("S'ha generat el codi " + c.toString());
    }

    private static void case1() {
        initializeGameInfo();
        UserPlayer up = new UserPlayer("testUser", Player.Role.MAKER);
        ColorRow c = new ColorRow();
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalInputOutput());
        try {
            c = up.makerGuess(pegs, colors);
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        terminalController.printLine("S'ha generat el codi " + c.toString());

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