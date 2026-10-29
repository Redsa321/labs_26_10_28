package library;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBookRepository implements BookRepository {

    private final List<Book> books = new ArrayList<>();

    @Override
    public List<Book> getBooks() {
        return List.copyOf(books);
    }

    @Override
    public void saveBook(Book book) {
        books.add(book);
    }
}
