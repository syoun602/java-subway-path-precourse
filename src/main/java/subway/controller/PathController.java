package subway.controller;

import subway.domain.Result;
import subway.service.PathService;
import subway.util.InputValidator;
import subway.view.InputScreen;
import subway.view.PathScreen;
import subway.view.ResultScreen;

public class PathController {
    private static PathController instance;
    private static PathService pathService;

    public static PathController getInstance() {
        if (instance == null) {
            instance = new PathController();
            pathService = new PathService();
        }
        return instance;
    }

    public void run() {
        String input;
        do {
            PathScreen.print();
            input = stationScreenInput();
        } while (processInput(input));
    }

    public String stationScreenInput() {
        while (true) {
            try {
                InputScreen.printSelectModeMessage();
                String input = InputScreen.getInput();
                InputValidator.validatePathScreenInput(input);
                return input;
            } catch (IllegalArgumentException e) {
                ResultScreen.printErrorMessage(e);
            }
        }
    }

    private boolean processInput(String pathScreenInput) {
        try {
            if (!pathScreenInput.equals("B")) {
                selectMode(pathScreenInput);
            }
            return false;
        } catch (IllegalArgumentException e) {
            ResultScreen.printErrorMessage(e);
            return true;
        }
    }

    private void selectMode(String mode) {
        InputScreen.printInputSource();
        String src = InputScreen.getInput();
        InputScreen.printInputDestination();
        String dest = InputScreen.getInput();

        if (mode.equals("1")) {
            ResultScreen.printResult(pathService.createPathByDistance(src, dest));
        }
        if (mode.equals("2")) {
            pathService.createPathByTime(src, dest);
        }
    }
}
