package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

public class Main
{
    static void main()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

    }
}
