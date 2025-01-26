package lib.test.Admin;

import lib.test.DBController.DBConnection;
import lib.test.DBController.DBController;

import java.util.Scanner;

import static java.lang.System.exit;

public class SuperAdmin {
    private void showMenu() {
        System.out.println("1. Dodaj ksiazke\n2. Usun ksiazke\n3. Wyswietl wypozyczenia\n4. Dodaj czytelnika\n5. Usun czytelnika\n6. Wyswietl czytelnikow");
        System.out.println("7. Dodaj administratora\n8. Usun administratora\n9. Wyswietl administratorow\n10. Wyswietl kopie ksiazki");
        System.out.println("0. Wyloguj");
    }

    public SuperAdmin(DBConnection dbConnection) {
        System.out.println("Zalogowano jako admin z id: " + dbConnection.getId());
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
                    //dodaj ksiazke
                    System.out.println("Podaj tytul ksiazki\n");
                    String title = input.next();
                    System.out.println("Podaj autora ksiazki\n");
                    input.nextLine();
                    String author = input.nextLine();
                    System.out.println("Podaj ISBN ksiazki\n");
                    String isbn = input.next();
//                    try {
//                        isbn = input.nextLong();
//                    } catch (Exception e) {
//                        System.out.println("Podano niepoprawna wartosc");
//                        break;
//                    }
                    System.out.println("Podaj rok wydania ksiazki\n");
                    int year;

                    try {
                        year = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Podano niepoprawna wartosc");
                        break;
                    }

                    myDBController.addBook(title, author,isbn, year);
                    break;
                case 2:
                    //usun ksiazke
                    System.out.println("Podaj id kopii ksiazki\n");
                    int bookId;
                    try {
                        bookId = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Podano niepoprawna wartosc");
                        break;
                    }
                    myDBController.deleteBook(bookId);
                    break;
                case 3:
                    //wyswietl wypozyczenia
                    myDBController.showRentals();
                    break;
                case 4:
                    //dodaj czytelnika
                    System.out.println("Podaj imie czytelnika\n");
                    String name = input.next();
                    System.out.println("Podaj nazwisko czytelnika\n");
                    String surname = input.next();
                    System.out.println("Podaj pesel czytelnika\n");
                    String pesel = input.next();
                    System.out.println("Podaj login czytelnika\n");
                    String login = input.next();

                    myDBController.addReader(name, surname, pesel, login);
                    break;
                case 5:
                    //usun czytelnika
                    System.out.println("Podaj id czytelnika\n");
                    int readerId = -1;
                    try {
                        readerId = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Podano niepoprawna wartosc");
                        break;
                    }

                    myDBController.deleteReader(readerId);
                    break;
                case 6:
                    //wyswietl czytelnikow
                    myDBController.showReaders();
                    break;
                case 7:
                    //dodaj administratora
                    System.out.println("Podaj imie administratora\n");
                    String adminName = input.next();
                    System.out.println("Podaj nazwisko administratora\n");
                    String adminSurname = input.next();
                    System.out.println("Podaj pesel administratora\n");
                    String adminPesel = input.next();
                    System.out.println("Podaj login administratora\n");
                    String adminLogin = input.next();

                    myDBController.addAdmin(adminName, adminSurname, adminPesel,adminLogin);
                    break;
                case 8:
                    //usun administratora
                    System.out.println("Podaj id administratora\n");
                    int adminId = -1;
                    try {
                        adminId = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Podano niepoprawna wartosc");
                        break;
                    }
                    myDBController.deleteAdmin(adminId);
                    break;
                case 9:
                    //wyswietl administratorow
                    myDBController.showAdmins();
                    break;
                case 10:
                    //wyswietl kopie ksiazki
                    System.out.println("Podaj tytul ksiazki\n");
                    String title2 = input.next();
                    myDBController.showCopies(title2);
                    break;
                default:
                    System.out.println("Podano niepoprawna wartosc");
                    break;
            }


        }
        //input.close();
    }
}
