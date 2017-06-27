package com.shop.thrift;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "barsik";
    public static void main(String[] args) {
        Connection connection;

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD );

            if(!connection.isClosed()) {
                System.out.println("DB is connected");
            }

            connection.close();

            if(connection.isClosed()) {
                System.out.println("DB is disconnected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

