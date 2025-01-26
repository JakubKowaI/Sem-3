package lib.test.DBController;

import org.mariadb.jdbc.Connection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;
    private char type = ' ';
    private int id = -1;

    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public DBConnection() {

    }

    public Boolean pass_setup(String login) {
        try {
            connection = (Connection) DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/library",
                    "Server_Login", "LoginPassword"
            );
            try (PreparedStatement statement = connection.prepareStatement("""
            SELECT password
            FROM login_view
            WHERE login = ?
        """)) {
                statement.setString(1, login);
                //statement.setString(2, hashPassword(login));
                statement.execute();
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    //System.out.println("User found");
                    String storedHashedPassword = resultSet.getString("password");
                    return verifyPassword(login, storedHashedPassword); // Verify the password
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
        return false;
    }

    public void connect(String login,String password) {
        try {
            String user;
            String pass;
            switch (type) {
                case 'r':
                    user = "User_Reader";
                    pass = "ReaderPassword";
                    break;
                case 'a':
                    user = "User_Admin";
                    pass = "AdminPassword";
                    break;
                case 's':
                    user = "User_Super";
                    pass = "SuperPassword";
                    break;
                default:
                    System.out.println("Unknown user type");
                    return;
            }
            connection = (Connection) DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/library",
                    user, pass
            );

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
    }

    private void setType(String login, String password) {
        try {
            connection = (Connection) DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/library",
                    "Server_Login", "LoginPassword"
            );
            try (PreparedStatement statement = connection.prepareStatement("""
                SELECT password
                FROM login_view
                WHERE login = ?
            """)) {
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String storedHashedPassword = resultSet.getString("password");
                    if (verifyPassword(password, storedHashedPassword)){ // Verify the password
                        checkType(login);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void checkType(String login) {
        try {


            try (PreparedStatement statement = connection.prepareStatement("""
            SELECT type
            FROM login_view
            WHERE login = ?
        """)) {
                statement.setString(1, login);
                //statement.setString(2, password);
                //statement.setString(2, hashPassword(password));

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String val1 = resultSet.getString(1); // by column index
                    type = val1.charAt(0);
                    System.out.println("User type: " + type);
                    //id = resultSet.getInt(2);
                    // ... use val1 and val2 ...
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
    }

    public DBConnection(String login, String password) {
        setType(login, password);
        connect(login, password);
        setID(login, password);
    }

    public char getType() {
        return type;
    }

    public void setID(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement("""
            SELECT id
            FROM users
            WHERE login = ?
        """)) {
            statement.setString(1, login);
            //statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //String val1 = resultSet.getString(1); // by column index
                //type = val1.charAt(0);
                id = resultSet.getInt(1);
                // ... use val1 and val2 ...
                return;
            }
        } catch (SQLException ex) {
            System.out.println("Connection failed");
        }
    }

    public int getId() {
        return id;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean pass_change(String login, String password) {
        try {
            connection = (Connection) DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/library",
                    "Pass_Changer", "ChangerPassword"
            );
            try (PreparedStatement statement = connection.prepareStatement("""
            UPDATE users
            SET password = ?
            WHERE login = ?
        """)) {
                statement.setString(1, hashPassword(password));
                statement.setString(2, login);
                statement.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
        return false;
    }
}


