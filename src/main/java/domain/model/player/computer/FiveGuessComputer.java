package domain.model.player.computer;

import domain.model.player.ComputerPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FiveGuessComputer extends ComputerPlayer {

    private int totalCombinations;

    private ColorRow lastGuess;
    private ControlRow currentControl;

    private ArrayList<ColorRow> possibleCombinations = new ArrayList<>();
    private ArrayList<ColorRow> availableGuesses;

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
        Map<ControlRow, Integer> allScores = new HashMap<>();

        for (ColorRow combination : possibleCombinations) {
            ControlRow control = compareGuess(combination, nextGuess);
            if (allScores.isEmpty()) {
                allScores.put(control, 1);
            } else {
                boolean exists = false;
                for (Map.Entry<ControlRow, Integer> a : allScores.entrySet()) {
                    if (a.getKey().equals(control)) {
                        a.setValue(a.getValue() + 1);
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    allScores.put(control, 1);
                }
            }
        }

        int max = 0;
        for (Map.Entry<ControlRow, Integer> a : allScores.entrySet()) {
            if (a.getValue() > max) {
                max = a.getValue();
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
            availableGuesses = new ArrayList<>(possibleCombinations);
            totalCombinations = possibleCombinations.size();
            lastGuess = breakerInitialGuess(pegs, colors);
        } else {
            int guessScore = 0;
            ArrayList<ColorRow> auxCombinations = new ArrayList<>(possibleCombinations);
            for (ColorRow combination : auxCombinations) {
                ControlRow control = compareGuess(combination, lastGuess);
                if (!control.equals(currentControl)) {
                    possibleCombinations.remove(combination);
                }
            }

            maxHitCombination = new ColorRow();

            for (ColorRow combination : availableGuesses) {
                maxHit = 0;
                maxScore(combination);
                int minEliminated = totalCombinations - maxHit;
                if (guessScore < minEliminated) {
                    guessScore = minEliminated;
                    lastGuess = new ColorRow(maxHitCombination);
                } else if ((guessScore == minEliminated) && (!possibleCombinations.contains(lastGuess))
                        && possibleCombinations.contains(maxHitCombination)) {
                    guessScore = minEliminated;
                    lastGuess = new ColorRow(maxHitCombination);
                }
            }
        }

        availableGuesses.remove(lastGuess);
        return lastGuess;
    }

    @Override
    public void receiveControl(ControlRow control) {
        currentControl = control;
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
}
