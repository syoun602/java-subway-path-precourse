package subway.controller;

public class PathController {
    private static PathController instance;

    public static PathController getInstance() {
        if (instance == null) {
            instance = new PathController();
        }
        return instance;
    }

    public void run() {

    }
}
