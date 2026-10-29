package library;

public class BookCopy {

    private final int id;
    private final Book book;
    private Reader borrower;

    public BookCopy(int id, Book book) {
        this.id = id;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Reader getBorrower() {
        return borrower;
    }

    public boolean isAvailable() {
        return borrower == null;
    }

    //Wypożyczanie
    //GRASP: BookCopy know its borrower, it checks availability and controls borrowing
    public void borrow(Reader reader) {
        if(reader == null) {
            throw new IllegalArgumentException("Reader must no be null");
        }
        if(!isAvailable()) {
            throw new IllegalArgumentException("This copy is already borrowed");
        }
        borrower = reader;
    }

}
