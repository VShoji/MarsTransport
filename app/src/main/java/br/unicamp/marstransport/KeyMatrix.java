package br.unicamp.marstransport;

import java.util.Arrays;

public class KeyMatrix<K, T> extends Matrix<T> {
    K[] rowKeys, colKeys;

    // TODO: Change .length to .count()
    public KeyMatrix(K[] rowKeys, K[] colKeys) {
        super(rowKeys.length, colKeys.length);
        setRowKeys(rowKeys);
        setColKeys(colKeys);
    }

    public KeyMatrix(K[] keys) {
        super(keys.length, keys.length);
        setRowKeys(keys);
        setColKeys(keys);
    }

    public K[] getRowKeys() {
        return rowKeys;
    }

    public K[] getColKeys() {
        return colKeys;
    }

    public void setRowKeys(K[] rowKeys) {
        for (K k : rowKeys)
            if (k == null)
                throw new NullPointerException();

        this.rowKeys = rowKeys;
    }

    public void setColKeys(K[] colKeys) {
        for (K k : colKeys)
            if (k == null)
                throw new NullPointerException();

        this.colKeys = colKeys;
    }
}
