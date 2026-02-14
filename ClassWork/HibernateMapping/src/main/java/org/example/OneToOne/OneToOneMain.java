package org.example.OneToOne;

import org.example.OneToOne.entity.Passport;
import org.example.OneToOne.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class OneToOneMain {
    public static void main(String[] args) {
        Scanner sp = new Scanner(System.in);
        Configuration factory = new Configuration().configure();
        SessionFactory sf = factory.buildSessionFactory();

        while (true) {
            System.out.println("\n1: Add Person and Passport");
            System.out.println("2: Exit");
            int choice = sp.nextInt();
            sp.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter person Name: ");
                    String name = sp.nextLine();

                    System.out.println("Enter passport number: ");
                    String passportNumber = sp.nextLine();

                    System.out.println("Enter country: ");
                    String country = sp.nextLine();

                    try (Session session = sf.openSession()) {
                        Transaction txt = session.beginTransaction();

                        Passport passport = new Passport(passportNumber, country);
                        Person person = new Person(name, passport);

                        session.persist(person);

                        txt.commit();
                    }

                    System.out.println("Person and Passport saved successfully");
                    break;

                case 2:
                    sf.close();
                    sp.close();
                    System.out.println("Exiting.... ");
                    return; // exit program
            }
        }
    }
}