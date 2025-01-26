package lib.test.Admin;

import lib.test.DBController.DBConnection;
import lib.test.DBController.DBController;

import java.util.Scanner;

public class AdminLogin {
    public AdminLogin() {
        Scanner input = new Scanner(System.in);
        DBConnection dbConnection;
        while(true) {
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
            System.out.println(dbConnection.getId());
            if(dbConnection.getId()!=-1) {
                System.out.println("Zalogowano pomyslnie");
                if(dbConnection.getType() == 's') {
                    new SuperAdmin(dbConnection);
                } else if(dbConnection.getType()=='a'){
                    new Admin(dbConnection);
                }else
                System.out.println("Nie jestes administratorem");
                break;
            } else {
                System.out.println("Niepoprawne dane");
            }
        }
        input.close();
    }
}
