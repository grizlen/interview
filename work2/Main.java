package work2;

import work2.item.TestItem;
import work2.list.W2LinkedList;
import work2.list.W2ArrayList;
import work2.list.W2List;

public class Main {
    public static void main(String[] args) {
        // linked list
        W2List<TestItem> list;
        list = new W2LinkedList<>();

        // array list
        list = new W2ArrayList<>();
    }
}
