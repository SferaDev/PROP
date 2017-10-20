package presentation;

import domain.controller.MainController;
import domain.controller.UserController;
import domain.model.*;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.UserPlayer;
import presentation.Constants;
import presentation.utils.TerminalUtils;

import java.util.Scanner;

public class TerminalApp {
    private MainController mainController;
    private UserController userController;

    public void startApplication() {
        mainController = MainController.getInstance();
        userController = mainController.getUserController();

        mainController.setGameInterface(new InputOutput() {
            @Override
            public Row<ControlPeg> inputControlRow(int pegs) {
                Row<ControlPeg> result = new Row<>();
                TerminalUtils.println("Introdueixi combinació de " + pegs + " fitxes de control [B B W -]");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.next().replace(" ", "");
                for (int i = 0; i < pegs; ++i) {
                    if (i < input.length() && input.charAt(i) == 'B') result.add(new ControlPeg(ControlPeg.Type.BLACK));
                    else if (i < input.length() && input.charAt(i) == 'W') result.add(new ControlPeg(ControlPeg.Type.WHITE));
                    else result.add(new ControlPeg(ControlPeg.Type.EMPTY));
                }
                return result;
            }

            @Override
            public Row<ColorPeg> inputColorRow(int pegs, int colors) {
                Row<ColorPeg> result = new Row<>();
                TerminalUtils.println("Introdueixi combinació de " + pegs + " fitxes i " + colors + " colors [1 2 2 1]");
                Scanner scanner = new Scanner(System.in);
                while (result.size() < pegs) {
                    String input = scanner.next().replaceAll("[^1-" + colors + "]", "");
                    for (int i = 0; i < input.length(); i++) {
                        result.add(new ColorPeg(Integer.parseInt(String.valueOf(input.charAt(i)))));
                    }
                }
                return result;
            }

            @Override
            public void outputControlRow(Row<ControlPeg> row) {
                StringBuilder output = new StringBuilder();
                for (ControlPeg peg : row) {
                    if (peg.getType() == ControlPeg.Type.BLACK) output.append("B ");
                    if (peg.getType() == ControlPeg.Type.WHITE) output.append("W ");
                    if (peg.getType() == ControlPeg.Type.EMPTY) output.append("- ");
                }
                TerminalUtils.println(output.toString());
            }

            @Override
            public void outputColorRow(Row<ColorPeg> row) {
                StringBuilder output = new StringBuilder();
                for (ColorPeg peg : row) {
                    output.append(peg.getColor()).append(" ");
                }
                TerminalUtils.println(output.toString());
            }
        });

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

    private void showPlayMenu(User user) {
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
                    TerminalUtils.errorln("Introdueixi una opció de la llista");
                    break;
            }
        } while (true);
    }

    private void newGame(User user) {
        Scanner scanner = new Scanner(System.in);
        Role role = null;
        do {
            TerminalUtils.println(Constants.NEW_MENU_SEPARATOR + "\n" +
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
                    TerminalUtils.errorln("Introdueixi una opció de la llista");
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
        TerminalUtils.println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();

        if (!userController.exists(username)) {
            TerminalUtils.errorln(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        User user = null;
        for (int i = 0; user == null && i < 3; i++) {
            if (i > 0) TerminalUtils.errorln("Contrasenya erronea!");
            TerminalUtils.println("Introdueixi la seva contrasenya");
            user = (User) userController.login(username, scanner.next());
        }

        if (user == null) {
            TerminalUtils.errorln("Contrasenya erronea 3 cops");
        } else showPlayMenu(user);
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

        User user = new User(username, password1);
        if (userController.insert(user)) {
            showPlayMenu(user);
        } else {
            TerminalUtils.errorln("Ja existeix l'usuari");
        }
    }
}
