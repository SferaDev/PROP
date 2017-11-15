package domain.test.drivers;

import presentation.utils.TerminalMenuBuilder;

public class DriverUserPlayer {
    private static TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
    public static void main(String args[]) {
        createMenu();
    }

    private static void createMenu() {
        terminalMenuBuilder.addTitle("Menu DriverUserPlayer:");
        terminalMenuBuilder.addOption("o1", DriverUserPlayer::proba);
        terminalMenuBuilder.execute();

    }
    private static void proba()
    {}

}