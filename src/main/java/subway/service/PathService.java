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
import java.util.stream.Collectors;

public class PathService {
    private static final String SAME_SOURCE_DESTINATION_MESSAGE = "출발역과 도착역이 동일합니다.";
    private static final String INVALID_SOURCE_STATION_NAME_MESSAGE = "출발역이 존재하지 않습니다.";
    private static final String INVALID_DESTINATION_STATION_NAME_MESSAGE = "도착역이 존재하지 않습니다.";

    public Result createPathByDistance(String src, String dest) {
        validateStationNames(src, dest);
        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph
                = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        StationRepository.stations().forEach(station -> distanceGraph.addVertex(station.getName()));
        SectionRepository.sections().forEach(section -> distanceGraph.setEdgeWeight(
                distanceGraph.addEdge(section.getSource().getName(), section.getDestination().getName()),
                section.getDistance()));

        return createResultByDistance(distanceGraph, src, dest);
    }

    public Result createPathByTimeCost(String src, String dest) {
        validateStationNames(src, dest);
        WeightedMultigraph<String, DefaultWeightedEdge> timeCostGraph
                = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        StationRepository.stations().forEach(station -> timeCostGraph.addVertex(station.getName()));
        SectionRepository.sections().forEach(section -> timeCostGraph.setEdgeWeight(
                timeCostGraph.addEdge(section.getSource().getName(), section.getDestination().getName()),
                section.getTimeCost()));

        return createResultByTimeCost(timeCostGraph, src, dest);
    }

    private Result createResultByDistance(WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph, String src, String dest) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(distanceGraph);
        GraphPath<String, DefaultWeightedEdge> path = dijkstraShortestPath.getPath(src, dest);
        int totalTimeCost = getTotalTimeCost(path);
        return new Result(path.getVertexList(), (int)path.getWeight(), totalTimeCost);
    }

    private Result createResultByTimeCost(WeightedMultigraph<String, DefaultWeightedEdge> timeCostGraph, String src, String dest) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(timeCostGraph);
        GraphPath<String, DefaultWeightedEdge> path = dijkstraShortestPath.getPath(src, dest);
        int totalDistance = getTotalDistance(path);
        return new Result(path.getVertexList(), totalDistance, (int)path.getWeight());
    }

    private int getTotalTimeCost(GraphPath<String, DefaultWeightedEdge> path) {
        int sum = 0;
        for (DefaultWeightedEdge edge : path.getEdgeList()) {
            List<String> list = Arrays.stream(edge.toString().split(":")).collect(Collectors.toList());
            String edgeSrc = list.get(0).substring(1, list.get(0).length() - 1);
            String edgeDest = list.get(1).substring(1, list.get(1).length() - 1);
            sum += SectionRepository.findByNames(edgeSrc, edgeDest).getTimeCost();
        }
        return sum;
    }

    private int getTotalDistance(GraphPath<String, DefaultWeightedEdge> path) {
        int sum = 0;
        for (DefaultWeightedEdge edge : path.getEdgeList()) {
            List<String> list = Arrays.stream(edge.toString().split(":")).collect(Collectors.toList());
            String edgeSrc = list.get(0).substring(1, list.get(0).length() - 1);
            String edgeDest = list.get(1).substring(1, list.get(1).length() - 1);
            sum += SectionRepository.findByNames(edgeSrc, edgeDest).getDistance();
        }
        return sum;
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
