package domain.test.drivers;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.row.ControlRow;
import presentation.model.TerminalInputOutput;
import presentation.utils.TerminalMenuBuilder;

public class DriverControlRow {
    private static TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
    private static TerminalInputOutput terminalInputOutput = new TerminalInputOutput();

    public static void main(String args[]){
        terminalMenuBuilder.addTitle("Menu DriverControlRow:");
        terminalMenuBuilder.addOption("Probar creadora", DriverControlRow::case1);
        terminalMenuBuilder.addOption("Probar igualtat entre dos controlRows", DriverControlRow::case2);
        terminalMenuBuilder.addOption("Sortir",terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case2() {
        Integer b = 0, w = 0;
        try {
            b  = terminalInputOutput.inputControlBlacks();
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        try {
            w = terminalInputOutput.inputControlWhites();
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow c1 = new ControlRow(b, w);
        try {
            b  = terminalInputOutput.inputControlBlacks();
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        try {
            w = terminalInputOutput.inputControlWhites();
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow c2 = new ControlRow(b, w);
        if (c1.equals(c2)) terminalInputOutput.outputMessage("Son iguals");
        else terminalInputOutput.outputMessage("No son iguals");

    }

    private static void case1() {
        Integer b = 0, w = 0;
        try {
            b  = terminalInputOutput.inputControlBlacks();
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        try {
            w = terminalInputOutput.inputControlWhites();
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        ControlRow control = new ControlRow(b, w);
        terminalInputOutput.outputControlRow(control.getBlacks(), control.getWhites());
    }
}
