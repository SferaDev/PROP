package presentation.utils;

import presentation.controller.TerminalController;

import java.util.ArrayList;

public class TerminalMenuBuilder {
    private StringBuilder sTitle = new StringBuilder();
    private StringBuilder sCommands = new StringBuilder();
    private ArrayList<Command> commands = new ArrayList<>();
    private String defaultOption;
    private boolean finish = false;
    private boolean goBackToStart = false;

    public void addTitle(String title) {
        // Add margin to title
        title = "  " + title + "  ";
        // Create separator
        String separator = new String(new char[title.length()]).replace('\0', '=');
        // Build the title
        sTitle.append(separator).append("\n");
        sTitle.append(title).append("\n");
        sTitle.append(separator).append("\n");
    }

    public void addOption(String title, Command function) {
        commands.add(function);
        sCommands.append(commands.size()).append(". ").append(title).append("\n");
    }

    public void onExitGoBackToStart(boolean goBack) {
        goBackToStart = goBack;
    }

    public boolean finishExecution() {
        finish = true;
        return true;
    }

    public void defaultError(String error) {
        defaultOption = error;
    }

    public void queryLoop() {
        while (!finish) {
            query();
        }
    }

    private void query() {
        // Todo: Clean screen
        TerminalController.getInstance().printLine("\033[H\033[2J");

        // Print lines
        TerminalController.getInstance().printLine(sTitle.toString());
        TerminalController.getInstance().printLine(sCommands.toString());

        // Request input
        int inputInteger = TerminalController.getInstance().readInteger() - 1;
        if (inputInteger >= 0 && inputInteger < commands.size()) {
            commands.get(inputInteger).apply();
            if (goBackToStart) finish = true;
        } else if (defaultOption != null){
            TerminalController.getInstance().printLine(defaultOption);
        }
    }

    @FunctionalInterface
    public interface Command {
        void apply();
    }
}
