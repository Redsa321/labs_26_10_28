package library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BookCopyTest {

    @Test
    void newCopyShouldBeAvailable() {
        Book book = new Book(1, "Dune", "Frank Herbert");
        BookCopy copy = new BookCopy(1, book);

        assertTrue(copy.isAvailable());
    }
}