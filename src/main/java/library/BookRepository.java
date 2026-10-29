package library;

import java.util.List;

public interface BookRepository {
    List<Book> getBooks();
    void saveBook(Book book);
}
