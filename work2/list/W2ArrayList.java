package work2.list;

import java.util.Iterator;

public class W2ArrayList<T> extends W2List<T> {

    private T[] array = (T[]) new Object[1];
    private int iteratorIndex;
    private final Iterator<T> iterator = new Iterator<>() {
        @Override
        public boolean hasNext() {
            return iteratorIndex < size;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return array[iteratorIndex++];
            }
            return null;
        }
    };

    @Override
    public void add(T item) {
        if (size > array.length - 1) {
            resize();
        }
        array[size++] = item;
    }

    private void resize() {
        Object[] newArray = new Object[size * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = (T[]) newArray;
    }

    @Override
    public void add(int pos, T item) {
        if (pos < 0) {
            pos = 0;
        }
        if (pos >= size) {
            add(item);
            return;
        }
        if (size > array.length - 1) {
            resize();
        }
        System.arraycopy(array, pos, array, pos + 1, size - pos);
        array[pos] = item;
        size++;
    }

    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException(pos);
        }
        return array[pos];
    }

    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException(pos);
        }
        T result = array[pos];
        System.arraycopy(array, pos + 1, array, pos, size - pos-1);
        size--;
        return result;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        iteratorIndex = 0;
        return iterator;
    }
}
