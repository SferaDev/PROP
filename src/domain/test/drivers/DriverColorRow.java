package domain.test.drivers;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.row.ColorRow;
import presentation.model.TerminalInputOutput;
import presentation.utils.TerminalMenuBuilder;

public class DriverColorRow {
    private static TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
    private static TerminalInputOutput terminalInputOutput = new TerminalInputOutput();


    public static void main(String args[]){
        terminalMenuBuilder.addTitle("Menu DriverColorRow:");
        terminalMenuBuilder.addOption("Probar creadora", DriverColorRow::case1);
        terminalMenuBuilder.addOption("Sortir",terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }


    private static void case1() {
        int[] cint = null;
        try {
             cint = terminalInputOutput.inputColorRow(4, 4);
        } catch (FinishGameException e) {
            e.printStackTrace();
        } catch (CommandInterruptException e) {
            e.printStackTrace();
        }
        ColorRow c = new ColorRow(cint);
        terminalInputOutput.outputColorRow(c.toString());
    }

}

