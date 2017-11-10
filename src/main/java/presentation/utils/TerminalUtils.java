package presentation.utils;

import domain.controller.DomainController;

import java.util.Scanner;

public class TerminalUtils {
    public static void printLine(String string) {
        System.out.println(string);
        System.out.flush();
    }

    public static void errorLine(String string) {
        System.err.println(string);
        System.err.flush();
    }

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        String token = scanner.next();
        scanner.close();

        executeCommands(token);

        return token;
    }

    public static Integer readInteger() {
        Scanner scanner = new Scanner(System.in);
        String token = scanner.next();
        scanner.close();

        executeCommands(token);

        return Integer.parseInt(token);
    }

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
