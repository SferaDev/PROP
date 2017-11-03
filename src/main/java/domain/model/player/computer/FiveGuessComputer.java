package domain.model.player.computer;

import domain.model.player.ComputerPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;

public class FiveGuessComputer extends ComputerPlayer {

    private int totalCombinations;

    private ColorRow lastGuess;

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
                ColorRow newCombination = new ColorRow(combination);
                newCombination.add(position, new ColorRow.ColorPeg(i));
                backtracking(position + 1, pegs, colors, newCombination);
            }

        }
    }

    private void maxScore(ColorRow nextGuess) {
        ArrayList<ControlScore> allScores = new ArrayList<>();

        for (ColorRow combination : possibleCombinations) {
            ControlRow control = compareGuess(combination, nextGuess);
            if (allScores.isEmpty()) {
                allScores.add(new ControlScore(control, 1));
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
                    ControlScore controlScore = new ControlScore(control, 1);
                    allScores.add(controlScore);
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
            maxHitCombination = new ColorRow(nextGuess);
        }

    }

    @Override
    public ColorRow breakerGuess(int pegs, int colors) {
        if (possibleCombinations.isEmpty()) {
            ColorRow combination = new ColorRow();
            backtracking(0, pegs, colors, combination);
            possibleGuess = new ArrayList<>(possibleCombinations);
            totalCombinations = possibleCombinations.size();
            lastGuess = breakerInitialGuess(pegs, colors);
        } else {
            int guessScore = 0;
            ArrayList<ColorRow> auxCombinations = new ArrayList<>(possibleCombinations);
            for (ColorRow combination : auxCombinations) {
                ControlRow control = compareGuess(combination, lastGuess);
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
                    lastGuess = new ColorRow(maxHitCombination);
                } else if ((guessScore == minEliminated) && (!possibleCombinations.contains(lastGuess)) && possibleCombinations.contains(maxHitCombination)) {
                    guessScore = minEliminated;
                    lastGuess = new ColorRow(maxHitCombination);
                }
            }
        }

        possibleGuess.remove(lastGuess);
        return lastGuess;
    }

    @Override
    public void receiveControl(ControlRow control) {
        controlRow = control;
    }

    private ColorRow breakerInitialGuess(int pegs, int colors) {
        ColorRow firstAttempt;
        if (pegs == 4 && colors >= 2) {
            firstAttempt = new ColorRow(1, 1, 2, 2);
        } else if (pegs == 5 && colors >= 3) {
            firstAttempt = new ColorRow(1, 1, 2, 2, 3);
        } else {
            return randomRow(pegs, colors);
        }
        return firstAttempt;
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
