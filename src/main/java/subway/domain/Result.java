package subway.domain;

import java.util.List;

public class Result {
    private List<String> stationNames;
    private int distance;
    private int timeCost;

    public Result(List<String> stationNames, int distance, int timeCost) {
        this.stationNames = stationNames;
        this.distance = distance;
        this.timeCost = timeCost;
    }

    public List<String> getStationNames() {
        return stationNames;
    }

    public int getDistance() {
        return distance;
    }

    public int getTimeCost() {
        return timeCost;
    }
}
