package subway.view;

public class ResultScreen {
    private static final String NEW_LINE = "\n";
    private static final String INFO_MESSAGE = "[INFO]";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String INPUT_SOURCE_STATION = "## 출발역을 입력하세요.";
    private static final String INPUT_DESTINATION_STATION = "## 도착역을 입력하세요.";
    private static final String RESULT_MESSAGE = "## 조회 결과";

    public static void printError(IllegalArgumentException e) {
        System.out.println(ERROR_MESSAGE + e.getMessage());

    }
}
