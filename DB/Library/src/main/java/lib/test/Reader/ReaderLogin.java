package lib.test.Reader;

import lib.test.Admin.Admin;
import lib.test.DBController.DBConnection;
import lib.test.DBController.DBController;

import java.sql.SQLException;
import java.util.Scanner;

public class ReaderLogin {
    DBConnection dbConnection;
    public ReaderLogin() {
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Wpisz login");
            String login = input.nextLine();
            if(new DBConnection().pass_setup(login)) {
                System.out.println("Wpisz nowe haslo");
                String password = input.nextLine();
                if(new DBConnection().pass_change(login, password)) {
                    System.out.println("Haslo zmienione");
                } else {
                    System.out.println("Blad zmiany hasla");
                }
                continue;
            }
            System.out.println("Wpisz haslo");
            String password = input.nextLine();
            dbConnection = new DBConnection(login, password);
            if(dbConnection.getId()!=-1) {
                System.out.println("Zalogowano pomyslnie");
                new Reader(dbConnection);
                break;
            } else {
                System.out.println("Niepoprawne dane");
            }
        }
    }

}
