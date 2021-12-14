package subway.domain;

public class Section {
    private Station currentStation;
    private Station nextStation;
    private int distance;
    private int timeCost;

    public Section(Station currentStation, Station nextStation, int distance, int timeCost) {
        this.currentStation = currentStation;
        this.nextStation = nextStation;
        this.distance = distance;
        this.timeCost = timeCost;
    }
}
