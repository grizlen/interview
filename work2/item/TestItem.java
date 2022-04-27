package work2.item;

import java.util.Objects;

public class TestItem {
    private final int id;
    private final String title;

    public TestItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("{Id: %d; title: %s}", id, title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestItem testItem = (TestItem) o;
        return id == testItem.id && title.equals(testItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
