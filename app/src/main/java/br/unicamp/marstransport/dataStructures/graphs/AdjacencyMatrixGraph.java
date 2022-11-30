package br.unicamp.marstransport.dataStructures.graphs;

import androidx.annotation.NonNull;

import java.security.KeyException;
import java.util.List;

import br.unicamp.marstransport.dataStructures.matrices.KeyMatrix;

public class AdjacencyMatrixGraph<K, N extends Number> {
    private KeyMatrix<K, N> matrix;
    private List<K> nodes;

    public static enum Algorithm {
        DJIKSTRA,
        BACKTRACKING
    }

    public AdjacencyMatrixGraph(@NonNull List<K> nodes) throws KeyException {
        this.matrix = new KeyMatrix<K, N>(nodes);
        this.nodes = nodes;
    }

    public N getWeight(K row, K col) {
        return matrix.getValue(row, col);
    }

    public void setWeight(N weight, K row, K col) {
        matrix.setValue(weight, row, col)
    }

    public Queue<K> getShortestPath(K start, K dest) {
        return djikstra(start, dest);
    }

    public Queue<K> getShortestPath(K start, K dest, Algorithm algorithm) {
        throw new NotImplementedException();
    }

    private Queue<K> djikstra(K start, K dest) {
        Matrix<N> distance = new Matrix<N>(nodes.count())
        int startIdx = nodes.indexOf(start)
        distance.setValue(0, startIdx, startIdx);

        throw new NotImplementedException();
    }
}
