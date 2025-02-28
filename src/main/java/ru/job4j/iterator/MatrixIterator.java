package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (column < data.length - 1 && data[column].length == 0) {
            column++;
        }
        return column < data.length
                && data[column].length != 0
                && row < data[column].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (data[column].length - 1 == row) {
            var rsl = data[column++][row];
            row = 0;
            return rsl;
        } else {
            return data[column][row++];
        }
    }
}
