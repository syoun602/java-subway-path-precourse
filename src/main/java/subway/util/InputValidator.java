package subway.util;

public class InputValidator {
    private static final String NEW_LINE = "\n";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String MAIN_SCREEN_INPUT_FORMAT = "[1Q]";
    private static final String INVALID_SCREEN_INPUT = "선택할 수 없는 기능입니다.";
    private static final String INVALID_STATION_NAME = "잘못된 역 이름입니다.";

    public static void validateMainScreenInput(String input) {
        if (!input.matches(MAIN_SCREEN_INPUT_FORMAT)) {
            throw new IllegalArgumentException(INVALID_SCREEN_INPUT);
        }
    }
}
