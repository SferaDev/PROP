package presentation.controller;

import presentation.utils.TerminalMenuBuilder;
import resources.strings.TerminalConstants;

import java.util.ArrayList;

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
