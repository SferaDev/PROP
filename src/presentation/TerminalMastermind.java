package presentation;

import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import presentation.controller.TerminalController;
import presentation.utils.LocaleUtils;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;
import presentation.utils.TimeUtils;
import resources.strings.TerminalConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * The type Terminal app.
 *
 * @author Alexis Rico Carreto
 */
public class TerminalMastermind {
    /**
     * Starts the Mastermind in a terminal
     */
    public void startTerminalApplication() {
        TerminalController.getInstance().setGameInterface();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.MAIN_MENU);
        builder.addOption(TerminalConstants.MAIN_REGISTER, this::register);
        builder.addOption(TerminalConstants.MAIN_LOGIN, this::login);
        builder.addOption(TerminalConstants.MAIN_STATS, this::showStats);
        builder.addOption(TerminalConstants.EXIT, builder::finishExecution);
        builder.defaultError(TerminalConstants.ERROR_INPUT);
        builder.execute();
    }

    private void showPlayMenu(String userName) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.PLAY_MENU + userName);
        builder.addOption(TerminalConstants.PLAY_NEW_GAME, () -> showNewGameMenu(userName));
        builder.addOption(TerminalConstants.PLAY_PREV_GAME, () -> showContinueGameMenu(userName));
        builder.addOption(TerminalConstants.PLAY_STATS, this::showStats);
        builder.addOption(TerminalConstants.USER_MENU, () -> showUserMenu(builder, userName));
        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.defaultError(TerminalConstants.ERROR_INPUT);
        builder.execute();
    }

    private void showNewGameMenu(String userName) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.NEW_GAME_MENU);
        builder.addOption(TerminalConstants.NEW_GAME_BREAKER, () -> newBreakerGame(userName));
        builder.addOption(TerminalConstants.NEW_GAME_MAKER, () -> newMakerGame(userName));
        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.defaultError(TerminalConstants.ERROR_INPUT);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void showContinueGameMenu(String userName) {
        ArrayList games = TerminalController.getInstance().requestSavedGames(userName);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.PREVIOUS_GAME_MENU);
        for (Object game : games)
            builder.addOption((String) game, () -> continueGame((String) game));
        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void newBreakerGame(String userName) {
        String role = TerminalConstants.ROLE_BREAKER;
        String computer = TerminalConstants.COMPUTER_DUMMY;
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.NEW_GAME_MENU);
        builder.addOption(TerminalConstants.NEW_GAME_BREAKER_EASY, () -> newGame(userName, role, computer, 4, 4));
        builder.addOption(TerminalConstants.NEW_GAME_BREAKER_AVERAGE, () -> newGame(userName, role, computer, 4, 6));
        builder.addOption(TerminalConstants.NEW_GAME_BREAKER_HARD, () -> newGame(userName, role, computer, 6, 6));
        builder.addOption(TerminalConstants.NEW_GAME_BREAKER_CUSTOM, () -> newGame(userName, role, computer));
        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void newMakerGame(String userName) {
        String role = TerminalConstants.ROLE_MAKER;
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.NEW_GAME_MENU);
        builder.addOption(TerminalConstants.NEW_GAME_FIVEGUESS, () -> newGame(userName, role, TerminalConstants.COMPUTER_FIVEGUESS));
        builder.addOption(TerminalConstants.NEW_GAME_GENETIC, () -> newGame(userName, role, TerminalConstants.COMPUTER_GENETIC));
        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void newGame(String userName, String role, String computerName) {
        // Request pegs and colors
        int pegs, colors;
        do {
            TerminalUtils.getInstance().printLine(TerminalConstants.NEW_GAME_INSERT_PEGS);
            pegs = TerminalUtils.getInstance().readInteger();
        } while (pegs == -1);

        do {
            TerminalUtils.getInstance().printLine(TerminalConstants.NEW_GAME_INSERT_COLORS);
            colors = TerminalUtils.getInstance().readInteger();
        } while (colors == -1);

        newGame(userName, role, computerName, pegs, colors);
    }

    private void newGame(String userName, String role, String computerName, int pegs, int colors) {
        TerminalController.getInstance().requestNewGame(userName, role, computerName, pegs, colors);
    }

    private void continueGame(String game) {
        TerminalController.getInstance().requestContinueGame(game);
    }

    private void showUserMenu(TerminalMenuBuilder originalBuilder, String username) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.USER_MENU);
        builder.addOption(TerminalConstants.USER_CHANGE_PASSWORD, () -> changeUserPassword(username));
        builder.addOption(TerminalConstants.USER_DELETE, () -> deleteUserMenu(originalBuilder, username));
        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void deleteUserMenu(TerminalMenuBuilder originalBuilder, String username) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.USER_DELETE);
        builder.addOption(TerminalConstants.YES, () -> deleteUser(builder, originalBuilder, username));
        builder.addOption(TerminalConstants.NO, builder::finishExecution);
        builder.execute();
    }

    private void deleteUser(TerminalMenuBuilder builder, TerminalMenuBuilder originalBuilder, String username) {
        TerminalController.getInstance().requestDeleteUser(username);
        builder.finishExecution();
        originalBuilder.finishExecution();
    }

    private void changeUserPassword(String username) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.USER_CHANGE_PASSWORD);
        builder.execute();
        TerminalController.getInstance().requestChangePassword(username, inputPassword());
    }

    private void login() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.MAIN_LOGIN);
        builder.execute();
        TerminalUtils.getInstance().printLine(TerminalConstants.INSERT_USERNAME);
        String userName = TerminalUtils.getInstance().readString();

        if (!TerminalController.getInstance().requestExistsUser(userName)) {
            TerminalUtils.getInstance().errorLine(TerminalConstants.ERROR_USER_NOT_FOUND);
            return;
        }

        boolean login = false;
        for (int i = 0; !login && i < 3; i++) {
            if (i > 0) TerminalUtils.getInstance().errorLine(TerminalConstants.WRONG_PASSWORD);
            TerminalUtils.getInstance().printLine(TerminalConstants.INSERT_PASSWORD);
            try {
                String password = TerminalUtils.getInstance().readString();
                login = TerminalController.getInstance().requestLogin(userName, password);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (!login) {
            TerminalUtils.getInstance().errorLine(TerminalConstants.WRONG_PASSWORD_3_TIMES);
        } else showPlayMenu(userName);
    }

    private void register() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.MAIN_REGISTER);
        builder.execute();
        TerminalUtils.getInstance().printLine(TerminalConstants.INSERT_USERNAME);
        String username = TerminalUtils.getInstance().readString();
        if (TerminalController.getInstance().requestExistsUser(username)) {
            TerminalUtils.getInstance().errorLine(TerminalConstants.USER_ALREADY_EXISTS);
        } else {
            try {
                String password = inputPassword();
                String locale = LocaleUtils.getInstance().getLanguage().name();
                TerminalController.getInstance().requestRegisterUser(username, password, locale);
                showPlayMenu(username);
            } catch (UserAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }

    private String inputPassword() {
        String password1, password2;
        int i = 1;
        do {
            if (i++ > 1) TerminalUtils.getInstance().errorLine(TerminalConstants.PASSWORD_DONT_MATCH);
            TerminalUtils.getInstance().printLine(TerminalConstants.INSERT_PASSWORD);
            password1 = TerminalUtils.getInstance().readString();
            TerminalUtils.getInstance().printLine(TerminalConstants.REPEAT_PASSWORD);
            password2 = TerminalUtils.getInstance().readString();
        } while (!password1.equals(password2));
        return password1;
    }

    private void showStats() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.MAIN_STATS);
        builder.addOption(TerminalConstants.STATS_POINTS, this::showPointStats);
        builder.addOption(TerminalConstants.STATS_TIME, this::showTimeStats);
        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void showPointStats() {
        Map<String, Long> pointRanking = TerminalController.getInstance().requestPointRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.STATS_POINTS);

        for (Map.Entry entry : pointRanking.entrySet()) {
            builder.addDescription(String.format("%-10.10s %14.14s", entry.getKey(), entry.getValue()));
        }

        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void showTimeStats() {
        Map<String, Long> timeRanking = TerminalController.getInstance().requestTimeRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.STATS_TIME);

        ArrayList<String> entries = new ArrayList<>();

        for (Map.Entry<String, Long> entry : timeRanking.entrySet()) {
            String[] gameTitle = entry.getKey().split("-");
            String type = "(" + gameTitle[2] + " " + TerminalConstants.PEGS + " | " + gameTitle[3] + " " +
                    TerminalConstants.COLORS + ")";
            if (Integer.parseInt(gameTitle[2]) > 3 && Integer.parseInt(gameTitle[2]) > 3)
                entries.add(String.format("%-15.15s %-15.15s %-25.25s",
                        TimeUtils.timestampToString(entry.getValue()), gameTitle[0], type));
        }

        Collections.sort(entries);

        for (String string : entries) builder.addDescription(string);

        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.execute();
    }
}
