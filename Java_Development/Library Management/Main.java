import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Main
{
    public static void main(String[] args)
    {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Welcome to Book Management Application");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the book: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author of the book: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    System.out.println("Book with ID " + library.getNextBookId() + " has been added.");
                    break;
                case 2:
                    System.out.print("Enter the book ID to borrow: ");
                    int bookIdToBorrow = scanner.nextInt();
                    library.borrowBook(bookIdToBorrow);
                    break;
                case 3:
                    System.out.print("Enter the book ID to return: ");
                    int bookIdToReturn = scanner.nextInt();
                    library.returnBook(bookIdToReturn);
                    break;
                case 4:
                    System.out.println("Library Contents:");
                    library.displayBooks();
                    break;
                case 5:
                    System.out.println("Thank you for Using Application !!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}