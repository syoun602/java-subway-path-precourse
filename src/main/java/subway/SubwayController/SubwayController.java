package subway.SubwayController;

import subway.InitialSetting;

public class SubwayController {
    private static SubwayController instance;

    public static SubwayController getInstance() {
        if (instance == null) {
            instance = new SubwayController();
        }
        return instance;
    }

    public void run() {
        InitialSetting.initialize();
    }
}
