package presentation.controller;

import presentation.model.Board;
import presentation.model.Turn;
import presentation.utils.Constants;
import presentation.utils.TerminalMenuBuilder;

/**
 * The type Presentation controller.
 *
 * @author Alexis Rico Carreto
 */
public class TerminalController {
    private static final TerminalController mInstance = new TerminalController();
    private BoardController currentBoard;

    private TerminalController() {
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
            builder.addDescription(Constants.GAME_HELP_INGAMEHELP);
            builder.addDescription(Constants.GAME_HELP_SAVE);
            builder.addDescription(Constants.GAME_HELP_QUIT);
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
}
