package subway.controller;

import subway.InitialSetting;
import subway.util.InputValidator;
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
        } while (processInput(input));
    }

    private boolean processInput(String mainScreenInput) {
        if (mainScreenInput.equals("1")) {
            PathController.getInstance().run();
            return true;
        }
        return !mainScreenInput.equals("Q");
    }

    private String mainScreenInput() {
        while (true) {
            try {
                String input = InputScreen.getInput();
                InputValidator.validateMainScreenInput(input);
                return input;
            } catch (IllegalArgumentException e) {
                ResultScreen.printErrorMessage(e);
            }
        }
    }
}
