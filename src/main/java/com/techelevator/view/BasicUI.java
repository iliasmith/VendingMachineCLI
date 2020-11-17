package com.techelevator.view;

public interface BasicUI {

    void output(String content);

    void pauseOutput();

    String promptForSelection(String[] options);

    int promptForInt(String prompt);

    String promptForPosition(String prompt);
}
