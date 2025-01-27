package lib.test;

//Low Coupling bo jest wywoływana tylko przez Book, a więc i High Cohesion
//Klasa Information Expert odpowiada za informacje o dostępności kopii książki
public class BookCopy {
  private static int copyCounter = 0;
  private final int copyId;
  private boolean status;

  public BookCopy() {
    this.copyId = ++copyCounter;
    this.status = true;
  }

  public boolean isAvailable() {
    return status;
  }

  public void borrowCopy() {
    this.status = false;
  }

  public void returnCopy() {
    this.status = true;
  }

  public int getCopyId() {
    return copyId;
  }
}
