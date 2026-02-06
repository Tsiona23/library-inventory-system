public class Book {
    private static int nextId = 1; // Static counter for auto-ID generation

    private int id;
    private String title;
    private String author;
    private double price;
    private boolean isAvailable;

    /**
     * Constructor for Book class
     * 
     * @param title  Book title (cannot be empty)
     * @param author Book author (cannot be empty)
     * @param price  Book price (cannot be negative)
     */
    public Book(String title, String author, double price) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isAvailable = true;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * String representation of Book object
     * 
     * @return Formatted string with book details
     */
    @Override
    public String toString() {
        return String.format("ID: %-4d | Title: %-25s | Author: %-20s | Price: $%-8.2f | Available: %s",
                id,
                truncateString(title, 25),
                truncateString(author, 20),
                price,
                isAvailable ? "Yes" : "No");
    }

    /**
     * Helper method to truncate long strings for display
     * 
     * @param str       String to truncate
     * @param maxLength Maximum length allowed
     * @return Truncated string with ellipsis if needed
     */
    private String truncateString(String str, int maxLength) {
        if (str == null) {
            return "";
        }
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * Reset the static ID counter (useful for testing)
     */
    public static void resetIdCounter() {
        nextId = 1;
    }

    /**
     * Get the next ID that will be assigned (for testing purposes)
     * 
     * @return The next ID value
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Check if two books are equal based on ID
     * 
     * @param obj The object to compare with
     * @return true if books have same ID, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Book book = (Book) obj;
        return id == book.id;
    }

    /**
     * Hash code based on book ID
     * 
     * @return Hash code value
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}