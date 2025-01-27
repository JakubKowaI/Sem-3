import lib.test.Library;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
  //@Test
  public @Test void testAddBook() {
    Library library = new Library();
    library.addBook("Fightclub");
    assertNotNull(library.findBook("Fightclub"));
  }

  @Test
  public void testBorrowBookCopy() {
    Library library = new Library();
    library.addBook("Fightclub");
    library.addReader("Jan Plewa");

    library.borrowBookCopy("Fightclub", "Jan Plewa");
    assertFalse(library.findBook("Fightclub").getCopies().get(0).isAvailable());
  }
}