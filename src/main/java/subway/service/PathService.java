package subway.service;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import subway.domain.Result;
import subway.repository.SectionRepository;
import subway.repository.StationRepository;

import java.util.Arrays;
import java.util.List;

public class PathService {
    private static final String SAME_SOURCE_DESTINATION_MESSAGE = "출발역과 도착역이 동일합니다.";
    private static final String INVALID_SOURCE_STATION_NAME_MESSAGE = "출발역이 존재하지 않습니다.";
    private static final String INVALID_DESTINATION_STATION_NAME_MESSAGE = "도착역이 존재하지 않습니다.";
    private final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph;
    private final WeightedMultigraph<String, DefaultWeightedEdge> timeCostGraph;

    public PathService() {
        distanceGraph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        timeCostGraph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        StationRepository.stations().forEach(station -> {
            distanceGraph.addVertex(station.getName());
            timeCostGraph.addVertex(station.getName());
        });
        SectionRepository.sections().forEach(section -> {
            distanceGraph.setEdgeWeight(distanceGraph.addEdge(
                    section.getSource().getName(), section.getDestination().getName()), section.getDistance());
            timeCostGraph.setEdgeWeight(timeCostGraph.addEdge(
                    section.getSource().getName(), section.getDestination().getName()), section.getTimeCost());
        });
    }

    public Result createPathByDistance(String src, String dest) {
        return getResult(src, dest, distanceGraph);
    }

    public Result createPathByTimeCost(String src, String dest) {
        return getResult(src, dest, timeCostGraph);
    }

    private Result getResult(String src, String dest, WeightedMultigraph<String, DefaultWeightedEdge> graphType) {
        validateStationNames(src, dest);
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graphType);
        GraphPath<String, DefaultWeightedEdge> path = dijkstraShortestPath.getPath(src, dest);
        List<String> vertexList = path.getVertexList();
        return new Result(vertexList, calculateDistanceAndTime(vertexList));
    }

    private List<Integer> calculateDistanceAndTime(List<String> vertexList) {
        List<Integer> list = Arrays.asList(0, 0);
        for (int i = 0; i < vertexList.size() - 1; i++) {
            DefaultWeightedEdge edgeByDistance = distanceGraph.getEdge(vertexList.get(i), vertexList.get(i + 1));
            DefaultWeightedEdge edgeByTimeCost = timeCostGraph.getEdge(vertexList.get(i), vertexList.get(i + 1));
            list.set(0, list.get(0) + (int) distanceGraph.getEdgeWeight(edgeByDistance));
            list.set(1, list.get(1) + (int) timeCostGraph.getEdgeWeight(edgeByTimeCost));
        }
        return list;
    }

    private void validateStationNames(String src, String dest) {
        validateDuplicate(src, dest);
        validateExistence(src, dest);
    }

    private void validateExistence(String src, String dest) {
        if (StationRepository.findByName(src) == null) {
            throw new IllegalArgumentException(INVALID_SOURCE_STATION_NAME_MESSAGE);
        }
        if (StationRepository.findByName(dest) == null) {
            throw new IllegalArgumentException(INVALID_DESTINATION_STATION_NAME_MESSAGE);
        }
    }

    private void validateDuplicate(String src, String dest) {
        if (src.equals(dest)) {
            throw new IllegalArgumentException(SAME_SOURCE_DESTINATION_MESSAGE);
        }
    }
}
