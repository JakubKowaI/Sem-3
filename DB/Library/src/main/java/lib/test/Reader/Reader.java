package lib.test.Reader;

import lib.test.DBController.DBConnection;
import lib.test.DBController.DBController;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Reader {

    private void showMenu (){
        System.out.println("1. Znajdz ksiazke\n2. Wypozycz ksiazke\n3. Oddaj ksiazke\n4. Wyswietl wypozyczenia");
        System.out.println("0. Wyloguj");
    }

    public Reader(DBConnection dbConnection) {
            System.out.println("Zalogowano jako czytelnik z id: " + dbConnection.getId());
            DBController myDBController = new DBController(dbConnection);
            Scanner input = new Scanner(System.in);
            int choice = -1;
            while (true) {
                showMenu();
                try {
                    choice = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Podano niepoprawna wartosc");
                }
                if (choice == 0) {
                    exit(0);
                }
                    switch (choice) {
                        case 1:
                            //znajdz ksiazke
                            System.out.println("Podaj tytul ksiazki\n");
                            String title = input.next();

                            myDBController.findBook(title);
                            break;
                        case 2:
                            //wypozycz ksiazke
                            System.out.println("Podaj tytul ksiazki\n");
                            title = input.next();

                            myDBController.rentBook(dbConnection.getId(), title);
                            break;
                        case 3:
                            //oddaj ksiazke
                            System.out.println("Podaj id ksiazki\n");
                            int bookId2 = -1;

                            try {
                                bookId2 = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Podano niepoprawna wartosc");
                                break;
                            }
                            myDBController.returnBook(dbConnection.getId(), bookId2);
                            break;
                        case 4:
                            //wyswietl wypozyczenia
                            myDBController.showMyRentals(dbConnection.getId());
                            break;

                        default:
                            System.out.println("Podano niepoprawna wartosc");
                            break;
                    }
            }
            //input.close();
    }

}

