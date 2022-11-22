package br.unicamp.marstransport;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import br.unicamp.marstransport.dataStructures.matrices.Matrix;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MatrixTest {
    Matrix<Integer> matrix;

    @Before
    public void setup() {
        matrix = new Matrix<Integer>(2, 3);
        matrix.set(1, 0, 0);
        matrix.set(2, 0, 1);
        matrix.set(3, 0, 2);
        matrix.set(4, 1, 0);
        matrix.set(5, 1, 1);
        matrix.set(6, 1, 2);
    }

    @Test
    public void test_iterator() {
        ArrayList<Integer> aux = new ArrayList<Integer>(6);
        Iterator<Integer> iter = matrix.iterator();
        while (iter.hasNext())
            aux.add(iter.next());

        assertEquals(new Integer(1), aux.get(0));
        assertEquals(new Integer(2), aux.get(1));
        assertEquals(new Integer(3), aux.get(2));
        assertEquals(new Integer(4), aux.get(3));
        assertEquals(new Integer(5), aux.get(4));
        assertEquals(new Integer(6), aux.get(5));
    }

    @Test
    public void test_forEach() {
        ArrayList<Integer> aux = new ArrayList<Integer>(6);
        for (Integer i : matrix)
            aux.add(i);

        assertEquals(new Integer(1), aux.get(0));
        assertEquals(new Integer(2), aux.get(1));
        assertEquals(new Integer(3), aux.get(2));
        assertEquals(new Integer(4), aux.get(3));
        assertEquals(new Integer(5), aux.get(4));
        assertEquals(new Integer(6), aux.get(5));
    }
}