package domain.model.player.computer;

import domain.model.player.ComputerPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;

public class GeneticComputer extends ComputerPlayer {
    private final int POPULATION_SIZE = 1296;
    private final int GENERATION_SIZE = 500;
    private final int FEASIBLE_CODES_MAX = 1;

    private int currentTurn = 0;
    private int[] blacks, whites;

    private ColorRow[] population = new ColorRow[POPULATION_SIZE];
    private int[] fitness = new int[POPULATION_SIZE];

    private ArrayList<ColorRow> feasibleCodes = new ArrayList<>();
    private ArrayList<ColorRow> gameGuesses = new ArrayList<>();

    private int parentPos = 0;

    public GeneticComputer(Role role, int pegs, int colors, int turns) {
        super(role);

        blacks = new int[turns]; // blacks[i] = numero de negres en el torn i;
        whites = new int[turns]; // whites[i] = numero de blanques en el torn i;
    }

    @Override
    public String getName() {
        return "Genetic";
    }

    private ColorRow breakerInitialGuess(int pegs, int colors) {
        if (pegs == 4 && colors >= 3) {
            ColorRow firstAttempt = new ColorRow();
            firstAttempt.add(new ColorRow.ColorPeg(1));
            firstAttempt.add(new ColorRow.ColorPeg(1));
            firstAttempt.add(new ColorRow.ColorPeg(2));
            firstAttempt.add(new ColorRow.ColorPeg(3));
            gameGuesses.add(firstAttempt);
            return firstAttempt;

        } else if (pegs == 5 && colors >= 8) {
            ColorRow firstAttempt = new ColorRow();
            firstAttempt.add(new ColorRow.ColorPeg(1));
            firstAttempt.add(new ColorRow.ColorPeg(1));
            firstAttempt.add(new ColorRow.ColorPeg(2));
            firstAttempt.add(new ColorRow.ColorPeg(3));
            firstAttempt.add(new ColorRow.ColorPeg(4));
            gameGuesses.add(firstAttempt);
            return firstAttempt;

        } else if (pegs == 6 && colors >= 9) {
            ColorRow firstAttempt = new ColorRow();
            firstAttempt.add(new ColorRow.ColorPeg(1));
            firstAttempt.add(new ColorRow.ColorPeg(1));
            firstAttempt.add(new ColorRow.ColorPeg(2));
            firstAttempt.add(new ColorRow.ColorPeg(2));
            firstAttempt.add(new ColorRow.ColorPeg(3));
            firstAttempt.add(new ColorRow.ColorPeg(4));
            gameGuesses.add(firstAttempt);
            return firstAttempt;

        } else if (pegs == 8 && colors >= 12) {
            ColorRow firstAttempt = new ColorRow();
            firstAttempt.add(new ColorRow.ColorPeg(1));
            firstAttempt.add(new ColorRow.ColorPeg(1));
            firstAttempt.add(new ColorRow.ColorPeg(2));
            firstAttempt.add(new ColorRow.ColorPeg(2));
            firstAttempt.add(new ColorRow.ColorPeg(3));
            firstAttempt.add(new ColorRow.ColorPeg(3));
            firstAttempt.add(new ColorRow.ColorPeg(4));
            firstAttempt.add(new ColorRow.ColorPeg(5));
            gameGuesses.add(firstAttempt);
            return firstAttempt;

        } else {

            // Random entry
            ColorRow firstAttempt = randomRow(pegs, colors);
            gameGuesses.add(firstAttempt);
            return firstAttempt;
        }
    }

    @Override
    public ColorRow breakerGuess(int pegs, int colors) {
        if (currentTurn == 0) return breakerInitialGuess(pegs, colors);
        int generation = 0;
        boolean doCalc = true;
        initPopulation(pegs, colors);
        calculateFitness();
        sortFeasibleByFitness(fitness, population);
        while (feasibleCodes.isEmpty()) {
            while (doCalc && generation <= GENERATION_SIZE && feasibleCodes.size() <= FEASIBLE_CODES_MAX) {
                evolvePopulation(pegs, colors);
                calculateFitness();
                sortFeasibleByFitness(fitness, population);
                doCalc = addToFeasibleCodes();
                generation++;
            }
        }
        ColorRow guess = feasibleCodes.get((int) (Math.random() * feasibleCodes.size()));
        gameGuesses.add(guess);
        //++currentTurn;
        return guess;
    }

