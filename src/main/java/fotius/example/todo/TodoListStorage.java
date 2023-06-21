package fotius.example.todo;

/**
 * Storage layer for {@link TodoList}.
 */
public interface TodoListStorage {

    /**
     * Persists items to the storage associated with this todolist.
     *
     * @throws StorageException in case persistence operation fails
     */
    void save(TodoList list) throws StorageException;

    /**
     * Loads list of items from this storage.
     *
     * @throws StorageException in case load operation fails
     */
    TodoList load() throws StorageException;
}
