package org.example.registration.util;

import java.sql.Connection;

public class DBConnectionTest {

    public static void main(String[] args) {

        try {
            Connection connection = DatabaseConnection.getConnection();

            System.out.println("Database connection successful!");

            connection.close();

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}