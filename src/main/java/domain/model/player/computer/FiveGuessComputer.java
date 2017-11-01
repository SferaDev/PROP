package domain.model.player.computer;

import domain.model.player.ComputerPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;

public class FiveGuessComputer extends ComputerPlayer {

    private int totalCombinations;

    private ColorRow guess = new ColorRow();

    private ArrayList<ColorRow> possibleCombinations = new ArrayList<>();
    private ArrayList<ColorRow> possibleGuess;

    private ControlRow controlRow;
    private int maxHit;
    private ColorRow maxHitCombination;

    public FiveGuessComputer(Role role) {
        super(role);
    }

    @Override
    public String getName() {
        return "FiveGuess";
    }

    private void backtracking(int position, int pegs, int colors, ColorRow combination) {
        if (combination.size() == pegs) {
            possibleCombinations.add(combination);
        } else {
            for (int i = 1; i <= colors; ++i) {
                ColorRow newCombination = new ColorRow();
                newCombination.addAll(combination);
                newCombination.add(position, new ColorRow.ColorPeg(i));
                backtracking(position + 1, pegs, colors, newCombination);
            }

        }
    }

    private void maxScore(ColorRow nextGuess) {
        int count = 0;
        ArrayList<ControlScore> allScores = new ArrayList<>();

        for (ColorRow combination : possibleCombinations) {
            ControlRow control = compareGuess(combination, nextGuess);
            if (allScores.isEmpty()) {
                ControlScore controlScore = new ControlScore(control, 1);
                allScores.add(controlScore);
            } else {
                boolean exists = false;
                for (ControlScore a : allScores) {
                    if (a.getControlRow().equals(control)) {
                        a.setScore(a.getScore() + 1);
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    ControlScore scorecontrol = new ControlScore(control, 1);
                    allScores.add(scorecontrol);
                }
            }
        }

        int max = 0;
        for (ControlScore a : allScores) {
            if (a.getScore() > max) {
                max = a.getScore();
            }
        }

        if (max > maxHit) {
            maxHit = max;
            maxHitCombination = new ColorRow();
            maxHitCombination.add(0, nextGuess.get(0));
            maxHitCombination.add(1, nextGuess.get(1));
            maxHitCombination.add(2, nextGuess.get(2));
            maxHitCombination.add(3, nextGuess.get(3));
        }

    }

    @Override
    public ColorRow breakerGuess(int pegs, int colors) {
        if (possibleCombinations.isEmpty()) {
            ColorRow combination = new ColorRow();
            backtracking(0, pegs, colors, combination);
            possibleGuess = new ArrayList<>(possibleCombinations);
            totalCombinations = possibleCombinations.size();
            guess.add(new ColorRow.ColorPeg(1));
            guess.add(new ColorRow.ColorPeg(1));
            guess.add(new ColorRow.ColorPeg(2));
            guess.add(new ColorRow.ColorPeg(2));
        } else {
            int guessScore = 0;
            ArrayList<ColorRow> auxiliarcombinations = new ArrayList<>(possibleCombinations);
            for (ColorRow combination : auxiliarcombinations) {
                ControlRow control = compareGuess(combination, guess);
                if (!control.equals(controlRow)) {
                    possibleCombinations.remove(combination);
                }
            }

            maxHitCombination = new ColorRow();

            for (ColorRow combination : possibleGuess) {
                maxHit = 0;
                maxScore(combination);
                int minEliminated = totalCombinations - maxHit;
                if (guessScore < minEliminated) {
                    guessScore = minEliminated;
                    guess = new ColorRow();
                    guess.add(maxHitCombination.get(0));
                    guess.add(maxHitCombination.get(1));
                    guess.add(maxHitCombination.get(2));
                    guess.add(maxHitCombination.get(3));
                } else if ((guessScore == minEliminated) && (!possibleCombinations.contains(guess)) && possibleCombinations.contains(maxHitCombination)) {
                    guessScore = minEliminated;
                    guess = new ColorRow();
                    guess.add(0, maxHitCombination.get(0));
                    guess.add(1, maxHitCombination.get(1));
                    guess.add(2, maxHitCombination.get(2));
                    guess.add(3, maxHitCombination.get(3));
                }
            }
        }

        possibleGuess.remove(guess);
        return guess;
    }

    @Override
    public void receiveControl(ControlRow control) {
        controlRow = control;
    }

    public class ControlScore {
        private ControlRow mControlRow;
        private int mScore;

        ControlScore(ControlRow control, int score) {
            mControlRow = new ControlRow(control.getBlacks(), control.getWhites());
            mScore = score;
        }

        ControlRow getControlRow() {
            return mControlRow;
        }

        int getScore() {
            return mScore;
        }

        void setScore(int score) {
            mScore = score;
        }
    }
}
