package work2.list;

import java.util.Iterator;

public abstract class W2List<T> implements Iterable<T>{
    protected int size = 0;

    public int size() {
        return size;
    }
    public abstract void add(T item);
    public abstract void add(int pos, T item);
    public abstract T get(int pos);
    public abstract T remove(int pos);
    public abstract T remove(T value);

    public void print() {
        System.out.println('[');
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            System.out.print("\t" + iterator.next());
            System.out.println(iterator.hasNext() ? ',' : "");
        }
        System.out.println(']');
    }
}
