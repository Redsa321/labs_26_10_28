package library;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowerTest {

    @Test
    void borrowerIdShouldBeEqual() {
        Library library = new Library(new InMemoryReaderRepository(), new InMemoryBookRepository(), new InMemoryCopyRepository());
        Book book1 = library.addBook("ABC", "Serhii");
        BookCopy bookcopy1 = library.addCopy(1);
        Reader reader1 = library.addReader("Marek");
        library.borrowCopy(1, 1);
        assertEquals(bookcopy1.getBorrower(), reader1);
    }
}
