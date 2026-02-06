
/**
 * Library Inventory System - Main application class
 * Demonstrates OOP principles using array of objects
 * Provides menu-driven interface for library management
 */
import java.util.Scanner;

public class libraryInventory {
    private Book[] books;
    private int bookCount;
    private Scanner scanner;

    /**
     * Constructor initializes library with specified capacity
     * 
     * @param capacity Maximum number of books library can hold
     */
    public libraryInventory(int capacity) {
        books = new Book[capacity];
        bookCount = 0;
        scanner = new Scanner(System.in);
    }

    // 1. Add Multiple Books
    public void addMultipleBooks() {
        System.out.print("How many books would you like to add? ");
        int count = getPositiveInteger();

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Adding Book " + (i + 1) + " ---");
            addSingleBook();
        }
        System.out.println("\nSuccessfully added " + count + " books!");
    }

    private void addSingleBook() {
        scanner.nextLine(); // Clear buffer

        // Get and validate title
        String title;
        do {
            System.out.print("Enter book title: ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Error: Title cannot be empty. Please try again.");
            }
        } while (title.isEmpty());

        // Get and validate author
        String author;
        do {
            System.out.print("Enter author name: ");
            author = scanner.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("Error: Author cannot be empty. Please try again.");
            }
        } while (author.isEmpty());

        // Get and validate price
        double price;
        do {
            System.out.print("Enter book price: $");
            price = getNonNegativeDouble();
            if (price < 0) {
                System.out.println("Error: Price cannot be negative. Please try again.");
            }
        } while (price < 0);

        // Create and add book
        if (bookCount < books.length) {
            books[bookCount] = new Book(title, author, price);
            bookCount++;
            System.out.println("Book added successfully! ID: " + books[bookCount - 1].getId());
        } else {
            System.out.println("Error: Library inventory is full!");
        }
    }

    // 2. Display All Books
    public void displayAllBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        System.out.println("\n=== ALL BOOKS IN INVENTORY ===");
        System.out.println("Total books: " + bookCount);
        System.out.println("=".repeat(80));
        for (int i = 0; i < bookCount; i++) {
            System.out.println(books[i]);
        }
        System.out.println("=".repeat(80));
    }

    // 3. Search Book by Title
    public void searchByTitle() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        scanner.nextLine(); // Clear buffer
        System.out.print("Enter title to search: ");
        String searchTitle = scanner.nextLine().toLowerCase();

        boolean found = false;
        System.out.println("\n=== SEARCH RESULTS FOR TITLE: '" + searchTitle + "' ===");

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().toLowerCase().contains(searchTitle)) {
                System.out.println(books[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with the specified title.");
        }
    }

    // 4. Search Book by Author
    public void searchByAuthor() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        scanner.nextLine(); // Clear buffer
        System.out.print("Enter author to search: ");
        String searchAuthor = scanner.nextLine().toLowerCase();

        boolean found = false;
        System.out.println("\n=== SEARCH RESULTS FOR AUTHOR: '" + searchAuthor + "' ===");

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getAuthor().toLowerCase().contains(searchAuthor)) {
                System.out.println(books[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found by the specified author.");
        }
    }

    // 5. Issue Book
    public void issueBook() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        System.out.print("Enter book ID to issue: ");
        int bookId = getPositiveInteger();

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == bookId) {
                if (books[i].isAvailable()) {
                    books[i].setAvailable(false);
                    System.out.println("Book '" + books[i].getTitle() + "' has been issued successfully.");
                } else {
                    System.out.println("Book is already issued.");
                }
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    // 6. Return Book
    public void returnBook() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        System.out.print("Enter book ID to return: ");
        int bookId = getPositiveInteger();

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == bookId) {
                if (!books[i].isAvailable()) {
                    books[i].setAvailable(true);
                    System.out.println("Book '" + books[i].getTitle() + "' has been returned successfully.");
                } else {
                    System.out.println("Book is already available.");
                }
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    // 7. Remove Book
    public void removeBook() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        System.out.print("Enter book ID to remove: ");
        int bookId = getPositiveInteger();

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == bookId) {
                // Shift all elements after the removed book
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[bookCount - 1] = null;
                bookCount--;
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    // 8. Sort by Price (ascending) - Using Bubble Sort
    public void sortByPrice() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        // Bubble sort implementation
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = 0; j < bookCount - i - 1; j++) {
                if (books[j].getPrice() > books[j + 1].getPrice()) {
                    // Swap books
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }

        System.out.println("Books sorted by price (ascending).");
        displayAllBooks();
    }

    // 9. Sort by Title (A-Z) - Using Bubble Sort
    public void sortByTitle() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        // Bubble sort implementation
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = 0; j < bookCount - i - 1; j++) {
                if (books[j].getTitle().compareToIgnoreCase(books[j + 1].getTitle()) > 0) {
                    // Swap books
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }

        System.out.println("Books sorted by title (A-Z).");
        displayAllBooks();
    }

    // 10. Show Issued Books
    public void showIssuedBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        boolean found = false;
        System.out.println("\n=== ISSUED BOOKS ===");

        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isAvailable()) {
                System.out.println(books[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No issued books found.");
        }
    }

    // 11. Show Available Books
    public void showAvailableBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        boolean found = false;
        System.out.println("\n=== AVAILABLE BOOKS ===");

        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                System.out.println(books[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available books found.");
        }
    }

    // 12. Show Books by Price Range
    public void showBooksByPriceRange() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        System.out.print("Enter minimum price: $");
        double minPrice = getNonNegativeDouble();

        System.out.print("Enter maximum price: $");
        double maxPrice = getNonNegativeDouble();

        if (minPrice > maxPrice) {
            System.out.println("Error: Minimum price cannot be greater than maximum price.");
            return;
        }

        boolean found = false;
        System.out.println("\n=== BOOKS IN PRICE RANGE: $" + minPrice + " - $" + maxPrice + " ===");

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getPrice() >= minPrice && books[i].getPrice() <= maxPrice) {
                System.out.println(books[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found in the specified price range.");
        }
    }

    // 13. Show Total Inventory Value
    public void showTotalInventoryValue() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        double totalValue = 0;
        for (int i = 0; i < bookCount; i++) {
            totalValue += books[i].getPrice();
        }

        System.out.printf("\nTotal Inventory Value: $%.2f\n", totalValue);
        System.out.println("Total number of books: " + bookCount);
    }

    // 14. Show Most Expensive and Cheapest Book
    public void showMostExpensiveAndCheapestBook() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        Book mostExpensive = books[0];
        Book cheapest = books[0];

        for (int i = 1; i < bookCount; i++) {
            if (books[i].getPrice() > mostExpensive.getPrice()) {
                mostExpensive = books[i];
            }
            if (books[i].getPrice() < cheapest.getPrice()) {
                cheapest = books[i];
            }
        }

        System.out.println("\n=== PRICE EXTREMES ===");
        System.out.println("Most Expensive Book:");
        System.out.println(mostExpensive);
        System.out.println("\nCheapest Book:");
        System.out.println(cheapest);
    }

    // 15. Count of Books per Author
    public void countBooksPerAuthor() {
        if (bookCount == 0) {
            System.out.println("No books in the inventory.");
            return;
        }

        // Create arrays to store authors and counts
        String[] authors = new String[bookCount];
        int[] counts = new int[bookCount];
        int uniqueAuthors = 0;

        for (int i = 0; i < bookCount; i++) {
            String currentAuthor = books[i].getAuthor();
            boolean authorExists = false;

            // Check if author already exists in our array
            for (int j = 0; j < uniqueAuthors; j++) {
                if (authors[j].equals(currentAuthor)) {
                    counts[j]++;
                    authorExists = true;
                    break;
                }
            }

            // If author doesn't exist, add them
            if (!authorExists) {
                authors[uniqueAuthors] = currentAuthor;
                counts[uniqueAuthors] = 1;
                uniqueAuthors++;
            }
        }

        System.out.println("\n=== BOOK COUNT PER AUTHOR ===");
        for (int i = 0; i < uniqueAuthors; i++) {
            System.out.printf("%-25s: %d book(s)\n", authors[i], counts[i]);
        }
    }

    // Utility methods for input validation
    private int getPositiveInteger() {
        while (true) {
            try {
                int value = scanner.nextInt();
                if (value > 0) {
                    return value;
                } else {
                    System.out.print("Please enter a positive number: ");
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private double getNonNegativeDouble() {
        while (true) {
            try {
                double value = scanner.nextDouble();
                if (value >= 0) {
                    return value;
                } else {
                    System.out.print("Please enter a non-negative number: ");
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Display menu
    public void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("      LIBRARY INVENTORY SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1.  Add Multiple Books");
        System.out.println("2.  Display All Books");
        System.out.println("3.  Search Book by Title");
        System.out.println("4.  Search Book by Author");
        System.out.println("5.  Issue Book");
        System.out.println("6.  Return Book");
        System.out.println("7.  Remove Book");
        System.out.println("8.  Sort by Price (ascending)");
        System.out.println("9.  Sort by Title (A-Z)");
        System.out.println("10. Show Issued Books");
        System.out.println("11. Show Available Books");
        System.out.println("12. Show Books by Price Range");
        System.out.println("13. Show Total Inventory Value");
        System.out.println("14. Show Most Expensive and Cheapest Book");
        System.out.println("15. Count of Books per Author");
        System.out.println("16. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice (1-16): ");
    }

    // Main program loop
    public void run() {
        System.out.println("Welcome to Library Inventory System!");
        System.out.println("Initializing with capacity for 100 books...");

        int choice;
        do {
            displayMenu();
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> addMultipleBooks();
                    case 2 -> displayAllBooks();
                    case 3 -> searchByTitle();
                    case 4 -> searchByAuthor();
                    case 5 -> issueBook();
                    case 6 -> returnBook();
                    case 7 -> removeBook();
                    case 8 -> sortByPrice();
                    case 9 -> sortByTitle();
                    case 10 -> showIssuedBooks();
                    case 11 -> showAvailableBooks();
                    case 12 -> showBooksByPriceRange();
                    case 13 -> showTotalInventoryValue();
                    case 14 -> showMostExpensiveAndCheapestBook();
                    case 15 -> countBooksPerAuthor();
                    case 16 -> System.out.println("Thank you for using Library Inventory System. Goodbye!");
                    default -> System.out.println("Invalid choice! Please enter a number between 1-16.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number between 1-16.");
                scanner.next(); // Clear invalid input
                choice = 0;
            }
        } while (choice != 16);

        scanner.close();
    }

    public static void main(String[] args) {
        libraryInventory library = new libraryInventory(100);
        library.run();
    }
}
