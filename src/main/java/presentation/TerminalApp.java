package presentation;

import domain.controller.MainController;
import presentation.model.TerminalInputOutput;
import presentation.utils.TerminalUtils;

import java.util.Scanner;

public class TerminalApp {

    private MainController mainController = MainController.getInstance();

    public void startApplication() {
        mainController.setGameInterface(new TerminalInputOutput());
        showMainMenu();
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            TerminalUtils.println(Constants.MAIN_MENU_SEPARATOR + "\n" +
                    Constants.MAIN_MENU_TITLE + "\n" +
                    Constants.MAIN_MENU_SEPARATOR + "\n" +
                    Constants.MAIN_REGISTER + ". " + Constants.MAIN_REGISTER_TITLE + "\n" +
                    Constants.MAIN_LOGIN + ". " + Constants.MAIN_LOGIN_TITLE + "\n" +
                    Constants.MAIN_STATS + ". " + Constants.MAIN_STATS_TITLE + "\n" +
                    Constants.MAIN_HELP + ". " + Constants.MAIN_HELP_TITLE + "\n" +
                    Constants.MAIN_EXIT + ". " + Constants.MAIN_EXIT_TITLE);

            switch (scanner.nextInt()) {
                case Constants.MAIN_REGISTER:
                    register();
                    break;
                case Constants.MAIN_LOGIN:
                    login();
                    break;
                case Constants.MAIN_STATS:
                    showStatsMenu();
                    break;
                case Constants.MAIN_HELP:
                    showHelpMenu();
                    break;
                case Constants.MAIN_EXIT:
                    System.exit(0);
                    break;
                default:
                    TerminalUtils.errorln("Introdueixi una opció de la llista");
                    break;
            }

        } while (true);
    }

    private void showPlayMenu(String userName) {
        Scanner scanner = new Scanner(System.in);
        do {
            TerminalUtils.println(Constants.PLAY_MENU_SEPARATOR + "\n" +
                    Constants.PLAY_MENU_TITLE + "\n" +
                    Constants.PLAY_MENU_SEPARATOR + "\n" +
                    Constants.PLAY_NEW_GAME + ". " + Constants.PLAY_NEW_GAME_TITLE + "\n" +
                    Constants.PLAY_PREV_GAME + ". " + Constants.PLAY_PREV_GAME_TITLE + "\n" +
                    Constants.PLAY_STATS + ". " + Constants.PLAY_STATS_TITLE + "\n" +
                    Constants.PLAY_HELP + ". " + Constants.PLAY_HELP_TITLE + "\n" +
                    Constants.PLAY_BACK + ". " + Constants.PLAY_BACK_TITLE);

            switch (scanner.nextInt()) {
                case Constants.PLAY_NEW_GAME:
                    newGame(userName);
                    break;
                case Constants.PLAY_PREV_GAME:
                    continueGame(userName);
                    break;
                case Constants.PLAY_STATS:
                    showStatsMenu();
                    break;
                case Constants.PLAY_HELP:
                    showHelpMenu();
                    break;
                case Constants.PLAY_BACK:
                    return;
                default:
                    TerminalUtils.errorln("Introdueixi una opció de la llista");
                    break;
            }
        } while (true);
    }

    private void newGame(String userName) {
        Scanner scanner = new Scanner(System.in);
        String role = null;
        do {
            TerminalUtils.println(Constants.NEW_MENU_SEPARATOR + "\n" +
                    Constants.NEW_MENU_TITLE + "\n" +
                    Constants.NEW_MENU_SEPARATOR + "\n" +
                    Constants.NEW_BREAKER_GAME + ". " + Constants.NEW_BREAKER_GAME_TITLE + "\n" +
                    Constants.NEW_MAKER_GAME + ". " + Constants.NEW_MAKER_GAME_TITLE + "\n" +
                    Constants.NEW_BACK + ". " + Constants.NEW_BACK_TITLE);

            switch (scanner.nextInt()) {
                case Constants.NEW_BREAKER_GAME:
                    role = "BREAKER";
                    break;
                case Constants.NEW_MAKER_GAME:
                    role = "MAKER";
                    break;
                case Constants.NEW_BACK:
                    return;
                default:
                    TerminalUtils.errorln("Introdueixi una opció de la llista");
                    break;
            }
        } while (role == null);

        int pegs, colors, turns;
        // TODO
        pegs = 4;
        colors = 6;
        turns = 12;

        mainController.startNewGame(userName, role, pegs, colors, turns);
    }

    private void continueGame(String userName) {

    }

    private void showStatsMenu() {

    }

    private void showHelpMenu() {

    }

    private void login() {
        TerminalUtils.println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.next();

        if (!mainController.existsUser(userName)) {
            TerminalUtils.errorln(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        boolean login = false;
        for (int i = 0; !login && i < 3; i++) {
            if (i > 0) TerminalUtils.errorln("Contrasenya erronea!");
            TerminalUtils.println("Introdueixi la seva contrasenya");
            login = mainController.loginUser(userName, scanner.next());
        }

        if (!login) {
            TerminalUtils.errorln("Contrasenya erronea 3 cops");
        } else showPlayMenu(userName);
    }

    private void register() {
        TerminalUtils.println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        String password1, password2;
        int i = 1;
        do {
            if (i++ > 1) TerminalUtils.errorln("No coincideixen!");
            TerminalUtils.println("Introdueixi la seva contrasenya");
            password1 = scanner.next();
            TerminalUtils.println("Repeteixi la seva contrasenya");
            password2 = scanner.next();
        } while (!password1.equals(password2));

        if (mainController.createUser(username, password1)) {
            showPlayMenu(username);
        } else {
            TerminalUtils.errorln("Ja existeix l'usuari");
        }
    }
}
