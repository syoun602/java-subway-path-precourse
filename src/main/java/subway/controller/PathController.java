package subway.controller;

import subway.service.StationService;
import subway.util.InputValidator;
import subway.view.InputScreen;
import subway.view.PathScreen;
import subway.view.ResultScreen;

public class PathController {
    private static PathController instance;
    private static StationService stationService;

    public static PathController getInstance() {
        if (instance == null) {
            instance = new PathController();
            stationService = new StationService();
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
                String input = InputScreen.getInput();
                InputValidator.validatePathScreenInput(input);
                return input;
            } catch (IllegalArgumentException e) {
                ResultScreen.printErrorMessage(e);
            }
        }
    }

    private boolean processInput(String stationScreenInput) {
        return true;
    }
}
