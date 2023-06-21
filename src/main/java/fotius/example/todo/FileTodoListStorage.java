package fotius.example.todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class FileTodoListStorage implements TodoListStorage {

    private final Path path;

    public FileTodoListStorage(Path path) {
        this.path = Objects.requireNonNull(path);
    }

    @Override
    public void save(TodoList content) throws StorageException {
        try {
            Files.writeString(path, String.join("\n", content.items()));
        } catch (IOException ioEx) {
            throw new StorageException(ioEx);
        }
    }

    @Override
    public TodoList load() throws StorageException {
        if (!Files.exists(path)) {
            return new TodoList();
        }
        try {
            return new TodoList(Arrays.asList(Files.readString(path).split("\n")));
        } catch (IOException ioEx) {
            throw new StorageException(ioEx);
        }
    }
}
