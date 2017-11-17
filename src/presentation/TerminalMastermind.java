package presentation;

import domain.controller.DomainController;
import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.Constants;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * The type Terminal app.
 *
 * @author Alexis Rico Carreto
 */
public class TerminalMastermind implements Mastermind {

    private final DomainController domainController = DomainController.getInstance();
    private final TerminalUtils terminalUtils = TerminalUtils.getInstance();

    /**
     * Start application.
     */
    @Override
    public void startApplication() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.MAIN_MENU);
        builder.addOption(Constants.MAIN_REGISTER, this::register);
        builder.addOption(Constants.MAIN_LOGIN, this::login);
        builder.addOption(Constants.MAIN_STATS, this::showStats);
        builder.addOption(Constants.EXIT, builder::finishExecution);
        builder.defaultError(Constants.ERROR_INPUT);
        builder.execute();
    }

    @Override
    public void setInputOutPut() {
        domainController.setGameInterface(new TerminalReceiver());
    }

    private void showPlayMenu(String userName) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.PLAY_MENU + userName);
        builder.addOption(Constants.PLAY_NEW_GAME, () -> showNewGameMenu(userName));
        builder.addOption(Constants.PLAY_PREV_GAME, () -> showContinueGameMenu(userName));
        builder.addOption(Constants.PLAY_STATS, this::showStats);
        builder.addOption(Constants.USER_MENU, () -> showUserMenu(builder, userName));
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.defaultError(Constants.ERROR_INPUT);
        builder.execute();
    }

    private void showNewGameMenu(String userName) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.NEW_GAME_MENU);
        builder.addOption(Constants.NEW_GAME_BREAKER, () -> newBreakerGame(userName));
        builder.addOption(Constants.NEW_GAME_MAKER, () -> newMakerGame(userName));
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.defaultError(Constants.ERROR_INPUT);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void showContinueGameMenu(String userName) {
        ArrayList<String> games = domainController.getGameController().getAllGames(userName);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.PREVIOUS_GAME_MENU);
        for (String game : games)
            builder.addOption(game, () -> continueGame(game));
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void newBreakerGame(String userName) {
        String role = Constants.ROLE_BREAKER;
        String computer = Constants.COMPUTER_DUMMY;
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.NEW_GAME_MENU);
        builder.addOption(Constants.NEW_GAME_BREAKER_EASY, () -> newGame(userName, role, computer, 4, 4));
        builder.addOption(Constants.NEW_GAME_BREAKER_AVERAGE, () -> newGame(userName, role, computer, 4, 6));
        builder.addOption(Constants.NEW_GAME_BREAKER_HARD, () -> newGame(userName, role, computer, 6, 6));
        builder.addOption(Constants.NEW_GAME_BREAKER_CUSTOM, () -> newGame(userName, role, computer));
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void newMakerGame(String userName) {
        String role = Constants.ROLE_MAKER;
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.NEW_GAME_MENU);
        builder.addOption(Constants.NEW_GAME_FIVEGUESS, () -> newGame(userName, role, Constants.COMPUTER_FIVEGUESS));
        builder.addOption(Constants.NEW_GAME_GENETIC, () -> newGame(userName, role, Constants.COMPUTER_GENETIC));
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void newGame(String userName, String role, String computerName) {
        // Request pegs and colors
        int pegs, colors;
        do {
            terminalUtils.printLine(Constants.NEW_GAME_INSERT_PEGS);
            pegs = terminalUtils.readInteger();
        } while (pegs == -1);

        do {
            terminalUtils.printLine(Constants.NEW_GAME_INSERT_COLORS);
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
        builder.addTitle(Constants.APP_TITLE + ": " + Constants.USER_MENU);
        builder.addOption(Constants.USER_CHANGE_PASSWORD, () -> changeUserPassword(username));
        builder.addOption(Constants.USER_DELETE, () -> deleteUserMenu(originalBuilder, username));
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void deleteUserMenu(TerminalMenuBuilder originalBuilder, String username) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.APP_TITLE + ": " + Constants.USER_DELETE);
        builder.addOption(Constants.YES, () -> deleteUser(builder, originalBuilder, username));
        builder.addOption(Constants.NO, builder::finishExecution);
        builder.execute();
    }

    private void deleteUser(TerminalMenuBuilder builder, TerminalMenuBuilder originalBuilder, String username) {
        domainController.getUserController().deleteUser(username);
        builder.finishExecution();
        originalBuilder.finishExecution();
    }

    private void changeUserPassword(String username) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.APP_TITLE + ": " + Constants.USER_CHANGE_PASSWORD);
        builder.execute();
        domainController.getUserController().changePassword(username, inputPassword());
    }

    private void login() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.APP_TITLE + ": " + Constants.MAIN_LOGIN);
        builder.execute();
        terminalUtils.printLine(Constants.INSERT_USERNAME);
        String userName = terminalUtils.readString();

        if (!domainController.getUserController().existsUser(userName)) {
            terminalUtils.errorLine(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        boolean login = false;
        for (int i = 0; !login && i < 3; i++) {
            if (i > 0) terminalUtils.errorLine(Constants.WRONG_PASSWORD);
            terminalUtils.printLine(Constants.INSERT_PASSWORD);
            try {
                login = domainController.getUserController().loginUser(userName, terminalUtils.readString());
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (!login) {
            terminalUtils.errorLine(Constants.WRONG_PASSWORD_3_TIMES);
        } else showPlayMenu(userName);
    }

    private void register() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.APP_TITLE + ": " + Constants.MAIN_REGISTER);
        builder.execute();
        terminalUtils.printLine(Constants.INSERT_USERNAME);
        String username = terminalUtils.readString();
        if (domainController.getUserController().existsUser(username)) {
            terminalUtils.errorLine(Constants.USER_ALREADY_EXISTS);
        } else {
            try {
                domainController.getUserController().createUser(username, inputPassword());
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
            if (i++ > 1) terminalUtils.errorLine(Constants.PASSWORD_DONT_MATCH);
            terminalUtils.printLine(Constants.INSERT_PASSWORD);
            password1 = terminalUtils.readString();
            terminalUtils.printLine(Constants.REPEAT_PASSWORD);
            password2 = terminalUtils.readString();
        } while (!password1.equals(password2));
        return password1;
    }

    private void showStats() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.APP_TITLE + ": " + Constants.MAIN_STATS);
        builder.addOption(Constants.STATS_POINTS, this::showPointStats);
        builder.addOption(Constants.STATS_TIME, this::showTimeStats);
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void showPointStats() {
        Map<String, Long> pointRanking = domainController.getStatController().getPointRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.APP_TITLE + ": " + Constants.STATS_POINTS);

        for (Map.Entry entry : pointRanking.entrySet()) {
            builder.addDescription(String.format("%-10.10s %14.14s", entry.getKey(), entry.getValue()));
        }

        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void showTimeStats() {
        Map<String, Long> timeRanking = domainController.getStatController().getTimeRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.APP_TITLE + ": " + Constants.STATS_TIME);

        ArrayList<String> entries = new ArrayList<>();

        for (Map.Entry<String, Long> entry : timeRanking.entrySet()) {
            String[] gameTitle = entry.getKey().split("-");
            String tipus = "(" + gameTitle[2] + " " + Constants.PEGS + " | " + gameTitle[3] + " " + Constants.COLORS + ")";
            if (Integer.parseInt(gameTitle[2]) > 3 && Integer.parseInt(gameTitle[2]) > 3)
                entries.add(String.format("%-15.15s %-15.15s %-25.25s",
                        TerminalUtils.timestampToString(entry.getValue()), gameTitle[0], tipus));
        }

        Collections.sort(entries);

        for (String string : entries) builder.addDescription(string);

        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }
}
