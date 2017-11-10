package presentation.utils;

import domain.controller.DomainController;

import java.util.Scanner;

/**
 * The type Terminal utils.
 */
public class TerminalUtils {
    /**
     * Print line.
     *
     * @param string the string
     */
    public static void printLine(String string) {
        System.out.println(string);
        System.out.flush();
    }

    /**
     * Error line.
     *
     * @param string the string
     */
    public static void errorLine(String string) {
        System.err.println(string);
        System.err.flush();
    }

    /**
     * Read string string.
     *
     * @return the string
     */
    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        String token = scanner.next();
        scanner.close();

        executeCommands(token);

        return token;
    }

    /**
     * Read integer integer.
     *
     * @return the integer
     */
    public static Integer readInteger() {
        Scanner scanner = new Scanner(System.in);
        String token = scanner.next();
        scanner.close();

        executeCommands(token);

        return Integer.parseInt(token);
    }

    /**
     * Read line string.
     *
     * @return the string
     */
    public static String readLine() {
        Scanner scanner = new Scanner(System.in);
        String token = scanner.nextLine();
        scanner.close();

        executeCommands(token);

        return token;
    }

    private static void executeCommands(String token) {
        switch (token.toLowerCase()) {
            case "save":
                DomainController.getInstance().getGameController().saveCurrentGame();
                break;
            case "exit":
                DomainController.getInstance().getGameController().stopCurrentGame();
                break;
        }
    }
}
