package domain.test.drivers;

import domain.controller.DomainController;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.row.ControlRow;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

/**
 * The type Control row driver.
 *
 * @author Oriol Borrell Roig
 */
public class ControlRowDriver {

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Mastermind: ControlRowDriver");
        terminalMenuBuilder.addOption("Provar creadora", ControlRowDriver::testConstructor);
        terminalMenuBuilder.addOption("Provar igualtat entre dos controlRows", ControlRowDriver::testCompareControl);
        terminalMenuBuilder.addOption("Enrere", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void testCompareControl() {
        Integer b = 0, w = 0;
        try {
            b = DomainController.getInstance().getGameInterface().inputControlBlacks();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        try {
            w = DomainController.getInstance().getGameInterface().inputControlWhites();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow c1 = new ControlRow(b, w);
        try {
            b = DomainController.getInstance().getGameInterface().inputControlBlacks();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        try {
            w = DomainController.getInstance().getGameInterface().inputControlWhites();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow c2 = new ControlRow(b, w);
        if (c1.equals(c2)) DomainController.getInstance().getGameInterface().outputMessage("Son iguals");
        else DomainController.getInstance().getGameInterface().outputMessage("No son iguals");
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    private static void testConstructor() {
        Integer b = 0, w = 0;
        try {
            b = DomainController.getInstance().getGameInterface().inputControlBlacks();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        try {
            w = DomainController.getInstance().getGameInterface().inputControlWhites();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow control = new ControlRow(b, w);
        DomainController.getInstance().getGameInterface().outputControlRow(control.getBlacks(), control.getWhites());
        TerminalUtils.getInstance().pressEnterToContinue();
    }
}
