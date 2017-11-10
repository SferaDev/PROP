package presentation;

import domain.controller.DomainController;
import presentation.controller.TerminalController;
import presentation.model.TerminalInputOutput;

/**
 * The type Terminal app.
 */
public class TerminalApp {

    private DomainController mainController = DomainController.getInstance();
    private TerminalController terminalController = TerminalController.getInstance();

    /**
     * Start application.
     */
    public void startApplication() {
        mainController.setGameInterface(new TerminalInputOutput());
        showMainMenu();
    }

    private void showMainMenu() {
        do {
            terminalController.printLine(Constants.MAIN_MENU_SEPARATOR + "\n" +
                    Constants.MAIN_MENU_TITLE + "\n" +
                    Constants.MAIN_MENU_SEPARATOR + "\n" +
                    Constants.MAIN_REGISTER + ". " + Constants.MAIN_REGISTER_TITLE + "\n" +
                    Constants.MAIN_LOGIN + ". " + Constants.MAIN_LOGIN_TITLE + "\n" +
                    Constants.MAIN_STATS + ". " + Constants.MAIN_STATS_TITLE + "\n" +
                    Constants.MAIN_HELP + ". " + Constants.MAIN_HELP_TITLE + "\n" +
                    Constants.MAIN_EXIT + ". " + Constants.MAIN_EXIT_TITLE);

            switch (terminalController.readInteger()) {
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
                    terminalController.errorLine("Introdueixi una opció de la llista");
                    break;
            }

        } while (true);
    }

    private void showPlayMenu(String userName) {
        do {
            terminalController.printLine(Constants.PLAY_MENU_SEPARATOR + "\n" +
                    Constants.PLAY_MENU_TITLE + "\n" +
                    Constants.PLAY_MENU_SEPARATOR + "\n" +
                    Constants.PLAY_NEW_GAME + ". " + Constants.PLAY_NEW_GAME_TITLE + "\n" +
                    Constants.PLAY_PREV_GAME + ". " + Constants.PLAY_PREV_GAME_TITLE + "\n" +
                    Constants.PLAY_STATS + ". " + Constants.PLAY_STATS_TITLE + "\n" +
                    Constants.PLAY_HELP + ". " + Constants.PLAY_HELP_TITLE + "\n" +
                    Constants.PLAY_BACK + ". " + Constants.PLAY_BACK_TITLE);

            switch (terminalController.readInteger()) {
                case Constants.PLAY_NEW_GAME:
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
                    terminalController.errorLine("Introdueixi una opció de la llista");
                    break;
            }
        } while (true);
    }


    private void newGame(String userName) {
        String role = null;
        do {
            terminalController.printLine(Constants.NEW_MENU_SEPARATOR + "\n" +
                    Constants.NEW_MENU_TITLE + "\n" +
                    Constants.NEW_MENU_SEPARATOR + "\n" +
                    Constants.NEW_BREAKER_GAME + ". " + Constants.NEW_BREAKER_GAME_TITLE + "\n" +
                    Constants.NEW_MAKER_GAME + ". " + Constants.NEW_MAKER_GAME_TITLE + "\n" +
                    Constants.NEW_BACK + ". " + Constants.NEW_BACK_TITLE);

            switch (terminalController.readInteger()) {
                case Constants.NEW_BREAKER_GAME:
                    role = "BREAKER";
                    break;
                case Constants.NEW_MAKER_GAME:
                    role = "MAKER";
                    break;
                case Constants.NEW_BACK:
                    return;
                default:
                    terminalController.errorLine("Introdueixi una opció de la llista");
                    break;
            }
        } while (role == null);

        int pegs, colors, turns;

        terminalController.printLine("Introdueixi el nombre de fitxes d'una combinació");
        pegs = terminalController.readInteger();
        terminalController.printLine("Introdueixi el nombre de colors possibles");
        colors = terminalController.readInteger();

        String computerName = "DummyComputer";
        if (role.equals("MAKER")) {
            do {
                terminalController.printLine(Constants.NEW_FIVEGUESS_GAME + ". " + Constants.NEW_FIVEGUESS_GAME_TITLE + "\n" +
                        Constants.NEW_GENETIC_GAME + ". " + Constants.NEW_GENETIC_GAME_TITLE + "\n" +
                        Constants.NEW_BACK_ALGORITHM + ". " + Constants.NEW_BACK_ALGORITHM_TITLE);

                switch (terminalController.readInteger()) {
                    case Constants.NEW_FIVEGUESS_GAME:
                        computerName = "FiveGuessComputer";
                        break;
                    case Constants.NEW_GENETIC_GAME:
                        computerName = "GeneticComputer";
                        break;
                    case Constants.NEW_BACK_ALGORITHM:
                        return;
                    default:
                        terminalController.errorLine("Introdueixi una opció de la llista");
                        break;
                }
            } while (computerName.equals("DummyComputer"));
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
        terminalController.printLine("Per triar una opció ha de marcar el nombre que acompanya a la opció desitjada");
        // TODO add exit

    }

    private void login() {
        terminalController.printLine("Introdueixi el seu nom d'usuari");
        String userName = terminalController.readString();

        if (!mainController.getUserController().existsUser(userName)) {
            terminalController.errorLine(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        boolean login = false;
        for (int i = 0; !login && i < 3; i++) {
            if (i > 0) terminalController.errorLine("Contrasenya erronea!");
            terminalController.printLine("Introdueixi la seva contrasenya");
            login = mainController.getUserController().loginUser(userName, terminalController.readString());
        }

        if (!login) {
            terminalController.errorLine("Contrasenya erronea 3 cops");
        } else showPlayMenu(userName);
    }

    private void register() {
        terminalController.printLine("Introdueixi el seu nom d'usuari");
        String username = terminalController.readString();
        String password1, password2;
        int i = 1;
        do {
            if (i++ > 1) terminalController.errorLine("No coincideixen!");
            terminalController.printLine("Introdueixi la seva contrasenya");
            password1 = terminalController.readString();
            terminalController.printLine("Repeteixi la seva contrasenya");
            password2 = terminalController.readString();
        } while (!password1.equals(password2));

        if (mainController.getUserController().createUser(username, password1)) {
            showPlayMenu(username);
        } else {
            terminalController.errorLine("Ja existeix l'usuari");
        }
    }

    private void showStats() {
        // TODO
    }


}
