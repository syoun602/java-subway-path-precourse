package subway;

import subway.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InitialSetting {
    private static final List<String> stationNameList = Arrays.asList("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
    private static final List<String> secondLineStationNameList = Arrays.asList("교대역", "강남역", "역삼역");
    private static final int[][] secondLineStationCostList = {{2, 3}, {2, 3}};
    private static final List<String> thirdLineStationNameList = Arrays.asList("교대역", "남부터미널역", "양재역", "매봉역");
    private static final int[][] thirdLineStationCostList = {{3, 2}, {6, 5}, {1, 1}};
    private static final List<String> shinBundangLineStationNameList = Arrays.asList("강남역", "양재역", "양재시민의숲역");
    private static final int[][] shinBundangLineCostList = {{2, 8}, {10, 3}};

    public static void initialize() {
        insertStations();
        insertLines();
        insertSection();
    }

    private static void insertSection() {
        insertSecondSection();
        insertThirdSection();
        insertShinBundangSection();
    }

    private static void insertSecondSection() {
        for (int i = 0; i < secondLineStationCostList.length; i++) {
            SectionRepository.addSection(new Section(
                    StationRepository.findByName(secondLineStationNameList.get(i)),
                    StationRepository.findByName(secondLineStationNameList.get(i+1)),
                    secondLineStationCostList[i][0],
                    secondLineStationCostList[i][0]));
        }
    }

    private static void insertThirdSection() {
        for (int i = 0; i < secondLineStationCostList.length; i++) {
            SectionRepository.addSection(new Section(
                    StationRepository.findByName(thirdLineStationNameList.get(i)),
                    StationRepository.findByName(thirdLineStationNameList.get(i+1)),
                    thirdLineStationCostList[i][0],
                    thirdLineStationCostList[i][0]));
        }
    }

    private static void insertShinBundangSection() {
        for (int i = 0; i < secondLineStationCostList.length; i++) {
            SectionRepository.addSection(new Section(
                    StationRepository.findByName(shinBundangLineStationNameList.get(i)),
                    StationRepository.findByName(shinBundangLineStationNameList.get(i+1)),
                    shinBundangLineCostList[i][0],
                    shinBundangLineCostList[i][0]));
        }
    }

    private static void insertStations() {
        stationNameList.forEach(stationName -> StationRepository.addStation(new Station(stationName)));
    }

    private static void insertLines() {
        LineRepository.addLine(insertSecondLine());
        LineRepository.addLine(insertThirdLine());
        LineRepository.addLine(insertShinBundangLine());
    }

    private static Line insertSecondLine() {
        return new Line("2호선", secondLineStationNameList.stream()
                .map(StationRepository::findByName)
                .collect(Collectors.toList()));
    }

    private static Line insertThirdLine() {
        return new Line("3호선", thirdLineStationNameList.stream()
                .map(StationRepository::findByName)
                .collect(Collectors.toList()));
    }

    private static Line insertShinBundangLine() {
        return new Line("신분당선", shinBundangLineStationNameList.stream()
                .map(StationRepository::findByName)
                .collect(Collectors.toList()));
    }
}
