package br.unicamp.marstransport.dataStructures.graphs;

import androidx.annotation.NonNull;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import br.unicamp.marstransport.dataStructures.matrices.KeyMatrix;
import kotlin.NotImplementedError;

public class AdjacencyMatrixGraph<T> {
    private KeyMatrix<T, Double> adjacencyMatrix;
    public final int ORDER;

    public static enum Algorithm {
        DIJKSTRA,
        BACKTRACKING
    }

    public AdjacencyMatrixGraph(@NonNull List<T> nodes) throws KeyException {
        this.adjacencyMatrix = new KeyMatrix<T, Double>(nodes);
        this.ORDER = adjacencyMatrix.WIDTH;
    }

    public double getWeight(T row, T col) {
        return adjacencyMatrix.getValue(row, col);
    }

    public void setWeight(double weight, T row, T col) {
        adjacencyMatrix.setValue(weight, row, col);
    }

    public LinkedList<T> getShortestPath(T start, T destination) throws Exception {
        return new DijkstrasAlgorithm(adjacencyMatrix).run(start, destination);
    }

    public LinkedList<T> getShortestPath(T start, T dest, Algorithm algorithm) {
        throw new NotImplementedError();
    }

    private class DijkstrasAlgorithm {
        KeyMatrix<T, Double> adjacencyMatrix;
        ArrayList<Node> notVisited;
        ArrayList<Node> visited;

        private class Node implements Comparable<Node>{
            public T node;
            public double weight;
            public Node previous;

            public Node(T node, double weight) {
                this.node = node;
                this.weight = weight;
            }

            public Node(T node) {
                this.node = node;
            }

            public Node(double weight) {
                this.weight = weight;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                Node node1 = (Node) o;
                return node.equals(node1.node);
            }

            @Override
            public int hashCode() {
                return Objects.hash(node);
            }

            @Override
            public int compareTo(Node o) {
                return Double.compare(weight, o.weight);
            }
        }

        public DijkstrasAlgorithm(KeyMatrix<T, Double> adjacencyMatrix) {
            this.adjacencyMatrix = adjacencyMatrix;
            notVisited = new ArrayList<Node>(adjacencyMatrix.WIDTH);
            visited = new ArrayList<Node>(adjacencyMatrix.WIDTH);

            for (T node : adjacencyMatrix.getColKeys())
                notVisited.add(new Node(node, Double.MAX_VALUE));
        }

        public LinkedList<T> run(T start, T destination) throws Exception {
            Node currentNode = new Node(start, 0);

            while (!notVisited.isEmpty() && currentNode.equals(new Node(destination))) {
                Node nextNode = new Node(Double.MAX_VALUE);

                for (Node node : notVisited) {
                    if (start.equals(node))
                        continue;

                    Double weight = adjacencyMatrix.getValue(start, node.node);
                    if (weight == null)
                        continue;

                    double nextWeight = currentNode.weight + weight;
                    if (nextWeight < node.weight) {
                        node.weight = nextWeight;
                        node.previous = currentNode;
                    }

                    if (node.compareTo(nextNode) < 0)
                        nextNode = node;
                }

                visited.add(currentNode);
                notVisited.remove(currentNode);
                currentNode = notVisited.get(notVisited.indexOf(nextNode));
            }

            if (notVisited.isEmpty())
                throw new Exception("Path not found");

            LinkedList<T> ret = new LinkedList<T>();
            for (;;) {
                ret.addFirst(currentNode.node);
                currentNode = currentNode.previous;

                if (currentNode.previous == null) {
                    ret.addFirst(currentNode.node);
                    break;
                }
            }

            return ret;
        }
    }
}
