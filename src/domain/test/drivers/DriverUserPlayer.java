package domain.test.drivers;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.controller.TerminalController;
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
        initializeGameInfo();
        UserPlayer up = new UserPlayer("testUser", Player.Role.MAKER);
        ColorRow c = new ColorRow();
        //Todo: No se de que son aquestes excepcions ni que fer amb elles
        try {
            c = up.breakerGuess(pegs, colors);
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow control = new ControlRow(0,0);
        try {
            control = up.scoreGuess(c);
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        terminalController.printLine("S'ha obtingut la correcció (blacks, whites): "
                + control.getBlacks() + ", " + control.getWhites());

    }

    private static void case2() {
        initializeGameInfo();
        UserPlayer up = new UserPlayer("testUser", Player.Role.MAKER);
        ColorRow c = new ColorRow();
        //Todo: No se de que son aquestes excepcions ni que fer amb elles
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
        //Todo: No se de que son aquestes excepcions ni que fer amb elles
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