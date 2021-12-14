package subway.view;

public class MainScreen {
    private static final String NEW_LINE = "\n";
    private static final String MAIN_PROMPT = "## 메인 화면\n" +
            "1. 경로 조회\n" +
            "Q. 종료";

    public static void print() {
        System.out.println(MAIN_PROMPT + NEW_LINE);
    }
}
