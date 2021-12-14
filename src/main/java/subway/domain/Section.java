package subway.domain;

public class Section {
    private Station source;
    private Station destination;
    private int distance;
    private int timeCost;

    public Section(Station source, Station destination, int distance, int timeCost) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.timeCost = timeCost;
    }

    public Station getSource() {
        return source;
    }

    public Station getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    public int getTimeCost() {
        return timeCost;
    }
}
