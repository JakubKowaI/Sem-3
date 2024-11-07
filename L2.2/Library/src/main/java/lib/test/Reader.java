package lib.test;

import java.util.ArrayList;
import java.util.List;

//Low Coupling bo wywołuje ją tylko Library albo Loan a więc i High Cohesion
//Klasa Information Expert odpowiada za informacje o czytelniku i wypożyczeniach
public class Reader {
    private final String name;
    private final List<Loan> loans = new ArrayList<>();

    public Reader(final String name) {
        this.name = name;
    }

    public void addLoan(final Loan loan) {
        loans.add(loan);
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public String getName() {
        return name;
    }
}