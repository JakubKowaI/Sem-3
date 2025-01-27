package lib.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
  public static void main(String[] args) {
    int choice = 0;
    final Map<String, Integer> books = new HashMap<>();
    final Map<Integer, Readers> users = new HashMap<>();
    final Scanner scan = new Scanner(System.in);
    books.put("Harry Potter", 1);
    while (choice != 6) {
      try {
        System.out.print("1. Dodaj ksiazke\n2. Wypozycz ksiazke\n3. Dodaj czytelnika\n4. Wypisz ksiazki\n5. Wypisz czytelnikow\n6. Wyjdz\n");
        choice = Integer.parseInt(scan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Podaj poprawna liczbe");
        System.out.printf(e.getMessage());
      }
      if (choice == 1) {
        System.out.println("Podaj tytul ksiazki");
        final String title = scan.nextLine();
        if (books.containsKey(title)) {
          books.put(title, books.get(title) + 1);
          System.out.println("Dolozono kolejna");
        } else {
          books.put(title, 1);
          System.out.printf("Dodano nowa ksiazke %s\n", title);
        }
      } else if (choice == 2) {
        System.out.print("Podaj nr czytelnika\n");
        int usrNr = 1;
        while (usrNr != 0) {
          try {
            usrNr = Integer.parseInt(scan.nextLine());
            if (users.containsKey(usrNr)) {
              System.out.println("Podaj tytul ksiazki");
              final String title = scan.nextLine();
              if (books.containsKey(title) && books.get(title) > 0) {
                System.out.println("Ksiazka zostala wypozyczona");
                books.replace(title, books.get(title) - 1);
                users.get(usrNr).getBook(title);
              } else {
                System.out.println("Ksiazka nie jest dostepna");
              }
              break;
            } else {
              System.out.println("Czytelnik nie istnieje");
            }
          } catch (NumberFormatException e) {
            System.out.println("Podaj poprawny nr czytelnika");
            System.out.printf(e.getMessage());
          }
        }
      } else if (choice == 3) {
        try {
          System.out.println("Podaj nr czytelinka");
          final int usrNr = Integer.parseInt(scan.nextLine());
          if (users.containsKey(usrNr)) {
            System.out.println("Czytelnik juz istnieje");
          } else {
            users.put(usrNr, new Readers());
            System.out.println("Dodano nowego czytelnika");
          }
          System.out.println("Podaj imie czytelnika");
          users.get(usrNr).name = scan.nextLine();
          System.out.println("Podaj nazwisko czytelnika");
          users.get(usrNr).surname = scan.nextLine();
          System.out.println("Czytelnik: " + users.get(usrNr).name + " " + users.get(usrNr).surname);
        } catch (NumberFormatException e) {
          System.out.println("Podaj poprawny nr czytelnika");
          System.out.printf(e.getMessage());
        }
      } else if (choice == 4) {
        for (final Map.Entry<String, Integer> entry : books.entrySet()) {
          System.out.println(entry.getKey() + " " + entry.getValue());
        }
      } else if (choice == 5) {
        for (final Map.Entry<Integer, Readers> entry : users.entrySet()) {
          System.out.println("nr: " + entry.getKey() + " " + entry.getValue().name + " " + entry.getValue().surname);
        }
      }
    }
    for (final Map.Entry<Integer, Readers> entry : users.entrySet()) {
      System.out.println("Czytelnik: " + entry.getValue().name + " " + entry.getValue().surname);
      System.out.println(entry.getValue().listBooks());
    }
    scan.close();
  }
}