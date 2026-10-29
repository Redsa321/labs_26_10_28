package library;

import java.util.ArrayList;
import java.util.List;


// GRASP Controller: coordinates library operations
// GRASP Creator: creates books, copies and readers registered in the library
// Dependency Inversion: depends on the repository interface

public class Library {
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;
    private final BookCopyRepository copiesRepository;
    private int nextReaderId = 1;
    private int nextBookId = 1;
    private int nextCopyId = 1;

    public Library(ReaderRepository readerRepository, BookRepository bookRepository, BookCopyRepository copiesRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
        this.copiesRepository = copiesRepository;
    }

    public Reader addReader(String name) {
        Reader reader = new Reader(nextReaderId, name);
        readerRepository.saveReader(reader);

        nextReaderId++;
        return reader;
    }

    public Book addBook(String title, String author) {
        Book book = new Book(nextBookId, title, author);
        bookRepository.saveBook(book);

        nextBookId++;
        return book;
    }

    public BookCopy addCopy(int bookId) {
        Book book = findBookById(bookId);
        BookCopy copy = new BookCopy(nextCopyId, book);
        copiesRepository.saveCopy(copy);
        nextCopyId++;
        return copy;
    }

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public List<BookCopy> getBookCopies() {
        return copiesRepository.getBookCopies();
    }

    public List<Reader> getReaders() {
        return readerRepository.getReaders();
    }

    public List<BookCopy> getBorrowedCopies(int userId) {
        List<BookCopy> borrowedBooks = new ArrayList<>();
        for(BookCopy copy : getBookCopies()) {
            if(!copy.isAvailable() && copy.getBorrower().getId() == userId) {
                borrowedBooks.add(copy);
            }
        }

        return borrowedBooks;
    }

    public Reader findReaderById(int id) {
        for(Reader reader : getReaders()) {
            if(reader.getId() == id) {
                return reader;
            }
        }
        throw new IllegalArgumentException("Reader not found: " + id);
    }

    public BookCopy findBookCopyById(int id) {
        for(BookCopy copy : getBookCopies()) {
            if(copy.getId() == id) {
                return copy;
            }
        }
        throw new IllegalArgumentException("Copy not found: " + id);
    }

    public Book findBookById(int id) {
        for(Book book : getBooks()) {
            if(book.getId() == id) {
                return book;
            }
        }
        throw new IllegalArgumentException("Book not found: " + id);
    }

    public void borrowCopy(int copyId, int readerId) {
        BookCopy copy = findBookCopyById(copyId);
        Reader reader = findReaderById(readerId);
        copy.borrow(reader);
    }
}
