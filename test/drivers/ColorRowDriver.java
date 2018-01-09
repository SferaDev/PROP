package drivers;

import domain.controller.DomainController;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.row.ColorRow;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

/**
 * The type Color row driver.
 *
 * @author Oriol Borrell Roig
 */
public class ColorRowDriver {

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Mastermind: ColorRowDriver:");
        terminalMenuBuilder.addOption("Provar creadora", ColorRowDriver::testConstructor);
        terminalMenuBuilder.addOption("Enrere", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void testConstructor() {
        int[] cint = null;
        try {
            cint = DomainController.getInstance().getGameInterface().inputColorRow(4, 4);
        } catch (FinishGameException | CommandInterruptException ignored) {
        }
        ColorRow c = new ColorRow(cint);
        DomainController.getInstance().getGameInterface().outputColorRow(c.toString());
        TerminalUtils.getInstance().pressEnterToContinue();
    }

}

