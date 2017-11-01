package domain.model.player.computer;

import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.ComputerPlayer;


import java.util.ArrayList;


public class FiveGuessComputer extends ComputerPlayer {

    public FiveGuessComputer(Role role) {
        super(role);
    }


    private Row<ColorPeg> correctMakerGuess;

    //private Row<ControlPeg> controlguess;

    private Row<ColorPeg> guess = new Row<>();

    int quantityofcombinations;

    private ArrayList<Row<ColorPeg>> possibleCombinations = new ArrayList<>(); //conte totes les combinacions que poden ser la correcta

    private ArrayList<Row<ColorPeg>> possibleGuess; // conte totes les combinacions que no hem provat

    private ControlPair controlpair;

    private int maximhit;

    private Row<ColorPeg> maximhitcombination;





    @Override
    public String getName() {
        return "FiveGuess";
    }




    @Override
    public Row<ColorPeg> makerGuess(int pegs, int colors) {
        correctMakerGuess = randomRow(pegs, colors);
        return correctMakerGuess;
    }

    public void backtracking(int position, int pegs, int colors, Row<ColorPeg> combination){
        if (combination.size() == pegs) {
            //possibleGuess.add(combination);
            possibleCombinations.add(combination);
        }
        else {
            for (int i = 1; i <= colors; ++i) {
                Row<ColorPeg> combi = new Row<ColorPeg>();
                combi.addAll(combination);
                combi.add(position, new ColorPeg(i));
                backtracking(position+1, pegs, colors, combi);

            }

        }
    }
    public void maximscore (Row<ColorPeg> nextguess){
        int count = 0;
        ArrayList<ControlScore> allscores = new ArrayList<>();

        for (Row<ColorPeg> combination : possibleCombinations) {
            Row<ControlPeg> control = compareGuess(combination, nextguess);
            ControlPair possiblecontrol = converttoPair(control);
            if (allscores.isEmpty()) {
                ControlScore scorecontrol = new ControlScore(possiblecontrol,1);
                allscores.add(scorecontrol);
            } else {
                boolean exists = false;
                for (ControlScore a : allscores) {
                    if (a.getFirst().equals(possiblecontrol)) {
                        a.increasescore();
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    ControlScore scorecontrol = new ControlScore(possiblecontrol,1);
                    allscores.add(scorecontrol);;
                }

            }

        }
        int maxim = 0;
        for (ControlScore a : allscores) {
            if (a.getSecond() > maxim ) {
                maxim = a.getSecond();
            }
        }
        if (maxim > maximhit) {
            maximhit = maxim;
            maximhitcombination  = new Row<ColorPeg>();
            maximhitcombination.add(0, nextguess.get(0));
            maximhitcombination.add(1, nextguess.get(1));
            maximhitcombination.add(2, nextguess.get(2));
            maximhitcombination.add(3, nextguess.get(3));
        }

    }

    public ControlPair converttoPair(Row<ControlPeg> controlrow) {
        int blacks = 0;
        int whites = 0;
        ControlPair res = new ControlPair(blacks, whites);
        for (ControlPeg peg : controlrow) {
            if (peg.getType() == ControlPeg.Type.BLACK) {
                res.increaseblacks();
            } else if (peg.getType() == ControlPeg.Type.WHITE) {
                res.increasewhites();
            }
        }
        return res;
    }

    @Override
    public Row<ColorPeg> breakerGuess(int pegs, int colors){
        if (possibleCombinations.isEmpty()) {
            Row<ColorPeg> combination = new Row<>();
            backtracking(0, pegs, colors, combination);
            possibleGuess = new ArrayList<>(possibleCombinations);
            quantityofcombinations = possibleCombinations.size();
            guess.add(new ColorPeg(1));
            guess.add(new ColorPeg(1));
            guess.add(new ColorPeg(2));
            guess.add(new ColorPeg(2));
        }

        else {
            int guessscore = 0;
            ArrayList<Row<ColorPeg>> auxiliarcombinations = new ArrayList<>(possibleCombinations);
            for (Row<ColorPeg> combination : auxiliarcombinations) {
                Row<ControlPeg> aux = compareGuess(combination, guess);
                ControlPair obtainedcontrol = converttoPair(aux);
                if (!obtainedcontrol.equals(controlpair)) {
                    possibleCombinations.remove(combination);
                }

            }

            maximhitcombination = new Row<>();
            guessscore = 0;
            //alexisisnull
            //pauthebest
            for (Row<ColorPeg> combination : possibleGuess) {
                maximhit = 0;
                maximscore(combination);
                int minimumeliminated = quantityofcombinations - maximhit;
                if (guessscore < minimumeliminated) {
                    guessscore = minimumeliminated;
                    guess  = new Row<ColorPeg>();
                    guess.add(maximhitcombination.get(0));
                    guess.add(maximhitcombination.get(1));
                    guess.add(maximhitcombination.get(2));
                    guess.add(maximhitcombination.get(3));
                }
                else if ((guessscore == minimumeliminated) && (!possibleCombinations.contains(guess)) && possibleCombinations.contains(maximhitcombination)){
                    guessscore = minimumeliminated;
                    guess  = new Row<ColorPeg>();
                    guess.add(0, maximhitcombination.get(0));
                    guess.add(1, maximhitcombination.get(1));
                    guess.add(2, maximhitcombination.get(2));
                    guess.add(3, maximhitcombination.get(3));
                }
            }
        }

        possibleGuess.remove(guess);
        return guess;
    }

    @Override
    public Row<ControlPeg> scoreGuess(Row<ColorPeg> guess) {
        return compareGuess(correctMakerGuess, guess);
    }

    @Override
    public void receiveControl(Row<ControlPeg> control) {
        controlpair = converttoPair(control);
    }
}
