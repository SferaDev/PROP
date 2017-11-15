package presentation.utils;

import presentation.controller.TerminalController;

import java.util.ArrayList;

public class TerminalMenuBuilder {
    private final StringBuilder mTitle = new StringBuilder();
    private final StringBuilder mDescription = new StringBuilder();
    private final StringBuilder mOptions = new StringBuilder();
    private final ArrayList<Command> mCommands = new ArrayList<>();
    private String defaultOption;
    private boolean finish = false;
    private boolean goBackToStart = false;

    public void addTitle(String title) {
        title = "  " + title + "  ";
        String separator = new String(new char[title.length()]).replace('\0', '=');

        mTitle.append(separator).append("\n");
        mTitle.append(title).append("\n");
        mTitle.append(separator).append("\n");
    }

    public void addDescription(String description) {
        mDescription.append(description).append("\n");
    }

    public void addOption(String title, Command function) {
        mCommands.add(function);
        mOptions.append(mCommands.size()).append(". ").append(title).append("\n");
    }

    public void onExitGoBackToStart(boolean goBack) {
        goBackToStart = goBack;
    }

    public void finishExecution() {
        finish = true;
    }

    public void defaultError(String error) {
        defaultOption = error;
    }

    public void execute() {
        while (!finish) {
            showQuery();
        }
    }

    private void showOutput() {
        TerminalController.getInstance().printLine("\033[H\033[2J");

        // Print lines
        if (!mTitle.toString().isEmpty())
            TerminalController.getInstance().printLine(mTitle.toString());
        if (!mDescription.toString().isEmpty())
            TerminalController.getInstance().printLine(mDescription.toString());
        if (!mOptions.toString().isEmpty())
            TerminalController.getInstance().printLine(mOptions.toString());
    }

    private void showQuery() {
        showOutput();

        // Request input
        int inputInteger = TerminalController.getInstance().readInteger() - 1;
        if (inputInteger >= 0 && inputInteger < mCommands.size()) {
            mCommands.get(inputInteger).apply();
            if (goBackToStart) finish = true;
        } else if (defaultOption != null) {
            TerminalController.getInstance().printLine(defaultOption);
        }
    }

    @FunctionalInterface
    public interface Command {
        void apply();
    }
}
