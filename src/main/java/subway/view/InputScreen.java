package subway.view;

import java.util.Scanner;

public class InputScreen {
    private static final String NEW_LINE = "\n";
    private static final String SELECT_MODE = "## 원하는 기능을 선택하세요.";
    static Scanner scanner = new Scanner(System.in);

    public static String getInput() {
        System.out.println(SELECT_MODE);
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }
}
