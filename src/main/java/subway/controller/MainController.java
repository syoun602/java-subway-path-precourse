package subway.controller;

import subway.InitialSetting;
import subway.view.InputScreen;
import subway.view.MainScreen;
import subway.view.ResultScreen;

public class MainController {
    private static MainController instance;

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void run() {
        InitialSetting.initialize();
        String input;
        do {
            MainScreen.print();
            input = mainScreenInput();
        } while (processInput());
    }

    private boolean processInput() {
        return true;
    }

    private String mainScreenInput() {
        while (true) {
            try {
                String input = InputScreen.getInput();
                return input;
            } catch (IllegalArgumentException e) {
                ResultScreen.printError(e);
            }
        }
    }
}
