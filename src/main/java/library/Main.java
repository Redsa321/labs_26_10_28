package library;

import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library(new InMemoryReaderRepository(), new InMemoryBookRepository(), new InMemoryCopyRepository());

        Book book = library.addBook("Dune", "Frank Herbert");
        Reader reader = library.addReader("Alice");
        BookCopy firstCopy = library.addCopy(book.getId());
        library.addCopy(book.getId());

        library.borrowCopy(firstCopy.getId(), reader.getId());

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.println("LIBRARY");
            System.out.println("1. List books");
            System.out.println("2. List readers");
            System.out.println("3. List copies");
            System.out.println("4. Add reader");
            System.out.println("5. Add book");
            System.out.println("6. Add copy");
            System.out.println("7. Borrow a copy");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1" -> showBooks(library);
                    case "2" -> showReaders(library);
                    case "3" -> showCopies(library);
                    case "4" -> addReader(library, scanner);
                    case "5" -> addBook(library, scanner);
                    case "6" -> addCopy(library, scanner);
                    case "7" -> borrowCopy(library, scanner);
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option");
                }
            } catch(IllegalArgumentException | IllegalStateException exception) {
                System.out.println("Error: " + exception.getMessage());
            }

        }


    }

    public static void showBooks(Library library) {
        System.out.println("Books\n");

        for(Book book : library.getBooks()) {
            System.out.println(
                    book.getId() + " | " + book.getTitle() + " | " + book.getAuthor()
            );
        }
    }

    public static void showCopies(Library library) {
        System.out.println("Copies\n");

        for(BookCopy bookcopy : library.getBookCopies()) {
            String status = bookcopy.isAvailable() ? "Available" : "Borrowed by: " + bookcopy.getBorrower().getName();
            System.out.println(
                    bookcopy.getId() + " | " + bookcopy.getBook().getTitle() + " | " + status
            );
        }
    }

    public static void showReaders(Library library) {
        for(Reader reader : library.getReaders()) {
            System.out.print(
                    reader.getId() + " | " + reader.getName() + " | "
            );

            StringJoiner titles = new StringJoiner(", ");
            for(BookCopy copy : library.getBorrowedCopies(reader.getId())) {
                titles.add(copy.getBook().getTitle());
            }
            System.out.println(titles);
        }
        System.out.println("\n");
    }

    public static void addReader(Library library, Scanner scanner) {
        System.out.print("Reader name: ");
        String name = scanner.nextLine();
        Reader reader = library.addReader(name);
        System.out.println("Added reader. ID: " + reader.getId());
    }

    public static void addBook(Library library, Scanner scanner) {
        System.out.print("Book title: ");
        String title = scanner.nextLine();
        System.out.print("Book author: ");
        String author = scanner.nextLine();

        Book book = library.addBook(title, author);
        System.out.println("Added book. ID: " + book.getId());
    }

    public static void addCopy(Library library, Scanner scanner) {
        showBooks(library);
        System.out.print("Book ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        BookCopy copy = library.addCopy(id);
        System.out.println("Copy added. ID: " + copy.getId());
    }

    public static void borrowCopy(Library library, Scanner scanner) {
        showCopies(library);
        System.out.print("Copy ID: ");
        int copyId = Integer.parseInt(scanner.nextLine());
        showReaders(library);
        System.out.print("Reader ID: ");
        int readerId = Integer.parseInt(scanner.nextLine());
        library.borrowCopy(copyId, readerId);
        System.out.println("Copy of " + library.findBookCopyById(copyId).getBook().getTitle() + " borrowed by " + library.findReaderById(readerId).getName());
    }
}
