package lib.test;

//Low Coupling bo wywołuje ją Library a sama wywołuje Reader
//Klasa Expert przy wywoływaniu Reader
public class Loan {
    private final BookCopy bookCopy;
    private final Reader reader;

    public Loan(final BookCopy bookCopy, final Reader reader) {
        this.bookCopy = bookCopy;
        this.reader = reader;
        bookCopy.borrowCopy();
        reader.addLoan(this);
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public Reader getReader() {
        return reader;
    }
}
