package subway.domain;

import java.util.List;

public class Result {
    private List<String> stationNames;
    private int distance;
    private int timeCost;

    public Result(List<String> vertexList, List<Integer> distanceAndTime) {
        this.stationNames = vertexList;
        this.distance = distanceAndTime.get(0);
        this.timeCost = distanceAndTime.get(1);
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
