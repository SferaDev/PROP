import domain.controller.MainController;
import domain.controller.UserController;
import domain.model.*;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.UserPlayer;

import java.util.Scanner;

class TerminalApp {
    private MainController mainController;
    private UserController userController;

    void startApplication() {
        mainController = MainController.getInstance();
        userController = mainController.getUserController();

        mainController.setGameInterface(new InputOutput() {
            @Override
            public Row<ControlPeg> inputControlRow(int pegs) {
                return null;
            }

            @Override
            public Row<ColorPeg> inputColorRow(int pegs, int colors) {
                // TODO
                return null;
            }

            @Override
            public void outputControlRow(Row<ControlPeg> row) {
                // TODO
            }

            @Override
            public void outputColorRow(Row<ColorPeg> row) {
                // TODO
            }
        });

        showMainMenu();
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            println(Constants.MAIN_MENU_SEPARATOR + "\n" +
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
                    errorln("Introdueixi una opció de la llista");
                    break;
            }

        } while (true);
    }

    private void showPlayMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        do {
            println(Constants.PLAY_MENU_SEPARATOR + "\n" +
                    Constants.PLAY_MENU_TITLE + "\n" +
                    Constants.PLAY_MENU_SEPARATOR + "\n" +
                    Constants.PLAY_NEW_GAME + ". " + Constants.PLAY_NEW_GAME_TITLE + "\n" +
                    Constants.PLAY_PREV_GAME + ". " + Constants.PLAY_PREV_GAME_TITLE + "\n" +
                    Constants.PLAY_STATS + ". " + Constants.PLAY_STATS_TITLE + "\n" +
                    Constants.PLAY_HELP + ". " + Constants.PLAY_HELP_TITLE + "\n" +
                    Constants.PLAY_BACK + ". " + Constants.PLAY_BACK_TITLE);

            switch (scanner.nextInt()) {
                case Constants.PLAY_NEW_GAME:
                    newGame(user);
                    break;
                case Constants.PLAY_PREV_GAME:
                    continueGame(user);
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
                    errorln("Introdueixi una opció de la llista");
                    break;
            }
        } while (true);
    }

    private void newGame(User user) {
        Scanner scanner = new Scanner(System.in);
        Role role = null;
        do {
            println(Constants.NEW_MENU_SEPARATOR + "\n" +
                    Constants.NEW_MENU_TITLE + "\n" +
                    Constants.NEW_MENU_SEPARATOR + "\n" +
                    Constants.NEW_BREAKER_GAME + ". " + Constants.NEW_BREAKER_GAME_TITLE + "\n" +
                    Constants.NEW_MAKER_GAME + ". " + Constants.NEW_MAKER_GAME_TITLE + "\n" +
                    Constants.NEW_BACK + ". " + Constants.NEW_BACK_TITLE);

            switch (scanner.nextInt()) {
                case Constants.NEW_BREAKER_GAME:
                    role = Role.BREAKER;
                    break;
                case Constants.NEW_MAKER_GAME:
                    role = Role.MAKER;
                    break;
                case Constants.NEW_BACK:
                    return;
                default:
                    errorln("Introdueixi una opció de la llista");
                    break;
            }
        } while (role == null);

        int pegs, colors, turns;
        // TODO
        pegs = 4;
        colors = 6;
        turns = 12;

        Game currentGame = new Game(new UserPlayer(user, role), pegs, colors, turns);
        currentGame.startGame();
        /*if (currentGame.getStatus() != Status.FINISHED) {
            // Offer to save!
        }*/
    }

    private void continueGame(User user) {

    }

    private void showStatsMenu() {

    }

    private void showHelpMenu() {

    }

    private void login() {
        println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();

        if (!userController.exists(username)) {
            errorln(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        User user = null;
        for (int i = 0; user == null && i < 3; i++) {
            if (i > 0) errorln("Contrasenya erronea!");
            println("Introdueixi la seva contrasenya");
            user = (User) userController.login(username, scanner.next());
        }

        if (user == null) {
            errorln("Contrasenya erronea 3 cops");
        } else showPlayMenu(user);
    }

    private void register() {
        println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        String password1, password2;
        do {
            println("Introdueixi la seva contrasenya");
            password1 = scanner.next();
            println("Repeteixi la seva contrasenya");
            password2 = scanner.next();
        } while (!password1.equals(password2));

        User user = new User(username, password1);
        if (userController.insert(user)) {
            showPlayMenu(user);
        } else {
            errorln("Ja existeix l'usuari");
        }
    }

    private void println(String string) {
        System.out.println(string);
        System.out.flush();
    }

    private void errorln(String string) {
        System.err.println(string);
        System.err.flush();
    }
}