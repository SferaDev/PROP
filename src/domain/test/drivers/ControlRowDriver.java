package domain.test.drivers;

import domain.model.Receiver;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.row.ControlRow;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.TerminalMenuBuilder;

public class ControlRowDriver {
    private static final Receiver terminalInputOutput = new TerminalReceiver();

    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Menu ControlRowDriver:");
        terminalMenuBuilder.addOption("Probar creadora", ControlRowDriver::case1);
        terminalMenuBuilder.addOption("Probar igualtat entre dos controlRows", ControlRowDriver::case2);
        terminalMenuBuilder.addOption("Sortir", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case2() {
        Integer b = 0, w = 0;
        try {
            b = terminalInputOutput.inputControlBlacks();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        try {
            w = terminalInputOutput.inputControlWhites();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow c1 = new ControlRow(b, w);
        try {
            b = terminalInputOutput.inputControlBlacks();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        try {
            w = terminalInputOutput.inputControlWhites();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow c2 = new ControlRow(b, w);
        if (c1.equals(c2)) terminalInputOutput.outputMessage("Son iguals");
        else terminalInputOutput.outputMessage("No son iguals");

    }

    private static void case1() {
        Integer b = 0, w = 0;
        try {
            b = terminalInputOutput.inputControlBlacks();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        try {
            w = terminalInputOutput.inputControlWhites();
        } catch (FinishGameException | CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow control = new ControlRow(b, w);
        terminalInputOutput.outputControlRow(control.getBlacks(), control.getWhites());
    }
}
