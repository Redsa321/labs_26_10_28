package library;

import java.util.List;

public interface BookCopyRepository {
    List<BookCopy> getBookCopies();
    void saveCopy(BookCopy copy);
}
