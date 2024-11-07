package lib.test;

import java.util.ArrayList;
import java.util.List;

//Low Coupling bo wywołuje ją tylko Library a sama wywołuje tylko BookCopy a więc i High Cohesion
//Klasa expert względem BookCopy
public class Book {
  private final String title;
  private final List<BookCopy> copies = new ArrayList<>();

  public Book(final String title) {
    this.title = title;
  }

  public void addCopy(final BookCopy copy) {
    copies.add(copy);
  }

  public String getTitle() {
    return title;
  }

  public List<BookCopy> getCopies() {
    return copies;
  }
}