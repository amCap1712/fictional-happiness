package aoc.y2021;

import aoc.Utils;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Day15 {
    public static void main(String[] args) {
        Graph<Integer, DefaultWeightedEdge> g = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        var weights = Utils.INSTANCE.readInput(2021, 15)
                .stream()
                .map(String::chars)
                .map(s -> s.map(x -> x - 48).toArray())
                .toArray(int[][]::new);

        var vertices = new int[weights.length][weights[0].length];
        int count = 0;
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices[0].length; j++) {
                vertices[i][j] = count++;
                g.addVertex(vertices[i][j]);
            }
        }

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices[0].length; j++) {
                if (i > 1) {
                    g.addEdge(vertices[i-1][j], vertices[i][j]);
                    g.setEdgeWeight(vertices[i-1][j], vertices[i][j], weights[i][j]);
                }
                if (i < vertices.length - 1) {
                    g.addEdge(vertices[i+1][j], vertices[i][j]);
                    g.setEdgeWeight(vertices[i+1][j], vertices[i][j], weights[i][j]);
                }
                if (j > 1) {
                    g.addEdge(vertices[i][j-1], vertices[i][j]);
                    g.setEdgeWeight(vertices[i][j-1], vertices[i][j], weights[i][j]);
                }
                if (j < vertices.length - 1) {
                    g.addEdge(vertices[i][j+1], vertices[i][j]);
                    g.setEdgeWeight(vertices[i][j+1], vertices[i][j], weights[i][j]);
                }
            }
        }

        ShortestPathAlgorithm<Integer, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(g);
        System.out.println((int) (dijkstra.getPathWeight(0, count - 1) + weights[0][0]));
    }
}
