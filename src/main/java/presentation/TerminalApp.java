package presentation;

import domain.controller.DomainController;
import presentation.model.TerminalInputOutput;
import presentation.utils.TerminalUtils;

import java.util.Scanner;

public class TerminalApp {

    private DomainController mainController = DomainController.getInstance();

    public void startApplication() {
        mainController.setGameInterface(new TerminalInputOutput());
        showMainMenu();
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            TerminalUtils.printLine(Constants.MAIN_MENU_SEPARATOR + "\n" +
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
                    TerminalUtils.errorLine("Introdueixi una opció de la llista");
                    break;
            }

        } while (true);
    }

    private void showPlayMenu(String userName) {
        Scanner scanner = new Scanner(System.in);
        do {
            TerminalUtils.printLine(Constants.PLAY_MENU_SEPARATOR + "\n" +
                    Constants.PLAY_MENU_TITLE + "\n" +
                    Constants.PLAY_MENU_SEPARATOR + "\n" +
                    Constants.PLAY_NEW_GAME + ". " + Constants.PLAY_NEW_GAME_TITLE + "\n" +
                    Constants.PLAY_PREV_GAME + ". " + Constants.PLAY_PREV_GAME_TITLE + "\n" +
                    Constants.PLAY_STATS + ". " + Constants.PLAY_STATS_TITLE + "\n" +
                    Constants.PLAY_HELP + ". " + Constants.PLAY_HELP_TITLE + "\n" +
                    Constants.PLAY_BACK + ". " + Constants.PLAY_BACK_TITLE);

            switch (scanner.nextInt()) {
                case Constants.PLAY_NEW_GAME:
                    showNewGameMenu(userName);
                    newGame(userName);
                    break;
                case Constants.PLAY_PREV_GAME:
                    continueGame(userName);
                    break;
                case Constants.PLAY_STATS:
                    showStats();
                    break;
                case Constants.PLAY_HELP:
                    showHelpMenu();
                    break;
                case Constants.PLAY_BACK:
                    return;
                default:
                    TerminalUtils.errorLine("Introdueixi una opció de la llista");
                    break;
            }
        } while (true);
    }

    private void showNewGameMenu(String userName) {

    }

    private void newGame(String userName) {
        Scanner scanner = new Scanner(System.in);
        String role = null;
        do {
            TerminalUtils.printLine(Constants.NEW_MENU_SEPARATOR + "\n" +
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
                    TerminalUtils.errorLine("Introdueixi una opció de la llista");
                    break;
            }
        } while (role == null);

        int pegs, colors, turns;

        TerminalUtils.println("Introdueixi el nombre de fitxes d'una combinació");
        pegs = scanner.nextInt();
        TerminalUtils.println("Introdueixi el nombre de colors possibles");
        colors = scanner.nextInt();

        String computerName = null;
        if (role == "MAKER") {
            do {
                TerminalUtils.println(Constants.NEW_FIVEGUESS_GAME + ". " + Constants.NEW_FIVEGUESS_GAME_TITLE + "\n" +
                        Constants.NEW_GENETIC_GAME + ". " + Constants.NEW_GENETIC_GAME_TITLE + "\n" +
                        Constants.NEW_DUMMY_GAME + ". " + Constants.NEW_DUMMY_GAME_TITLE + "\n" +
                        Constants.NEW_BACK_ALGORITHM + ". " + Constants.NEW_BACK_ALGORITHM_TITLE);

                switch (scanner.nextInt()) {
                    case Constants.NEW_FIVEGUESS_GAME:
                        computerName = "FiveGuessComputer";
                        break;
                    case Constants.NEW_GENETIC_GAME:
                        computerName = "GeneticComputer";
                        break;
                    case Constants.NEW_DUMMY_GAME:
                        computerName = "DummyComputer";
                        break;
                    case Constants.NEW_BACK_ALGORITHM:
                        return;
                    default:
                        TerminalUtils.errorln("Introdueixi una opció de la llista");
                        break;
                }
            } while (computerName == null);
        }

        // TODO

        turns = 12;


        mainController.getGameController().startNewGame(userName, computerName, role, pegs, colors, turns);
    }

    private void continueGame(String userName) {

    }

    private void showStatsMenu() {

    }

    private void showHelpMenu() {

    }

    private void login() {
        TerminalUtils.printLine("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.next();

        if (!mainController.getUserController().existsUser(userName)) {
            TerminalUtils.errorLine(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        boolean login = false;
        for (int i = 0; !login && i < 3; i++) {
            if (i > 0) TerminalUtils.errorLine("Contrasenya erronea!");
            TerminalUtils.printLine("Introdueixi la seva contrasenya");
            login = mainController.getUserController().loginUser(userName, scanner.next());
        }

        if (!login) {
            TerminalUtils.errorLine("Contrasenya erronea 3 cops");
        } else showPlayMenu(userName);
    }

    private void register() {
        TerminalUtils.printLine("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        String password1, password2;
        int i = 1;
        do {
            if (i++ > 1) TerminalUtils.errorLine("No coincideixen!");
            TerminalUtils.printLine("Introdueixi la seva contrasenya");
            password1 = scanner.next();
            TerminalUtils.printLine("Repeteixi la seva contrasenya");
            password2 = scanner.next();
        } while (!password1.equals(password2));

        if (mainController.getUserController().createUser(username, password1)) {
            showPlayMenu(username);
        } else {
            TerminalUtils.errorLine("Ja existeix l'usuari");
        }
    }

    private void showStats() {
        // TODO
    }


}
