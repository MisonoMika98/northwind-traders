package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DataBase
{
    private static Scanner userInput = new Scanner(System.in);


    public static void homeScreen()
    {
        while (true)
        {
            System.out.println();
            System.out.println("  What do you want to do?");
            System.out.println("  1) Display all products");
            System.out.println("  2) Display all customers");
            System.out.println("  0) Exit");
            System.out.println("================================");
            System.out.println();


            String choice = getUserInput("Select an option here: ");

            switch (choice)
            {
                case "1":
                    databaseTest();
                    break;

                case "2":
                    databaseTest2();
                    break;

                case "0":
                    System.out.println();
                    System.out.println("See you again soon!");
                    System.exit(0);

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
            }
        }
    }



    public static void databaseTest()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup26");

        String scuffedHeader = " Id   Name    Price    Stock";
        String scuffedHeader2 = "-- --------- ------- ---------";
        System.out.println(scuffedHeader);
        System.out.println(scuffedHeader2);

        String sql = "SELECT ProductId, ProductName, UnitPrice, UnitsInStock FROM products";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {

                int productId = rs.getInt("ProductId");
                String name = rs.getString("ProductName");
                double price = rs.getDouble("UnitPrice");
                double unitsInStock = rs.getDouble("UnitsInStock");

                System.out.printf("%d | %s: $%.2f | %.2f%n", productId, name, price, unitsInStock);

            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



    public static void databaseTest2()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup26");

        String scuffedHeader = " Name   Company     City    Country   Phone";
        String scuffedHeader2 = "------ --------- ------- --------- ---------";
        System.out.println(scuffedHeader);
        System.out.println(scuffedHeader2);

        String sql = "SELECT ContactName, CompanyName, City, Country, Phone FROM customers ORDER BY country";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {

                String contactName = rs.getString("ContactName");
                String companyName = rs.getString("CompanyName");
                String city = rs.getString("City");
                String country = rs.getString("Country");
                String phoneNumber = rs.getString("Phone");

                System.out.printf("%s | %s | %s | %s | %s%n", contactName, companyName, city, country, phoneNumber);

            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



    // helper method
    public static String getUserInput(String message)
    {
        System.out.print(message);
        return userInput.nextLine().trim().toUpperCase();
    }
}
