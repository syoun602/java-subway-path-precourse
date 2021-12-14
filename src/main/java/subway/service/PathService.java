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
    public Result createPathByDistance(String src, String dest) {
        WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph
                = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        StationRepository.stations().forEach(station -> distanceGraph.addVertex(station.getName()));
        SectionRepository.sections().forEach(section -> distanceGraph.setEdgeWeight(
                distanceGraph.addEdge(section.getSource().getName(), section.getDestination().getName()),
                section.getDistance()));

        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(distanceGraph);
        GraphPath<String, DefaultWeightedEdge> path = dijkstraShortestPath.getPath(src, dest);
        int totalTimeCost = calculateTimeCost(path);
        return new Result(path.getVertexList(), (int)path.getWeight(), totalTimeCost);
    }

    private int calculateTimeCost(GraphPath<String, DefaultWeightedEdge> path) {
        int sum = 0;
        for (DefaultWeightedEdge edge : path.getEdgeList()) {
            List<String> list = Arrays.stream(edge.toString().split(":")).collect(Collectors.toList());
            String edgeSrc = list.get(0).substring(1, list.get(0).length() - 1);
            String edgeDest = list.get(1).substring(1, list.get(1).length() - 1);
            sum += SectionRepository.findByNames(edgeSrc, edgeDest).getTimeCost();
        }
        return sum;
    }

    public void createPathByTime(String src, String dest) {

    }
}
