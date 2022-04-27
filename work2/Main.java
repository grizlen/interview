package work2;

import work2.item.TestItem;
import work2.list.W2LinkedList;
import work2.list.W2ArrayList;
import work2.list.W2List;

public class Main {
    public static void main(String[] args) {
        // linked list
        System.out.println("W2LinkedList:");
        testList(new W2LinkedList<>());

        // array list
        System.out.println("W2ArrayList:");
        testList(new W2ArrayList<>());
    }

    private static void testList(W2List<TestItem> list) {
        testAdd(list);
        testIterable(list);
        testGet(list);
        testRemove(list);
        list.print();
    }

    private static void testAdd(W2List<TestItem> list) {
        System.out.println("Add:");
        list.add(-1, new TestItem(1, "Item1"));
        list.add(new TestItem(3, "Item3"));
        list.add(1, new TestItem(2, "Item2"));
        list.add(10, new TestItem(4, "Item4"));
        list.print();
    }

    private static void testIterable(W2List<TestItem> list) {
        System.out.println("For each:");
        list.forEach(System.out::println);
    }

    private static void testGet(W2List<TestItem> list) {
        System.out.println("Get:");
        try {
            System.out.println(list.get(-1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        try {
            System.out.println(list.get(4));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testRemove(W2List<TestItem> list) {
        System.out.println("Remove:");
        System.out.println(list.remove(new TestItem(2, "Item2")));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        try {
            System.out.println(list.remove(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
