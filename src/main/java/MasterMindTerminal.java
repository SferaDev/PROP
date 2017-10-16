import domain.controller.MainController;
import domain.controller.UserController;
import domain.model.User;

import java.util.Scanner;

class MasterMindTerminal {
    private MainController mainController;
    private UserController userController;

    void startApplication() {
        mainController = MainController.getInstance();
        userController = mainController.getUserController();

        showMainMenu();
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(Constants.MAIN_MENU_TITLE);
            System.out.println(Constants.MAIN_REGISTER + ". " + Constants.MAIN_REGISTER_TITLE);
            System.out.println(Constants.MAIN_LOGIN + ". " + Constants.MAIN_LOGIN_TITLE);
            System.out.println(Constants.MAIN_STATS + ". " + Constants.MAIN_STATS_TITLE);
            System.out.println(Constants.MAIN_HELP + ". " + Constants.MAIN_HELP_TITLE);
            System.out.println(Constants.MAIN_EXIT + ". " + Constants.MAIN_EXIT_TITLE);

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
                    System.err.println("Introdueixi una opció de la llista");
                    break;
            }

        } while (true);
    }

    private void showPlayMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Mastermind: Play Game");
            System.out.println(Constants.PLAY_NEW_GAME + ". " + Constants.PLAY_NEW_GAME_TITLE);
            System.out.println(Constants.PLAY_CONTINUE_GAME + ". " + Constants.PLAY_CONTINUE_GAME_TITLE);
            System.out.println(Constants.PLAY_STATS + ". " + Constants.PLAY_STATS_TITLE);
            System.out.println(Constants.PLAY_HELP + ". " + Constants.PLAY_HELP_TITLE);
            System.out.println(Constants.PLAY_BACK + ". " + Constants.PLAY_BACK_TITLE);

            switch (scanner.nextInt()) {
                case Constants.PLAY_NEW_GAME:
                    newGame(user);
                    break;
                case Constants.PLAY_CONTINUE_GAME:
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
                    System.err.println("Introdueixi una opció de la llista");
                    break;
            }
        } while (true);
    }

    private void newGame(User user) {

    }

    private void continueGame(User user) {

    }

    private void showStatsMenu() {

    }

    private void showHelpMenu() {

    }

    private void login() {
        System.out.println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();

        if (!userController.exists(username)) {
            System.err.println(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        User user = null;
        for (int i = 0; user == null && i < 3; i++) {
            if (i > 0) System.err.println("Contrasenya erronea!");
            System.out.println("Introdueixi la seva contrasenya");
            user = userController.login(username, scanner.next());
        }

        if (user == null) {
            System.err.println("Contrasenya erronea 3 cops");
        } else showPlayMenu(user);
    }

    private void register() {
        System.out.println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String username =  scanner.next();
        String password1, password2;
        do {
            System.out.println("Introdueixi la seva contrasenya");
            password1 = scanner.next();
            System.out.println("Repeteixi la seva contrasenya");
            password2 = scanner.next();
        } while (!password1.equals(password2));

        User user = new User(username, password1);
        if (userController.insert(user)) {
            showPlayMenu(user);
        } else {
            System.err.println("Ja existeix l'usuari");
        }
    }
}