    @Override
    public void receiveControl(ControlRow control) {
        blacks[currentTurn] = control.getBlacks();
        whites[currentTurn] = control.getWhites();
        ++currentTurn;
    }

    private void initPopulation(int pegs, int colors) {
        feasibleCodes.clear();
        for (int i = 0; i < POPULATION_SIZE; ++i) {
            population[i] = randomRow(pegs, colors);
        }
    }

    private void calculateFitness() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            int x = 0;
            int y = 0;
            for (int j = 0; j < gameGuesses.size(); j++) {
                ControlRow compare = compareGuess(population[i], gameGuesses.get(j));
                x += Math.abs(compare.getBlacks() - blacks[j]);
                y += Math.abs(compare.getWhites() - whites[j]);
            }
            fitness[i] = (x + y);
        }

    }

    private void sortFeasibleByFitness(int[] fitness, ColorRow[] pop) {
        sort(fitness, pop, 0, fitness.length - 1);
    }

    private void sort(int[] fitness, ColorRow[] pop, int low, int up) {
        int p = (low + up) / 2;
        if (up > low) {
            int pn = divide(fitness, pop, low, up, p);
            sort(fitness, pop, low, pn - 1);
            sort(fitness, pop, pn + 1, up);
        }
    }

    private int divide(int[] fitness, ColorRow[] pop, int low, int up, int pivot) {
        int pn = low;
        int pv = fitness[pivot];
        swap(fitness, pivot, up);
        swap(pop, pivot, up);
        for (int i = low; i < up; i++) {
            if (fitness[i] <= pv) {
                swap(fitness, pn, i);
                swap(pop, pn++, i);
            }
            swap(fitness, up, pn);
            swap(pop, up, pn);
        }
        return pn;
    }

    private void swap(int[] fitness, int a, int b) {
        int tmp = fitness[a];
        fitness[a] = fitness[b];
        fitness[b] = tmp;
    }

    private void swap(ColorRow[] pop, int a, int b) {
        ColorRow tmp = pop[a];
        pop[a] = pop[b];
        pop[b] = tmp;
    }

    private void evolvePopulation(int pegs, int colors) {/**/

        ColorRow[] newPopulation = new ColorRow[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            newPopulation[i] = new ColorRow();
        }
        for (int i = 0; i < POPULATION_SIZE; i += 2) {
            if ((int) (Math.random() * 2) == 0) {
                xOver1(newPopulation, i, i + 1, pegs);
            } else {
                xOver2(newPopulation, i, i + 1, pegs);
            }
        }

        for (int i = 0; i < POPULATION_SIZE; i++) {
            if ((int) (Math.random() * 100) < 3) {
                mutation(newPopulation, i, pegs, colors);
            } else if ((int) (Math.random() * 100) < 3) {
                permutation(newPopulation, i, pegs);
            } else if ((int) (Math.random() * 100) < 2) {
                inversion(newPopulation, i, pegs);
            }
        }

        doubleToRnd(newPopulation, pegs, colors);

        population = newPopulation;
    }

    private boolean addToFeasibleCodes() {
        outer:
        for (int i = 0; i < POPULATION_SIZE; i++) {
            for (int j = 0; j < currentTurn; j++) {
                ControlRow compare = compareGuess(population[i], gameGuesses.get(j));

                if (compare.getBlacks() != blacks[j] || compare.getWhites() != whites[j]) {
                    continue outer;
                }
            }

            if (feasibleCodes.size() < FEASIBLE_CODES_MAX) {
                if (!feasibleCodes.contains(population[i])) {
                    feasibleCodes.add(population[i]);
                    if (feasibleCodes.size() < FEASIBLE_CODES_MAX) {
                        return false;
                    }
                }
            } else {
                // E is full.
                return false;
            }
        }
        return true;
    }

    private void xOver1(ColorRow[] newPopulation, int child1Pos, int child2Pos, int pegs) {
        int mother = getParentPos();
        int father = getParentPos();
        int sep = ((int) (Math.random() * pegs)) + 1;

        for (int j = 0; j < pegs; j++) {
            if (j <= sep) {
                newPopulation[child1Pos].add(j, population[mother].get(j));
                newPopulation[child2Pos].add(j, population[father].get(j));
            } else {
                newPopulation[child1Pos].add(j, population[father].get(j));
                newPopulation[child2Pos].add(j, population[mother].get(j));
            }
        }
    }

    private void xOver2(ColorRow[] newPopulation, int child1Pos, int child2Pos, int pegs) {
        int mother = getParentPos();
        int father = getParentPos();
        int sep1;
        int sep2;

        sep1 = ((int) (Math.random() * pegs)) + 1;
        sep2 = ((int) (Math.random() * pegs)) + 1;

        if (sep1 > sep2) {
            int temp = sep1;
            sep1 = sep2;
            sep2 = temp;
        }

        for (int i = 0; i < pegs; i++) {
            if (i <= sep1 || i > sep2) {
                newPopulation[child1Pos].add(i, population[mother].get(i));
                newPopulation[child2Pos].add(i, population[father].get(i));
            } else {
                newPopulation[child1Pos].add(i, population[father].get(i));
                newPopulation[child2Pos].add(i, population[mother].get(i));
            }
        }

    }

    private int getParentPos() {
        parentPos += (int) (Math.random() * 7);
        if (parentPos < POPULATION_SIZE / 5) {
            return parentPos;
        } else {
            parentPos = 0;
        }
        return parentPos;
    }

    private void mutation(ColorRow[] newPopulation, int popPos, int pegs, int colors) {
        int pos = (int) (Math.random() * pegs);
        newPopulation[popPos].remove(pos);
        newPopulation[popPos].add(pos, new ColorRow.ColorPeg((int) (Math.random() * colors)));
    }

    private void permutation(ColorRow[] newPopulation, int popPos, int pegs) {
        int pos1 = (int) (Math.random() * pegs);
        int pos2 = (int) (Math.random() * pegs);
        ColorRow.ColorPeg tmp = new ColorRow.ColorPeg(newPopulation[popPos].get(pos1).getColor());
        ColorRow tempRow = new ColorRow();
        for (int aux = 0; aux < pegs; ++aux) {
            tempRow.add(new ColorRow.ColorPeg(newPopulation[popPos].get(aux).getColor()));
        }
        newPopulation[popPos].remove(pos1);
        newPopulation[popPos].add(pos1,
                tempRow.get(pos2));
        newPopulation[popPos].remove(pos2);
        newPopulation[popPos].add(pos2, tmp);
    }

    private void inversion(ColorRow[] newPopulation, int popPos, int pegs) {
        int pos1 = (int) (Math.random() * pegs);
        int pos2 = (int) (Math.random() * pegs);

        if (pos2 < pos1) {
            int tmp = pos2;
            pos2 = pos1;
            pos1 = tmp;
        }

        for (int i = 0; i < (pos2 - pos1) / 2; i++) {
            ColorRow.ColorPeg tmp = new ColorRow.ColorPeg(newPopulation[popPos].get(pos1 + i).getColor());
            newPopulation[popPos].add(pos1 + i,
                    newPopulation[popPos].get(pos2 - i));
            newPopulation[popPos].remove(pos1 + i + 1);
            //newPopulation[popPos].remove(pos2 - i);
            newPopulation[popPos].add(pos2 - i, tmp);
            newPopulation[popPos].remove(pos2 - i + 1);
        }
    }

    private void doubleToRnd(ColorRow[] newPopulation, int pegs, int colors) {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (lookForSame(newPopulation, i)) {
                newPopulation[i] = randomRow(pegs, colors);
            }
        }
    }

    private boolean lookForSame(ColorRow[] newPopulation, int popPos) {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (population[popPos].equals(newPopulation[popPos])) {
                return true;
            }
        }
        return false;
    }

}
