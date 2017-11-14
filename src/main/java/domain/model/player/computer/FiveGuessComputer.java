package domain.model.player.computer;

import domain.model.player.ComputerPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Five guess computer
 *
 */
public class FiveGuessComputer extends ComputerPlayer implements java.io.Serializable {

    /**
     * Number of different combinations it is possible to obtain with the number of pegs and colors given
     */
    private int totalCombinations;

    /**
     * The guess tried in the last turn
     */
    private ColorRow lastGuess;
    /**
     * The ControlRow obtained because of the last guess
     */
    private ControlRow currentControl;

    /**
     * An ArrayList that contains all the combinations not discarded that could be the correct guess
     */
    private ArrayList<ColorRow> possibleCombinations = new ArrayList<>();
    /**
     * An ArrayList that contains all the combinations not proved
     */
    private ArrayList<ColorRow> availableGuesses;

    /**
     * Instantiates a new Five guess computer
     * @param role Is the role of the computer
     */
    public FiveGuessComputer(Role role) {
        super(role);
    }


    /**
     * Return the name of the algorithm
     */
    @Override
    public String getName() {
        return "FiveGuess";
    }


    /**
     * Generates all the possible combinations with the number of pegs and colors given
     *
     * @param position is the index of the position that is being added
     * @param pegs is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @param combination is the combination that is being completed to be added to possibleCombinations
     */
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

    private int maxScore(ColorRow nextGuess) {
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

        int maxHit = 0;
        for (Map.Entry<ControlRow, Integer> a : allScores.entrySet()) {
            if (a.getValue() > maxHit) {
                maxHit = a.getValue();
            }
        }

       return maxHit;
    }

    /**
     * Evaluates which is the combination that will eliminate more possible combinations.
     * To do it, first it searches for each combination the minimum eliminated for each ControlRow,
     * and chooses the maximum of these minimums.
     * @param pegs is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the combination that is going to be tried
     */
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


            for (ColorRow combination : availableGuesses) {
                int maxHit = maxScore(combination);
                int minEliminated = totalCombinations - maxHit;
                if (guessScore < minEliminated) {
                    guessScore = minEliminated;
                    lastGuess = new ColorRow(combination);
                } else if ((guessScore == minEliminated) && (!possibleCombinations.contains(lastGuess))
                        && possibleCombinations.contains(combination)) {
                    guessScore = minEliminated;
                    lastGuess = new ColorRow(combination);
                }
            }
        }

        availableGuesses.remove(lastGuess);
        return lastGuess;
    }

    /**
     * Receives the ControlRow given by the computer or the user
     * @param control is the combination given by the computer or the user
     */
    @Override
    public void receiveControl(ControlRow control) {
        currentControl = control;
    }


    /**
     * Choose a first combination to be tried
     * @param pegs is the number of pegs in the combination
     * @param colors is the number of different possible colors in a combination
     * @return the first combination that is going to be tried
     */
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

