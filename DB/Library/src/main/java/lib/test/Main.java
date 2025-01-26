package lib.test;

import lib.test.Admin.AdminLogin;
import lib.test.Reader.ReaderLogin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Zaloguj sie jako:\n1. Czytelnik\n2. Administrator");
        Scanner input;
        int choice = 0;
        while(true) {
            input = new Scanner(System.in);
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Podano niepoprawna wartosc");
            }
            if(choice == 1) {
                new ReaderLogin();
                break;
            } else if(choice == 2) {
                new AdminLogin();
                break;
            } else {
                System.out.println("Podano niepoprawna wartosc");
            }
        }
        input.close();
    }
}