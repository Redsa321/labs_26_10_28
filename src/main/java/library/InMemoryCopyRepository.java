package library;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCopyRepository implements BookCopyRepository {

    private final List<BookCopy> copies = new ArrayList<>();

    @Override
    public List<BookCopy> getBookCopies() {
        return List.copyOf(copies);
    }

    @Override
    public void saveCopy(BookCopy copy) {
        copies.add(copy);
    }
}
