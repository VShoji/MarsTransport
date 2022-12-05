package br.unicamp.marstransport;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.LinkedList;

import br.unicamp.marstransport.dataStructures.graphs.AdjacencyMatrixGraph;

public class GraphTest {
    AdjacencyMatrixGraph<String> graph;

    @Before
    public void setup() throws KeyException {
        ArrayList<String> nodes = new ArrayList<>(6);
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");
        nodes.add("E");
        nodes.add("F");
        graph = new AdjacencyMatrixGraph<>(nodes);
        graph.setWeight(2, "A", "B");
        graph.setWeight(8, "A", "D");
        graph.setWeight(5, "B", "D");
        graph.setWeight(1, "E", "F");
        graph.setWeight(6, "B", "E");
        graph.setWeight(2, "D", "F");
        graph.setWeight(3, "D", "E");
        graph.setWeight(9, "E", "C");
        graph.setWeight(3, "F", "C");
    }

    @Test
    public void dijkstra() throws Exception{
        LinkedList<String> path = null;
        path = graph.getShortestPath("A", "C");
        for (String node : path)
            System.out.println(node + " ");
    }
}
