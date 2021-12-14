package subway.view;

import subway.domain.Result;

public class ResultScreen {
    private static final String NEW_LINE = "\n";
    private static final String INFO_MESSAGE = "[INFO] ";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String RESULT_MESSAGE = "## 조회 결과";
    private static final String DASH = "---";
    private static final String TOTAL_DISTANCE = "총 거리: ";
    private static final String TOTAL_TIME_COST = "총 소요 시간: ";
    private static final String KM = "km";
    private static final Object MINUTE = "분";


    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_MESSAGE + e.getMessage());

    }

    public static void printResult(Result result) {
        System.out.println(RESULT_MESSAGE);
        System.out.println(INFO_MESSAGE + DASH);
        System.out.println(INFO_MESSAGE + TOTAL_DISTANCE + result.getDistance() + KM);
        System.out.println(INFO_MESSAGE + TOTAL_TIME_COST + result.getTimeCost() + MINUTE);
        System.out.println(INFO_MESSAGE + DASH);
        result.getStationNames().forEach(stationName -> System.out.println(INFO_MESSAGE + stationName));
        System.out.println();
    }
}
