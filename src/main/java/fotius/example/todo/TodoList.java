package fotius.example.todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of items.
 */
public class TodoList {

    private final List<String> items;

    public TodoList() {
        this(List.of());
    }

    public TodoList(List<String> items) {
        this.items = new ArrayList<>(items);
    }

    /**
     * Adds the given item to the list.
     */
    public void add(String item) {
        items.add(item);
    }

    /**
     * Returns items list. Note that returned list is an immutable copy of items.
     */
    public List<String> items() {
        return List.copyOf(items);
    }

    /**
     * Deletes all the items equal to the given one.
     * Returns true only if at least one item has been deleted.
     */
    public boolean delete(String item) {
        return items.removeIf(item::equals);
    }

    /**
     * Deletes all the items equal to the given one.
     * Returns true only if at least one item has been deleted.
     */
    public String deleteAt(int index) {
        return items.remove(index);
    }
}
