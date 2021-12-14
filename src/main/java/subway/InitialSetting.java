package subway;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InitialSetting {
    private static final List<String> stationNameList = Arrays.asList("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
    private static final List<String> secondLineStationNameList = Arrays.asList("교대역", "강남역", "역삼역");
    private static final List<String> thirdLineStationNameList = Arrays.asList("교대역", "남부터미널역", "양재역", "매봉역");
    private static final List<String> shinBundangLineStationNameList = Arrays.asList("강남역", "양재역", "양재시민의숲역");

    public static void initialize() {
        insertStations();
        insertLines();
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
