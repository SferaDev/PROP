package presentation;

import domain.controller.DomainController;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.Constants;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * The type Terminal app.
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
        builder.addOption(Constants.MAIN_HELP, this::showHelp);
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
        builder.addOption(Constants.PLAY_HELP, this::showHelp);
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
        String role = "BREAKER";
        String computer = "DummyComputer";
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: Nou joc");
        builder.addOption("4 Pegs i 4 Colors (Fàcil)", () -> newGame(userName, role, computer, 4, 4));
        builder.addOption("4 Pegs i 6 Colors (Mig)", () -> newGame(userName, role, computer, 4, 6));
        builder.addOption("6 Pegs i 6 Colors (Difícil)", () -> newGame(userName, role, computer, 6, 6));
        builder.addOption("Personalitzat", () -> newGame(userName, role, computer));
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void newMakerGame(String userName) {
        String role = "MAKER";
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.NEW_GAME_MENU);
        builder.addOption(Constants.NEW_GAME_FIVEGUESS, () -> newGame(userName, role, "FiveGuessComputer"));
        builder.addOption(Constants.NEW_GAME_GENETIC, () -> newGame(userName, role, "GeneticComputer"));
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.onExitGoBackToStart(true);
        builder.execute();
    }

    private void newGame(String userName, String role, String computerName) {
        // Request pegs and colors
        int pegs, colors;
        do {
            terminalUtils.printLine("Introdueixi el nombre de fitxes d'una combinació");
            pegs = terminalUtils.readInteger();
        } while (pegs == -1);

        do {
            terminalUtils.printLine("Introdueixi el nombre de colors possibles");
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

    private void showHelp() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: Ajuda");
        builder.addDescription("Per triar una opció ha de marcar el nombre que acompanya a la opció desitjada");
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void login() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: " + Constants.MAIN_LOGIN);
        builder.execute();
        terminalUtils.printLine("Introdueixi el seu nom d'usuari");
        String userName = terminalUtils.readString();

        if (!domainController.getUserController().existsUser(userName)) {
            terminalUtils.errorLine(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        boolean login = false;
        for (int i = 0; !login && i < 3; i++) {
            if (i > 0) terminalUtils.errorLine("Contrasenya erronea!");
            terminalUtils.printLine("Introdueixi la seva contrasenya");
            login = domainController.getUserController().loginUser(userName, terminalUtils.readString());
        }

        if (!login) {
            terminalUtils.errorLine("Contrasenya erronea 3 cops");
        } else showPlayMenu(userName);
    }

    private void register() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: " + Constants.MAIN_REGISTER);
        builder.execute();
        terminalUtils.printLine("Introdueixi el seu nom d'usuari");
        String username = terminalUtils.readString();
        if (domainController.getUserController().existsUser(username)) {
            terminalUtils.errorLine("Ja existeix l'usuari");
        } else {
            String password1, password2;
            int i = 1;
            do {
                if (i++ > 1) terminalUtils.errorLine("No coincideixen!");
                terminalUtils.printLine("Introdueixi la seva contrasenya");
                password1 = terminalUtils.readString();
                terminalUtils.printLine("Repeteixi la seva contrasenya");
                password2 = terminalUtils.readString();
            } while (!password1.equals(password2));

            if (domainController.getUserController().createUser(username, password1)) {
                showPlayMenu(username);
            }
        }
    }

    private void showStats() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: " + Constants.MAIN_STATS);
        builder.addOption(Constants.STATS_POINTS, this::showPointStats);
        builder.addOption(Constants.STATS_TIME, this::showTimeStats);
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void showPointStats() {
        Map<String, Long> pointRanking = domainController.getStatController().getPointRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: " + Constants.STATS_POINTS);

        for (Map.Entry entry : pointRanking.entrySet()) {
            builder.addDescription(String.format("%-10.10s %14.14s", entry.getKey(), entry.getValue()));
        }

        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void showTimeStats() {
        Map<String, Long> timeRanking = domainController.getStatController().getTimeRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: " + Constants.STATS_TIME);

        ArrayList<String> entries = new ArrayList<>();

        for (Map.Entry<String, Long> entry : timeRanking.entrySet()) {
            String[] gameTitle = entry.getKey().split("-");
            String tipus = "(" + gameTitle[2] + " Fitxes | " + gameTitle[3] + " Colors)";
            if (Integer.parseInt(gameTitle[2]) > 3 && Integer.parseInt(gameTitle[2]) > 3)
                entries.add(String.format("%-15.15s %-15.15s %-25.25s",
                        terminalUtils.outputTimestamp(entry.getValue()), gameTitle[0], tipus));
        }

        Collections.sort(entries);

        for (String string : entries) builder.addDescription(string);

        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }
}
