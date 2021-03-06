package drivers;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

/**
 * The type User player driver.
 *
 * @author Oriol Borrell Roig
 */
public class UserPlayerDriver {
    private static final TerminalUtils terminalUtils = TerminalUtils.getInstance();
    private static int pegs, colors;

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Mastermind: UserPlayerDriver");
        terminalMenuBuilder.addOption("Crear un makerGuess", UserPlayerDriver::testCreateMakerGuess);
        terminalMenuBuilder.addOption("Crear un breakerGuess", UserPlayerDriver::testCreateBreakerGuess);
        terminalMenuBuilder.addOption("Obtenir control d'un guess", UserPlayerDriver::testReceiveControl);
        terminalMenuBuilder.addOption("Enrere", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void testReceiveControl() {
        pegs = 4;
        colors = 6;
        UserPlayer up = new UserPlayer("testUser", Player.Role.Maker);
        ColorRow c = new ColorRow(1, 1, 1, 1);
        ControlRow control = new ControlRow(0, 0);
        try {
            control = up.scoreGuess(c);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        up.receiveControl(control);
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testCreateBreakerGuess() {
        initializeGameInfo();
        UserPlayer up = new UserPlayer("testUser", Player.Role.Maker);
        ColorRow c = new ColorRow();
        try {
            c = up.breakerGuess(pegs, colors);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        terminalUtils.printLine("S'ha generat el codi " + c.toString());
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testCreateMakerGuess() {
        initializeGameInfo();
        UserPlayer up = new UserPlayer("testUser", Player.Role.Maker);
        ColorRow c = new ColorRow();
        try {
            c = up.makerGuess(pegs, colors);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        terminalUtils.printLine("S'ha generat el codi " + c.toString());
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