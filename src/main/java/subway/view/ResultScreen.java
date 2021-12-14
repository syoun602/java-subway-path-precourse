package subway.view;

public class ResultScreen {
    private static final String NEW_LINE = "\n";
    private static final String INFO_MESSAGE = "[INFO]";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String RESULT_MESSAGE = "## 조회 결과";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_MESSAGE + e.getMessage());

    }
}
