package br.unicamp.marstransport;

import androidx.annotation.NonNull;

import java.security.KeyException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class KeyMatrix<K, T> extends Matrix<T> {
    K[] rowKeys, colKeys;

    // TODO: Change .length to .count()
    public KeyMatrix(K[] rowKeys, K[] colKeys) throws KeyException {
        super(rowKeys.length, colKeys.length);
        setRowKeys(rowKeys);
        setColKeys(colKeys);
    }

    public KeyMatrix(K[] keys) throws KeyException {
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

    public void setRowKeys(K[] rowKeys) throws KeyException {
        checkKeyIntegrity(rowKeys);
        this.rowKeys = rowKeys;
    }

    public void setColKeys(K[] colKeys) throws KeyException {
        checkKeyIntegrity(colKeys);
        this.colKeys = colKeys;
    }

    private void checkKeyIntegrity(K[] keys) throws KeyException {
        @SuppressWarnings("unchecked")
        K[] repeats = (K[]) new Object[keys.length];
        int i = 0;

        for (K k : keys) {
            if (k == null)
                throw new NullPointerException();

            if (Arrays.stream(repeats).anyMatch(k::equals))
                throw new KeyException("Key already present");

            repeats[i++] = k;
        }
    }

    @NonNull
    @Override
    public KeyMatrix<K, T> transpose() {
        KeyMatrix<K, T> t = (KeyMatrix<K, T>) super.transpose();
        t.colKeys = this.rowKeys;
        t.rowKeys = this.colKeys;
        return t;
    }

    public T get(K row, K col) {
        int ridx, cidx;
        ridx = Arrays.asList(rowKeys).indexOf(row);
        cidx = Arrays.asList(colKeys).indexOf(col);
        if (ridx == -1 || cidx == -1)
            throw new NoSuchElementException("\"" + row.toString() + "\" is not a key.");
        return get(ridx, cidx);
    }
}
