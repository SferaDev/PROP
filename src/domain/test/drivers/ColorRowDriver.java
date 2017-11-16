package domain.test.drivers;

import domain.model.Receiver;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.row.ColorRow;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.TerminalMenuBuilder;

public class ColorRowDriver {
    private static final Receiver terminalInputOutput = new TerminalReceiver();


    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Menu ColorRowDriver:");
        terminalMenuBuilder.addOption("Probar creadora", ColorRowDriver::case1);
        terminalMenuBuilder.addOption("Sortir", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }


    private static void case1() {
        int[] cint = null;
        try {
            cint = terminalInputOutput.inputColorRow(4, 4);
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ColorRow c = new ColorRow(cint);
        terminalInputOutput.outputColorRow(c.toString());
    }

}

