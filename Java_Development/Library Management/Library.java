import java.util.ArrayList;
import java.util.List;
public class Library
{
    private List<Book> books = new ArrayList<>();
    private int nextBookId = 1;

    public void addBook(String title, String author) {
        Book book = new Book(nextBookId, title, author);
        books.add(book);
        nextBookId++;
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
            System.out.println("------------");
        }
    }

    public void borrowBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (book.isAvailable()) {
                    book.borrow();
                    System.out.println("Book with ID " + bookId + " has been borrowed.");
                } else {
                    System.out.println("Book with ID " + bookId + " is already borrowed.");
                }
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (!book.isAvailable()) {
                    book.returnBook();
                    System.out.println("Book with ID " + bookId + " has been returned.");
                } else {
                    System.out.println("Book with ID " + bookId + " is already available in the library.");
                }
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }
    public int getNextBookId()
    {
        int maxId = 0;
        for (Book book : books) {
            if (book.getBookId() > maxId) {
                maxId = book.getBookId();
            }
        }
        return maxId ;
    }
}