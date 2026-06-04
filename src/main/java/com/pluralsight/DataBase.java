package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase
{
    public static void databaseTest()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup26");

        String scuffedHeader = " Id   Name    Price    Stock";
        String scuffedHeader2 = "--|---------|-------|---------";
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

                System.out.printf("%d %s: $%.2f %.2f%n", productId, name, price, unitsInStock);

            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
