package fotius.example.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TodoListTest {

  // EXAMPLE TEST
  // READS AS: Test that newly created list has no items in it
  @Test
  void createdListHasNoItemsInIt() {
    TodoList list = new TodoList();

    Assertions.assertTrue(list.items().isEmpty());
  }

  @Test
  void createdListWithItemsInIt() {
    List<String> items = Arrays.asList("item1", "item2", "item3");
    TodoList list = new TodoList(items);

    Assertions.assertEquals(list.items(), items);
  }

  @Test
  public void testAddItemToList() {
    String item = "Test item";
    TodoList list = new TodoList();

    list.add(item);

    Assertions.assertTrue(list.items().contains(item));
  }

  @Test
  public void testGetItems() {
    List<String> items = Arrays.asList("item1", "item2", "item3");
    TodoList list = new TodoList(items);

    List<String> returnedItems = list.items();

    Assertions.assertEquals(items, returnedItems);
    Assertions.assertNotSame(items, returnedItems);
  }

  @Test
  public void testDeleteItemExist() {
    String itemToDelete = "item2";
    TodoList list = new TodoList(Arrays.asList("item1", "item2", "item3"));

    boolean isDeleted = list.delete(itemToDelete);

    Assertions.assertTrue(isDeleted);
    Assertions.assertEquals(2, list.items().size());
    Assertions.assertFalse(list.items().contains(itemToDelete));
  }

  @Test
  public void testDeleteItemDoesNotExist() {
    String itemToDelete = "item4";
    TodoList list = new TodoList(Arrays.asList("item1", "item2", "item3"));

    boolean isDeleted = list.delete(itemToDelete);

    Assertions.assertFalse(isDeleted);
    Assertions.assertEquals(3, list.items().size());
  }


  @Test
  public void testDeleteAtValidIndex() {
    int indexToDelete = 1;
    TodoList list = new TodoList(Arrays.asList("item1", "item2", "item3"));

    String deletedItem = list.deleteAt(indexToDelete);

    Assertions.assertEquals("item2", deletedItem);
    Assertions.assertEquals(2, list.items().size());
    Assertions.assertFalse(list.items().contains(deletedItem));
  }

  @Test
  public void testDeleteAt_InvalidIndex() {
    Assertions.assertThrows(IndexOutOfBoundsException.class,
        () -> {
          int invalidIndex = 3;
          TodoList list = new TodoList(Arrays.asList("item1", "item2", "item3"));

          list.deleteAt(invalidIndex);
        });
  }
}
