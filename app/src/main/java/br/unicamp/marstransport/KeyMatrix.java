package br.unicamp.marstransport;

import java.security.KeyException;
import java.util.Arrays;

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
}
