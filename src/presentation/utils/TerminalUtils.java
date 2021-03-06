package presentation.utils;

import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import presentation.controller.TerminalController;
import resources.strings.TerminalConstants;

import java.util.Scanner;

/**
 * The type Terminal controller.
 *
 * @author Alexis Rico Carreto
 */
public class TerminalUtils {
    private static final TerminalUtils mInstance = new TerminalUtils();
    private final Scanner scanner = new Scanner(System.in);

    private TerminalUtils() {
        // Empty Constructor
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TerminalUtils getInstance() {
        return mInstance;
    }

    /**
     * Print line.
     *
     * @param string the string
     */
    public void printLine(String string) {
        System.out.println(string);
        System.out.flush();
    }

    /**
     * Error line.
     *
     * @param string the string
     */
    public void errorLine(String string) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: Error");
        builder.addDescription(string);
        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.execute();
    }

    /**
     * Read string string.
     *
     * @return the string
     */
    public String readString() {
        return scanner.next();
    }

    /**
     * Read integer integer.
     *
     * @return the integer
     */
    public Integer readInteger() {
        try {
            return Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Read game integer.
     *
     * @return the integer
     * @throws FinishGameException       the finish game exception
     * @throws CommandInterruptException the command interrupt exception
     */
    public Integer readGameInteger() throws FinishGameException, CommandInterruptException {
        String string = readString();
        if (executeCommands(string)) throw new CommandInterruptException();
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Clear the screen.
     */
    public void clearScreen() {
        printLine("\033[H\033[2J");
    }

    /**
     * Execute the in-game commands.
     *
     * @param token string to evaluate
     * @return true if executed, false if no command found
     * @throws FinishGameException in case the command finishes the current game
     */
    private boolean executeCommands(String token) throws FinishGameException {
        switch (token.toLowerCase()) {
            case "hint":
            case "help":
            case "ajuda":
                TerminalController.getInstance().requestHelp();
                return true;
            case "save":
            case "guardar":
            case "guarda":
                TerminalController.getInstance().requestSaveGame();
                return true;
            case "exit":
            case "surt":
                TerminalController.getInstance().requestQuitGame();
                return true;
        }
        return false;
    }
}
