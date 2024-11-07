package lib.test;

import java.util.Scanner;

//Klasa Creator dla Library i Expert bo ma input od użytkownika
public class Main {
  public static void main(String[] args) {
    int choice = 0;
    final Scanner scan = new Scanner(System.in);
    final Library library = new Library();
    library.addBook("Harry Potter");
    library.addBook("Fightclub");
    library.addReader("Jan Konieczny");
    library.addReader("Kajetan Plewa");

    while (choice != 4) {
      System.out.print("1. Add book\n2. Add reader\n3. Borrow book\n4. Exit\n");

      try {
        choice = Integer.parseInt(scan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid choice.");
        continue;
      }

      switch (choice) {
        case 1:
          System.out.print("Enter book title: ");
          library.addBook(scan.nextLine());
          break;
        case 2:
          System.out.print("Enter reader name: ");
          library.addReader(scan.nextLine());
          break;
        case 3:
          System.out.print("Enter book title: ");
          final String title = scan.nextLine();
          System.out.print("Enter reader name: ");
          final String readerName = scan.nextLine();
          library.borrowBookCopy(title, readerName);
          break;
        default:
          System.out.println("Invalid choice.");
      }
    }
    scan.close();
  }
}
