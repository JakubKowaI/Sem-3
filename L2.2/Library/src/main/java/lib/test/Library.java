package lib.test;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

//Klasa Creator - odpowiada za tworzenie obiektów Book i Reader a teoretycznie także Loan i BookCopy
public class Library {
  private final List<Book> books = new ArrayList<>();
  private final List<Reader> readers = new ArrayList<>();
  private final List<Loan> loans = new ArrayList<>();

  public void addBook(final String title) {
    if (findBook(title) != null) {
      findBook(title).addCopy(new BookCopy());
    } else {
      books.add(new Book(title));
      findBook(title).addCopy(new BookCopy());
    }
  }

  public void addReader(final String name) {
    readers.add(new Reader(name));
  }

  public void borrowBookCopy(final String title, final String readerName) {
    final Book book = findBook(title);
    final Reader reader = findReader(readerName);

    if (book != null && reader != null) {
      for (final BookCopy copy : book.getCopies()) {
        if (copy.isAvailable()) {
          final Loan temp = new Loan(copy, reader);
          loans.add(temp);
          findReader(readerName).addLoan(temp);
          System.out.println("Lent book: " + title + " to: " + readerName);
          return;
        }
      }
      System.out.println("No copies avaible.");
    } else {
      System.out.println("Book or reader not found.");
    }
  }

  public @Nullable Book findBook(final String title) {
    for (final Book book : books) {
      if (book.getTitle().equals(title)) {
        return book;
      }
    }
    return null;
  }

  public @Nullable Reader findReader(final String name) {
    for (final Reader reader : readers) {
      if (reader.getName().equals(name)) {
        return reader;
      }
    }
    return null;
  }
}