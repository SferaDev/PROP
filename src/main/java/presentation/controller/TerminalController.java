package presentation.controller;

import domain.controller.DomainController;
import domain.model.exceptions.FinishGameException;

import java.util.Scanner;

/**
 * The type Terminal controller.
 */
public class TerminalController {
    private static TerminalController mInstance = new TerminalController();
    private Scanner scanner = new Scanner(System.in);

    private TerminalController() {
        // Empty Constructor
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TerminalController getInstance() {
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
        System.err.println(string);
        System.err.flush();
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
     */
    public Integer readGameInteger() throws FinishGameException {
        String string = readString();
        executeCommands(string);
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Read line string.
     *
     * @return the string
     */
    public String readLine() {
        return scanner.nextLine();
    }

    public String outputTimestamp(long time) {
        long elapsed = time / 1000;
        int hours = (int) (elapsed / (3600));
        int minutes = (int) ((elapsed - (hours * 3600)) / 60);
        int seconds = (int) (elapsed - (hours * 3600) - minutes * 60);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void executeCommands(String token) throws FinishGameException {
        switch (token.toLowerCase()) {
            case "hint":
            case "help":
            case "ajuda":
                DomainController.getInstance().getGameController().provideHelp();
                break;
            case "save":
            case "guardar":
            case "guarda":
                DomainController.getInstance().getGameController().saveCurrentGame();
                break;
            case "exit":
            case "surt":
                DomainController.getInstance().getGameController().stopCurrentGame();
                break;
        }
    }
}
