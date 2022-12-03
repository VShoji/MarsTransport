package br.unicamp.marstransport.dataStructures.graphs;

import android.util.Pair;

import androidx.annotation.NonNull;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import br.unicamp.marstransport.dataStructures.matrices.KeyMatrix;
import kotlin.NotImplementedError;

public class AdjacencyMatrixGraph<K> {
    private KeyMatrix<K, Double> matrix;
    final private List<K> nodes;
    public final int ORDER;

    public static enum Algorithm {
        DJIKSTRA,
        BACKTRACKING
    }

    public AdjacencyMatrixGraph(@NonNull List<K> nodes) throws KeyException {
        this.matrix = new KeyMatrix<K, Double>(nodes);
        this.nodes = nodes;
        this.ORDER = matrix.WIDTH;
    }

    public double getWeight(K row, K col) {
        return matrix.getValue(row, col);
    }

    public void setWeight(double weight, K row, K col) {
        matrix.setValue(weight, row, col);
    }

    public LinkedList<K> getShortestPath(K start, K dest) {
        return new DijkstrasAlgorithm().run(start, dest);
    }

    public LinkedList<K> getShortestPath(K start, K dest, Algorithm algorithm) {
        throw new NotImplementedError();
    }

    private class DijkstrasAlgorithm {
        private class Node implements Comparable<Node> {
            public double value;
            public boolean visited;
            public int index;

            public Node(double value, boolean visited, int index) {
                this.value = value;
                this.visited = visited;
                this.index = index;
            }

            @Override
            public int compareTo(Node o) {
                return Double.compare(value, o.value);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                Node node = (Node) o;
                return index == node.index;
            }

            @Override
            public int hashCode() {
                return Objects.hash(value, visited, index);
            }
        }

        public LinkedList<K> run(K start, K dest) {
            ArrayList<Node> distance = new ArrayList<Node>(matrix.WIDTH);
            int startI = nodes.indexOf(start);
            distance.set(startI, new Node(0d, true, startI));
            return next(startI, nodes.indexOf(dest), distance, new LinkedList<Pair<Integer, Integer>>());
        }

        private LinkedList<K> next(int current, int destination, ArrayList<Node> distance, LinkedList<Pair<Integer, Integer>> nextNodes) {
            if (current == destination) {
                LinkedList<K> ret = new LinkedList<K>();
                ret.addFirst(nodes.get(current));
                return ret;
            }

            for (int i = 0; i < ORDER; i++) {
                if (current == i)
                    continue;

                Double relDistance = matrix.getValue(current, i);
                if (relDistance == null)
                    continue;

                Node node = distance.get(i);
                if (node == null)
                    node = new Node(Double.MAX_VALUE, false, i);

                double calculated = distance.get(current).value + matrix.getValue(current, i);
                if (calculated < node.value) {
                    node.value = calculated;
                    nextNodes.addLast(new Pair<Integer, Integer>(current, i));
                }

                distance.set(i, node);
            }

            throw new NotImplementedError();
        }
    }
}
