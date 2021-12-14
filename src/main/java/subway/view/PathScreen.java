package subway.view;

public class PathScreen {
    private static final String NEW_LINE = "\n";
    private static final String SELECT_PATH_MODE_PROMPT = "## 경로 기준\n" +
            "1. 최단 거리\n" +
            "2. 최소 시간\n" +
            "B. 돌아가기";

    public static void print() {
        System.out.println(SELECT_PATH_MODE_PROMPT + NEW_LINE);
    }
}
