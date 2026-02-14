package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.example.entity.Student;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Load configuration and build session factory
        Configuration cfg = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class);

        SessionFactory factory = cfg.buildSessionFactory();
        // Session 1
        Session session1 = factory.openSession();
        Student s1 = session1.get(Student.class, 101); // SQL query runs here
        session1.close();

        // Session 2
        Session session2 = factory.openSession();
        Student s2 = session2.get(Student.class, 101); // No SQL query! Loaded from Ehcache.
        session2.close();

        Session session = factory.openSession();


        Transaction tx = session.beginTransaction();

        Scanner sp = new Scanner(System.in);

        try {
            int choice;
            do {
                System.out.print("Enter choice: 1->INSERT, 2->FETCH, 3->UPDATE, 4->DELETE, 5->ALLDATA, 0->EXIT: ");
                choice = sp.nextInt();
                sp.nextLine(); // consume newline

                switch (choice) {
                    case 1: // INSERT
                        tx = session.beginTransaction();
                        System.out.print("Enter name: ");
                        String name = sp.nextLine();
                        System.out.print("Enter age: ");
                        int age = sp.nextInt();
                        Student s = new Student(name, age);
                        session.persist(s);
                        tx.commit();
                        System.out.println("Inserted: " + s);
                        break;

                    case 2: // FETCH
                        System.out.print("Enter student ID: ");
                        int id = sp.nextInt();
                        Student fetched = session.get(Student.class, id);
                        System.out.println(fetched != null ? "Fetched: " + fetched : "Not found!");
                        break;

                    case 3: // UPDATE
                        tx = session.beginTransaction();
                        System.out.print("Enter student ID: ");
                        int uid = sp.nextInt();
                        Student toUpdate = session.get(Student.class, uid);
                        if (toUpdate != null) {
                            System.out.print("Enter new age: ");
                            toUpdate.setAge(sp.nextInt());
                            System.out.println("Updated: " + toUpdate);
                        } else {
                            System.out.println("Not found!");
                        }
                        tx.commit();
                        break;

                    case 4: // DELETE
                        tx = session.beginTransaction();
                        System.out.print("Enter student ID: ");
                        int did = sp.nextInt();
                        Student toDelete = session.get(Student.class, did);
                        if (toDelete != null) {
                            session.remove(toDelete);
                            System.out.println("Deleted!");
                        } else {
                            System.out.println("Not found!");
                        }
                        tx.commit();
                        break;

                    case 5: // ALLDATA
                        List<Student> students = session.createQuery("from Student", Student.class).list();
                        students.forEach(System.out::println);
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 0);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            sp.close();
            session.close();
            factory.close();
        }
    }
}