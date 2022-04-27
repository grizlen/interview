package work2.list;

import java.util.Iterator;

public class W2LinkedList<T> extends W2List<T> {

    private Node<T> first = null;
    private Node<T> iteratorNode = null;
    private final Iterator<T> iterator = new Iterator<>() {
        @Override
        public boolean hasNext() {
            return iteratorNode != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T result = iteratorNode.value;
                iteratorNode = iteratorNode.next;
                return result;
            }
            return null;
        }
    };

    @Override
    public Iterator<T> iterator() {
        iteratorNode = first;
        return iterator;
    }

    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (first == null) {
            first = newNode;
        } else {
            Node<T> last = first;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
    }

    @Override
    public void add(int pos, T item) {
        if (pos < 0) {
            pos = 0;
        }
        if (pos >= size) {
            add(item);
        } else {
            Node<T> newNode = new Node<>(item);
            if (pos == 0){
                newNode.next = first;
                first = newNode;
            } else {
                Node<T> prev = find(pos - 1);
                newNode.next = prev.next;
                prev.next = newNode;
            }
            size++;
        }
    }

    private Node<T> find(int pos) {
        int i = 0;
        Node<T> result = first;
        while (result.next != null) {
            if (pos == i++) {
                break;
            }
            result = result.next;
        }
        return result;
    }

    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException(pos);
        }
        Node<T> node = find(pos);
        return node.value;
    }

    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException(pos);
        }
        if (first == null) {
            return null;
        }
        if (pos == 0) {
            Node<T> node = first;
            first = first.next;
            size--;
            return node.value;
        }
        Node<T> prev = find(pos - 1);
        Node<T> node = prev.next;
        if (node == null) {
            return null;
        }
        prev.next = node.next;
        size--;
        return node.value;
    }

    @Override
    public T remove(T value) {
        if (first == null || value == null) {
            return null;
        }
        Node<T> result = first;
        if (first.value.equals(value)) {
            first = first.next;
            size--;
            return result.value;
        }
        Node<T> prev = first;
        result = first.next;
        while (result != null) {
            if (result.value.equals(value)) {
                prev.next = result.next;
                size--;
                return result.value;
            }
            prev = result;
            result = result.next;
        }
        return null;
    }

    private static class Node<V> {
        private final V value;
        private Node<V> next = null;

        private Node(V item) {
            value = item;
        }
    }
}
