package br.unicamp.marstransport.dataStructures.graphs;

import androidx.annotation.NonNull;

import java.security.KeyException;
import java.util.List;

import br.unicamp.marstransport.dataStructures.matrices.KeyMatrix;

public class AdjacencyMatrixGraph<K, N extends Number> {
    KeyMatrix<K, N> matrix;
    List<K> nodes;

    public AdjacencyMatrixGraph(@NonNull List<K> nodes) throws KeyException {
        this.matrix = new KeyMatrix<K, N>(nodes);
        this.nodes = nodes;
    }
}
