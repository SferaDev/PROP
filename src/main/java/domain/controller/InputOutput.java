package domain.controller;

public interface InputOutput {
    // Inputs
    int inputControlBlacks(int pegs);
    int inputControlWhites(int pegs);

    int[] inputColorRow(int pegs, int colors);

    // Outputs
    void outputControlRow(int blacks, int whites);

    void outputColorRow(String row);

    void outputMessage(String message);

    void notifyInvalidInput();
}
