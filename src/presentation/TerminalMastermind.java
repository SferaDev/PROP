package presentation;

import domain.controller.DomainController;
import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import presentation.controller.LocaleController;
import presentation.controller.receiver.TerminalReceiver;
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
    private final DomainController domainController = DomainController.getInstance();
    private final TerminalUtils terminalUtils = TerminalUtils.getInstance();

    /**
     * Starts the Mastermind in a terminal
     */
    public void startTerminalApplication() {
        domainController.setGameInterface(new TerminalReceiver());

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
        ArrayList games = domainController.getGameController().getAllGames(userName);
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
            terminalUtils.printLine(TerminalConstants.NEW_GAME_INSERT_PEGS);
            pegs = terminalUtils.readInteger();
        } while (pegs == -1);

        do {
            terminalUtils.printLine(TerminalConstants.NEW_GAME_INSERT_COLORS);
            colors = terminalUtils.readInteger();
        } while (colors == -1);

        newGame(userName, role, computerName, pegs, colors);
    }

    private void newGame(String userName, String role, String computerName, int pegs, int colors) {
        domainController.getGameController().startNewGame(userName, computerName, role, pegs, colors, 12);
    }

    private void continueGame(String game) {
        domainController.getGameController().continueGame(game);
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
        domainController.getUserController().deleteUser(username);
        builder.finishExecution();
        originalBuilder.finishExecution();
    }

    private void changeUserPassword(String username) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.USER_CHANGE_PASSWORD);
        builder.execute();
        domainController.getUserController().changePassword(username, inputPassword());
    }

    private void login() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.MAIN_LOGIN);
        builder.execute();
        terminalUtils.printLine(TerminalConstants.INSERT_USERNAME);
        String userName = terminalUtils.readString();

        if (!domainController.getUserController().existsUser(userName)) {
            terminalUtils.errorLine(TerminalConstants.ERROR_USER_NOT_FOUND);
            return;
        }

        boolean login = false;
        for (int i = 0; !login && i < 3; i++) {
            if (i > 0) terminalUtils.errorLine(TerminalConstants.WRONG_PASSWORD);
            terminalUtils.printLine(TerminalConstants.INSERT_PASSWORD);
            try {
                login = domainController.getUserController().loginUser(userName, terminalUtils.readString());
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (!login) {
            terminalUtils.errorLine(TerminalConstants.WRONG_PASSWORD_3_TIMES);
        } else showPlayMenu(userName);
    }

    private void register() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.MAIN_REGISTER);
        builder.execute();
        terminalUtils.printLine(TerminalConstants.INSERT_USERNAME);
        String username = terminalUtils.readString();
        if (domainController.getUserController().existsUser(username)) {
            terminalUtils.errorLine(TerminalConstants.USER_ALREADY_EXISTS);
        } else {
            try {
                domainController.getUserController().createUser(username, inputPassword(),
                        LocaleController.getInstance().getLanguage().name());
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
            if (i++ > 1) terminalUtils.errorLine(TerminalConstants.PASSWORD_DONT_MATCH);
            terminalUtils.printLine(TerminalConstants.INSERT_PASSWORD);
            password1 = terminalUtils.readString();
            terminalUtils.printLine(TerminalConstants.REPEAT_PASSWORD);
            password2 = terminalUtils.readString();
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
        Map<String, Long> pointRanking = domainController.getStatController().getPointRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.STATS_POINTS);

        for (Map.Entry entry : pointRanking.entrySet()) {
            builder.addDescription(String.format("%-10.10s %14.14s", entry.getKey(), entry.getValue()));
        }

        builder.addOption(TerminalConstants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void showTimeStats() {
        Map<String, Long> timeRanking = domainController.getStatController().getTimeRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(TerminalConstants.APP_TITLE + ": " + TerminalConstants.STATS_TIME);

        ArrayList<String> entries = new ArrayList<>();

        for (Map.Entry<String, Long> entry : timeRanking.entrySet()) {
            String[] gameTitle = entry.getKey().split("-");
            String type = "(" + gameTitle[2] + " " + TerminalConstants.PEGS + " | " + gameTitle[3] + " " + TerminalConstants.COLORS + ")";
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
