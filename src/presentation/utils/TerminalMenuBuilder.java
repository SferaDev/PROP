package presentation.utils;

import java.util.ArrayList;

/**
 * The type Terminal menu builder.
 *
 * @author Alexis Rico Carreto
 */
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

    /**
     * Add title.
     *
     * @param title the title
     */
    public void addTitle(String title) {
        title = "  " + title + "  ";
        String separator = new String(new char[title.length()]).replace('\0', '=');

        mTitle.append(separator).append("\n");
        mTitle.append(title).append("\n");
        mTitle.append(separator).append("\n");
    }

    /**
     * Add description.
     *
     * @param description the description
     */
    public void addDescription(String description) {
        mDescription.append(description).append("\n");
    }

    /**
     * Add option.
     *
     * @param title    the title
     * @param function the function
     */
    public void addOption(String title, Command function) {
        mCommands.add(function);
        mOptions.append(String.format("%2.2s. %-55.55s", mCommands.size(), title)).append("\n");
    }

    /**
     * On exit go back to start.
     *
     * @param goBack the go back
     */
    public void onExitGoBackToStart(boolean goBack) {
        goBackToStart = goBack;
    }

    /**
     * Finish execution.
     */
    public void finishExecution() {
        finish = true;
    }

    /**
     * Default error.
     *
     * @param error the error
     */
    public void defaultError(String error) {
        defaultOption = error;
    }

    /**
     * Execute.
     */
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

    /**
     * The interface Command.
     */
    @FunctionalInterface
    public interface Command {
        /**
         * Apply.
         */
        void apply();
    }
}
