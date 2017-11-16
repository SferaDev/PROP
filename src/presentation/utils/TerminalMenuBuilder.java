package presentation.utils;

import java.util.ArrayList;

public class TerminalMenuBuilder {
    private final TerminalUtils terminalUtils = TerminalUtils.getInstance();
    private final StringBuilder mTitle = new StringBuilder();
    private final StringBuilder mDescription = new StringBuilder();
    private final StringBuilder mOptions = new StringBuilder();
    private final ArrayList<Command> mCommands = new ArrayList<>();
    private String defaultOption = "Error!";
    private boolean error = false;
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
        mOptions.append(String.format("%2.2s. %-55.55s", mCommands.size(), title)).append("\n");
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
        terminalUtils.clearScreen();

        // Print lines
        if (!mTitle.toString().isEmpty()) {
            terminalUtils.printLine(mTitle.toString());
        }
        if (!mDescription.toString().isEmpty()) {
            terminalUtils.printLine(mDescription.toString());
        }
        if (error && defaultOption != null) {
            terminalUtils.printLine(defaultOption);
            terminalUtils.printLine("");
        }
        if (!mOptions.toString().isEmpty()) {
            terminalUtils.printLine(mOptions.toString());
        }
    }

    private void showQuery() {
        showOutput();

        if (mCommands.size() == 0) finish = true;
        else {
            // Request input
            int inputInteger = terminalUtils.readInteger() - 1;
            if (inputInteger >= 0 && inputInteger < mCommands.size()) {
                mCommands.get(inputInteger).apply();
                if (goBackToStart) finish = true;
            } else if (defaultOption != null) {
                error = true;
            }
        }
    }

    @FunctionalInterface
    public interface Command {
        void apply();
    }
}
