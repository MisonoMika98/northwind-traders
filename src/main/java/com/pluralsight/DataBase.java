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

        String sql = "SELECT ProductName FROM products";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {

            while (rs.next())
            {

                String name = rs.getString("ProductName");
                double price = rs.getDouble("UnitPrice");
                System.out.printf("%s: $%.2f%n", name, price);



            }

        }

        catch (SQLException e)
        {

            e.printStackTrace();

        }
    }


}
