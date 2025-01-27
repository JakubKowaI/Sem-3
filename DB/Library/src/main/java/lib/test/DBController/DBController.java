package lib.test.DBController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

public class DBController {
    DBConnection connection;

    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public DBController(DBConnection connection) {
        this.connection = connection;
    }

    public void updateBookAvailability(int copyId) throws SQLException {
        String sql = "{CALL update_books_availability(?)}";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, copyId);
            stmt.execute();
        }
    }

    public void Login(String login, String password) {
    }

    public char getType() {
        return connection.getType();
    }

    public int getId() {
        return connection.getId();
    }

    public void addBook(String title, String author,String isbn, int year) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            INSERT INTO books(Title, Author, Year, isbn)
        VALUES (?, ?, ?, ?)
      """)) {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setInt(3, year);
            statement.setString(4, isbn);
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted + " rows inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(int copyID) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            DELETE FROM copies
            WHERE copy_id = ?
      """)) {
            statement.setInt(1, copyID);
            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " rows deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showRentals() {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            SELECT *
            FROM loans
        """)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int loanid = resultSet.getInt("id"); // by column index
                int bookid = resultSet.getInt("copy_id"); // by column name
                int userid = resultSet.getInt("user_id"); // by column index
                boolean ifback = resultSet.getBoolean("book_returned"); // by column name
                System.out.println("Loan ID: " + loanid + " Book ID: " + bookid + " User ID: " + userid + " Returned: " + ifback);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addReader(String name, String surname, String pesel, String login) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            Select users.login
            FROM users
            WHERE users.login = ?
        """)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Login already exists");
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            INSERT INTO users(name, surname, pesel, type, login)
        VALUES (?, ?, ?, ?, ?, ?)
      """)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, pesel);
            //statement.setString(4, name+"."+surname+"."+ String.valueOf(connection.getId()));
            statement.setString(4, "r");
            statement.setString(5, login);
            statement.setString(6, hashPassword(login));
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted + " rows inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReader(int readerId) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            DELETE FROM users
            WHERE id = ?
      """)) {
            statement.setInt(1, readerId);
            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " rows deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showReaders() {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            SELECT id, name, surname, pesel
            FROM users
            WHERE type = 'r'
        """)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int loanid = resultSet.getInt("id"); // by column index
                String bookid = resultSet.getString("name"); // by column name
                String userid = resultSet.getString("surname"); // by column index
                String ifback = resultSet.getString("pesel"); // by column name
                System.out.println("id: " + loanid + " name: " + bookid + " surname: " + userid + " pesel: " + ifback);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    public void addAdmin(String name, String surname, String pesel, String login) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            Select users.login
            FROM users
            WHERE users.login = ?
        """)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Login already exists");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            INSERT INTO users(name, surname, pesel, type, login, password)
        VALUES (?, ?, ?, ?, ?, ?)
      """)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, pesel);
            //statement.setString(4, name+"."+surname+"."+ String.valueOf(connection.getId()));
            statement.setString(4, "a");
            statement.setString(5, login);
            statement.setString(6, hashPassword(login));
            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted + " rows inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAdmin(int adminId) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            DELETE FROM users
            WHERE id = ?
      """)) {
            statement.setInt(1, adminId);
            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " rows deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAdmins() {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            SELECT id, name, surname, pesel
            FROM users
            WHERE type = 'a'
        """)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int loanid = resultSet.getInt("id"); // by column index
                String bookid = resultSet.getString("name"); // by column name
                String userid = resultSet.getString("surname"); // by column index
                String ifback = resultSet.getString("pesel"); // by column name
                System.out.println("id: " + loanid + " name: " + bookid + " surname: " + userid + " pesel: " + ifback);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void findBook(String title) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            SELECT b.title, b.author, b.isbn, COUNT(c.copy_id) AS available_copies
                               FROM books b
                               JOIN copies c ON b.isbn = c.isbn
                               WHERE b.title LIKE ? AND c.avaible = TRUE
                               GROUP BY b.title, b.author, b.isbn;
        """)) {
            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString(3); // by column index
                String titleResult = resultSet.getString(1); // by column name
                String authorResult = resultSet.getString(2); // by column index
                int amount = resultSet.getInt( 4); // by column name
                System.out.println("isbn: " + isbn + " title: " + titleResult + " author: " + authorResult + " number of available copies: " + amount);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rentBook(int userId, String bookTitle) {
        try (PreparedStatement selectStatement = connection.getConnection().prepareStatement("""
            SELECT c.copy_id
            FROM books b
            JOIN copies c ON b.isbn = c.isbn
            WHERE b.title = ? AND c.avaible = TRUE
            LIMIT 1
            FOR UPDATE
        """);
             PreparedStatement insertStatement = connection.getConnection().prepareStatement("""
            INSERT INTO loans (copy_id, user_id, book_returned)
            VALUES (?, ?, 0)
        """))
//             PreparedStatement updateStatement = connection.getConnection().prepareStatement("""
//            UPDATE copies
//            SET avaible = FALSE
//            WHERE copy_id = ?
//        """))
        {
            connection.getConnection().setAutoCommit(false);

            selectStatement.setString(1, bookTitle);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                int copyId = resultSet.getInt("copy_id");

                insertStatement.setInt(1, copyId);
                insertStatement.setInt(2, userId);
                insertStatement.executeUpdate();

                //updateStatement.setInt(1, copyId);
                //updateStatement.executeUpdate();
            }

            connection.getConnection().commit();
        } catch (SQLException e) {
            try {
                connection.getConnection().rollback();
            } catch (SQLException ex) {
                System.out.println("Nie udalo sie wypozyczyc ksiazki (rollback)");
            }
            System.out.println("Nie udalo sie wypozyczyc ksiazki");
        } finally {
            try {
                connection.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Nie udalo sie wlaczyc auto commita");
            }
        }
    }

    public void returnBook(int id, int bookId) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            UPDATE loans 
            SET book_returned = 1
            WHERE copy_id = ? AND user_id = ? AND book_returned = 0
      """)) {
            statement.setInt(1, bookId);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            System.out.println(rowsUpdated + " rows updated");
        } catch (SQLException e) {
            System.out.println("Nie udalo sie oddac ksiazki");
        }
    }

    public void showMyRentals(int id) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            SELECT loans.id, loans.copy_id, loans.user_id, loans.book_returned
            FROM loans
            WHERE user_id = ? AND book_returned = 0
        """)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int loanid = resultSet.getInt(1); // by column index
                int bookid = resultSet.getInt(2); // by column name
                int userid = resultSet.getInt(3); // by column index
                boolean ifback = resultSet.getBoolean(4); // by column name
                System.out.println("Loan ID: " + loanid + " Book ID: " + bookid + " User ID: " + userid + " Returned: " + ifback);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showCopies(String title2) {
        try (PreparedStatement statement = connection.getConnection().prepareStatement("""
            SELECT books.Title, copies.copy_id, copies.avaible
            FROM books
            JOIN copies on books.isbn = copies.isbn
            WHERE books.Title = ?
        """)) {
            statement.setString(1, title2);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String bookTitle = resultSet.getString(1); // by column index
                int bookid = resultSet.getInt(2); // by column name
                //int userid = resultSet.getInt(3); // by column index
                boolean ifback = resultSet.getBoolean(3); // by column name
                System.out.println("Book Title: " + bookTitle + " Copy ID: " + bookid + " Available: " + ifback);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
