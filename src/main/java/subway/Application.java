package subway;

import subway.SubwayController.SubwayController;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        SubwayController subwayController = SubwayController.getInstance();
        subwayController.run();
    }
}
