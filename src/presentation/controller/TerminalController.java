package presentation.controller;

import domain.controller.DomainController;
import domain.model.exceptions.FinishGameException;
import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import presentation.controller.receiver.TerminalGameReceiver;
import presentation.utils.TerminalMenuBuilder;
import resources.strings.TerminalConstants;

import java.util.ArrayList;
import java.util.Map;

/**
 * The type Presentation controller.
 *
 * @author Alexis Rico Carreto
 */
public class TerminalController {
    private static final TerminalController mInstance = new TerminalController();
    private BoardController currentBoard;

    private TerminalController() {
        // Empty private constructor
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TerminalController getInstance() {
        return mInstance;
    }

    /**
     * Gets current board.
     *
     * @return the current board
     */
    public BoardController getCurrentBoard() {
        return currentBoard;
    }

    /**
     * Sets current board.
     *
     * @param currentBoard the current board
     */
    public void setCurrentBoard(BoardController currentBoard) {
        this.currentBoard = currentBoard;
    }

    /**
     * Request Help
     */
    public void requestHelp() {
        DomainController.getInstance().getGameController().provideHelp();
    }

    /**
     * Request Save Game
     *
     * @throws FinishGameException when game finishes
     */
    public void requestSaveGame() throws FinishGameException {
        DomainController.getInstance().getGameController().saveCurrentGame();
    }

    /**
     * Request Quit Game
     *
     * @throws FinishGameException when game finishes
     */
    public void requestQuitGame() throws FinishGameException {
        DomainController.getInstance().getGameController().stopCurrentGame();
    }

    /**
     * Sets the Terminal Game Interface
     */
    public void setGameInterface() {
        DomainController.getInstance().setGameInterface(new TerminalGameReceiver());
    }

    /**
     * Request the saved games
     *
     * @param userName the user name
     * @return the list of saved games of the given user
     */
    public ArrayList requestSavedGames(String userName) {
        return DomainController.getInstance().getGameController().getAllGames(userName);
    }

    /**
     * Request a new game
     *
     * @param userName     the username
     * @param role         the role
     * @param computerName the computer
     * @param pegs         the number of pegs
     * @param colors       the number of colors
     */
    public void requestNewGame(String userName, String role, String computerName, int pegs, int colors) {
        DomainController.getInstance().getGameController().startNewGame(userName, computerName, role, pegs, colors, 12);
    }

    /**
     * Request continue game
     *
     * @param game the game id
     */
    public void requestContinueGame(String game) {
        DomainController.getInstance().getGameController().continueGame(game);
    }

    /**
     * Request delete user
     *
     * @param username the username
     */
    public void requestDeleteUser(String username) {
        DomainController.getInstance().getUserController().deleteUser(username);
    }

    /**
     * Request change password
     *
     * @param username the username
     * @param password the new password
     */
    public void requestChangePassword(String username, String password) {
        DomainController.getInstance().getUserController().changePassword(username, password);
    }

    /**
     * Request if the user exists
     *
     * @param userName the username
     * @return if the user exists
     */
    public boolean requestExistsUser(String userName) {
        return DomainController.getInstance().getUserController().existsUser(userName);
    }

    /**
     * Request login
     *
     * @param userName the username
     * @param password the password
     * @return if we could login
     * @throws UserNotFoundException if the user is not found
     */
    public boolean requestLogin(String userName, String password) throws UserNotFoundException {
        return DomainController.getInstance().getUserController().loginUser(userName, password);
    }

    /**
     * Request register
     *
     * @param username the username
     * @param password the password
     * @param locale   the locale
     * @throws UserAlreadyExistsException if the user already exists
     */
    public void requestRegisterUser(String username, String password, String locale) throws UserAlreadyExistsException {
        DomainController.getInstance().getUserController().createUser(username, password, locale);
    }

    /**
     * Request the Point Ranking
     *
     * @return the point ranking
     */
    public Map<String, Long> requestPointRanking() {
        return DomainController.getInstance().getStatController().getPointRanking();
    }

    /**
     * Request the Time Ranking
     *
     * @return the time ranking
     */
    public Map<String, Long> requestTimeRanking() {
        return DomainController.getInstance().getStatController().getTimeRanking();
    }

    public static class BoardController {
        private final Board mModel;

        /**
         * Instantiates a new Board controller.
         *
         * @param mModel the model
         */
        public BoardController(Board mModel) {
            this.mModel = mModel;
        }

        /**
         * Add turn.
         *
         * @param turn the turn
         */
        public void addTurn(Turn turn) {
            mModel.addTurn(turn);
        }

        /**
         * Add correct guess.
         *
         * @param turn the turn
         */
        public void setCorrectGuess(Turn turn) {
            mModel.setCorrectGuess(turn);
        }

        /**
         * Print terminal.
         */
        public void printTerminal() {
            TerminalMenuBuilder builder = new TerminalMenuBuilder();
            builder.addTitle(mModel.getGameTitle());
            builder.addDescription(TerminalConstants.GAME_HELP_INGAMEHELP);
            builder.addDescription(TerminalConstants.GAME_HELP_SAVE);
            builder.addDescription(TerminalConstants.GAME_HELP_QUIT);
            if (mModel.getCorrectGuess() != null)
                builder.addDescription("\nCombinació correcte: " + mModel.getCorrectGuess());
            builder.addDescription("\nTorns:\n");
            for (Turn turn : mModel.getTurns()) {
                String black = "";
                String white = "";
                if (turn.getBlacks() != -1 && turn.getWhites() != -1) {
                    black = String.valueOf(turn.getBlacks());
                    white = String.valueOf(turn.getWhites());
                }
                builder.addDescription(String.format("%15.15s %16.16s %16.16s",
                        turn.getGuess(), "Negres: " + black, "Blanques: " + white));
            }
            builder.execute();
        }

        /**
         * Add control last turn.
         *
         * @param blacks the blacks
         * @param whites the whites
         */
        public void addControlLastTurn(int blacks, int whites) {
            mModel.addControlLastTurn(blacks, whites);
        }
    }

    /**
     * The type Board.
     *
     * @author Elena Alonso Gonzalez
     */
    public static class Board {
        private final String gameTitle;
        private final ArrayList<Turn> mTurns = new ArrayList<>();
        private Turn mCorrectGuess = null;

        /**
         * Instantiates a new Board.
         *
         * @param title the title
         */
        public Board(String title) {
            gameTitle = title;
        }

        /**
         * Gets game title.
         *
         * @return the game title
         */
        public String getGameTitle() {
            return gameTitle;
        }

        /**
         * Add turn.
         *
         * @param turn the turn
         */
        public void addTurn(Turn turn) {
            if (mTurns.size() > 0) {
                Turn lastTurn = mTurns.get(mTurns.size() - 1);
                if (lastTurn.getBlacks() == -1 && lastTurn.getWhites() == -1)
                    mTurns.remove(lastTurn);
            }
            mTurns.add(turn);
        }

        /**
         * Add correct guess.
         *
         * @return The correct guess
         */
        public Turn getCorrectGuess() {
            return mCorrectGuess;
        }

        /**
         * Add correct guess.
         *
         * @param turn the turn
         */
        public void setCorrectGuess(Turn turn) {
            mCorrectGuess = turn;
        }

        /**
         * Gets turns.
         *
         * @return the turns
         */
        public ArrayList<Turn> getTurns() {
            return mTurns;
        }

        /**
         * Add control last turn.
         *
         * @param blacks the blacks
         * @param whites the whites
         */
        public void addControlLastTurn(int blacks, int whites) {
            addBlacksLastTurn(blacks);
            addWhitesLastTurn(whites);
        }

        /**
         * Add blacks last turn.
         *
         * @param result the result
         */
        private void addBlacksLastTurn(int result) {
            if (mTurns.size() == 0) return;
            mTurns.get(mTurns.size() - 1).setBlacks(result);
        }

        /**
         * Add whites last turn.
         *
         * @param result the result
         */
        private void addWhitesLastTurn(int result) {
            if (mTurns.size() == 0) return;
            mTurns.get(mTurns.size() - 1).setWhites(result);
        }
    }

    /**
     * The type Turn.
     *
     * @author Elena Alonso Gonzalez
     */
    public static class Turn {
        private final String guess;
        private int blacks = -1;
        private int whites = -1;

        /**
         * Instantiates a new Turn.
         *
         * @param guess the guess
         */
        public Turn(String guess) {
            this.guess = guess;
        }

        /**
         * Gets blacks.
         *
         * @return the blacks
         */
        public int getBlacks() {
            return blacks;
        }

        /**
         * Sets blacks.
         *
         * @param blacks the blacks
         */
        public void setBlacks(int blacks) {
            this.blacks = blacks;
        }

        /**
         * Gets whites.
         *
         * @return the whites
         */
        public int getWhites() {
            return whites;
        }

        /**
         * Sets whites.
         *
         * @param whites the whites
         */
        public void setWhites(int whites) {
            this.whites = whites;
        }

        /**
         * Gets guess.
         *
         * @return the guess
         */
        public String getGuess() {
            return guess;
        }

    }
}
