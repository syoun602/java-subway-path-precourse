package subway.view;

import java.util.Scanner;

public class InputScreen {
    private static final String NEW_LINE = "\n";
    private static final String SELECT_MODE = "## 원하는 기능을 선택하세요.";
    private static final String INPUT_SOURCE_STATION_MESSAGE = "## 출발역을 입력하세요.";
    private static final String INPUT_DESTINATION_STATION_MESSAGE = "## 도착역을 입력하세요.";
    static Scanner scanner = new Scanner(System.in);

    public static void printSelectModeMessage() {
        System.out.println(SELECT_MODE);
    }

    public static String getInput() {
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }

    public static void printInputSource() {
        System.out.println(INPUT_SOURCE_STATION_MESSAGE);
    }

    public static void printInputDestination() {
        System.out.println(INPUT_DESTINATION_STATION_MESSAGE);
    }
}
