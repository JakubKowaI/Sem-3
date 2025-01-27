package lib.test;

import java.util.HashMap;
import java.util.Map;


public class Readers {
  Map<String, Integer> readersBooks = new HashMap<>();
  String name;
  String surname;

  Readers() {
  }

  Readers(final String name, final String surname) {
    this.name = name;
    this.surname = surname;
  }

  void getBook(final String bookTitle) {
    if (readersBooks.containsKey(bookTitle) && readersBooks.get(bookTitle) > 0) {
      readersBooks.put(bookTitle, readersBooks.get(bookTitle) + 1);
    } else {
      readersBooks.put(bookTitle, 1);
    }
  }

  String listBooks() {
    String listOfBooks = "";
    for (final Map.Entry<String, Integer> entry : readersBooks.entrySet()) {
      if (entry.getValue() > 1) {
        for (int i = 0; i < entry.getValue(); i++) {
          listOfBooks += entry.getKey() + "\n";
        }
      } else {
        listOfBooks += entry.getKey() + "\n";
      }
    }
    return listOfBooks;
  }
}
