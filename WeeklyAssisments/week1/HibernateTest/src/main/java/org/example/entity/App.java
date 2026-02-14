package org.example.entity;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== Restaurant Management System =====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Price");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addItem();
                    break;

                case 2:
                    viewItems();
                    break;

                case 3:
                    updatePrice();
                    break;

                case 4:
                    deleteItem();
                    break;

                case 5:
                    HibernateUtil.shutdown();
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // 1. Add Item
    public static void addItem() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Category: ");
        sc.nextLine();
        String category = sc.nextLine();

        System.out.print("Available (true/false): ");
        boolean available = sc.nextBoolean();

        MenuItem item = new MenuItem(name, price, category, available);

        session.save(item);

        tx.commit();
        session.close();

        System.out.println("Item Added Successfully!");
    }

    // 2. View Items (HQL)
    public static void viewItems() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<MenuItem> query =
                session.createQuery("FROM MenuItem", MenuItem.class);

        List<MenuItem> list = query.list();

        System.out.println("\n------ MENU ITEMS ------");

        for (MenuItem item : list) {

            System.out.println(
                    item.getId() + " | " +
                            item.getName() + " | " +
                            item.getPrice() + " | " +
                            item.getCategory() + " | " +
                            item.isAvailable()
            );
        }

        session.close();
    }

    // 3. Update Price
    public static void updatePrice() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        MenuItem item = session.get(MenuItem.class, id);

        if (item != null) {

            System.out.print("Enter New Price: ");
            double price = sc.nextDouble();

            item.setPrice(price);

            session.update(item);
            tx.commit();

            System.out.println("Price Updated!");

        } else {
            System.out.println("Item Not Found!");
        }

        session.close();
    }

    // 4. Delete Item
    public static void deleteItem() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        MenuItem item = session.get(MenuItem.class, id);

        if (item != null) {

            session.delete(item);
            tx.commit();

            System.out.println("Item Deleted!");

        } else {
            System.out.println("Item Not Found!");
        }

        session.close();
    }
}
