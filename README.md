LIBRARY INVENTORY SYSTEM
========================

Project Overview:
-----------------
A Java-based console application that demonstrates Object-Oriented Programming 
principles using arrays of objects. The system manages library inventory with 
comprehensive features for book management and reporting.


Features Implemented:
---------------------
1. Add Multiple Books with auto-generated IDs
2. Display All Books in formatted table
3. Search by Title (partial matches supported)
4. Search by Author (partial matches supported)
5. Issue and Return Books with availability tracking
6. Remove Books from inventory
7. Sort by Price (ascending order)
8. Sort by Title (alphabetical order)
9. Show Issued Books report
10. Show Available Books report
11. Show Books by Price Range
12. Show Total Inventory Value
13. Show Most Expensive and Cheapest Book
14. Count of Books per Author

OOP Principles Demonstrated:
----------------------------
- Encapsulation: Private fields with public getters/setters
- Class Creation: Book class with proper attributes
- Array of Objects: Book[] array for storage
- Methods for functionality: Modular method implementation
- Validation: Input validation and error handling

How to Compile and Run:
-----------------------
1. Ensure you have Java JDK installed
2. Save both files in the same directory
3. Compile: javac Book.java LibraryInventory.java
4. Run: java LibraryInventory

System Requirements:
--------------------
- Java Runtime Environment (JRE) 8 or higher
- Command line/terminal access
- Minimum 128MB RAM

Input Validation:
-----------------
- Title and author cannot be empty
- Price cannot be negative
- Positive integer validation for counts
- Error handling for invalid inputs

