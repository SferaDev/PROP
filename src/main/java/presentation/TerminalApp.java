package presentation;

import domain.controller.DomainController;
import presentation.controller.TerminalController;
import presentation.model.TerminalInputOutput;
import presentation.utils.Constants;
import presentation.utils.TerminalMenuBuilder;

import java.util.ArrayList;
import java.util.Map;

/**
 * The type Terminal app.
 */
public class TerminalApp {

    private DomainController domainController = DomainController.getInstance();
    private TerminalController terminalController = TerminalController.getInstance();

    /**
     * Start application.
     */
    public void startApplication() {
        domainController.setGameInterface(new TerminalInputOutput());
        showMainMenu();
    }

    private void showMainMenu() {
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
        newGame(userName, "BREAKER", "DummyComputer");
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
            terminalController.printLine("Introdueixi el nombre de fitxes d'una combinació");
            pegs = terminalController.readInteger();
        } while (pegs == -1);

        do {
            terminalController.printLine("Introdueixi el nombre de colors possibles");
            colors = terminalController.readInteger();
        } while (colors == -1);

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
        terminalController.printLine("Introdueixi el seu nom d'usuari");
        String userName = terminalController.readString();

        if (!domainController.getUserController().existsUser(userName)) {
            terminalController.errorLine(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        boolean login = false;
        for (int i = 0; !login && i < 3; i++) {
            if (i > 0) terminalController.errorLine("Contrasenya erronea!");
            terminalController.printLine("Introdueixi la seva contrasenya");
            login = domainController.getUserController().loginUser(userName, terminalController.readString());
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

        if (domainController.getUserController().createUser(username, password1)) {
            showPlayMenu(username);
        } else {
            terminalController.errorLine("Ja existeix l'usuari");
        }
    }

    private void showStats() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: Estadístiques");
        builder.addOption("Puntuació", this::showPointStats);
        builder.addOption("Temps", this::showTimeStats);
        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void showPointStats() {
        Map<String, Long> pointRanking = domainController.getStatController().getPointRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: Puntuació");

        for (Map.Entry entry : pointRanking.entrySet()) {
            builder.addDescription(entry.getKey() + ": " + entry.getValue());
        }

        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }

    private void showTimeStats() {
        Map<String, Long> timeRanking = domainController.getStatController().getTimeRanking();

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: Temps");

        for (Map.Entry<String, Long> entry : timeRanking.entrySet()) {
            String[] gameTitle = entry.getKey().split("-");
            String playerName = gameTitle[0] + " (" + gameTitle[2] + " Fitxes | " + gameTitle[3] + " Colors)";
            long elapsed = entry.getValue() / 1000;
            int hours = (int) (elapsed / (3600));
            int minutes = (int) ((elapsed - (hours * 3600)) / 60);
            int seconds = (int) (elapsed - (hours * 3600) - minutes * 60);
            builder.addDescription(playerName + ": " + String.format("%02d:%02d:%02d", hours, minutes, seconds));
        }

        builder.addOption(Constants.BACK, builder::finishExecution);
        builder.execute();
    }
}
