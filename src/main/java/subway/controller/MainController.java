package subway.controller;

import subway.InitialSetting;

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
    }
}
