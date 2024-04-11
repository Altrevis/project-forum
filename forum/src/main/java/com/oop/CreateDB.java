package com.oop;
import java.sql.*;

public class CreateDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            
            
            String sqlCreateDatabase = "CREATE DATABASE IF NOT EXISTS db_ynov";
            statement.executeUpdate(sqlCreateDatabase);
            
            
            String sqlUseDatabase = "USE db_ynov";
            statement.executeUpdate(sqlUseDatabase);
            
            
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, nom VARCHAR(50))";
            statement.executeUpdate(sqlCreateTable);
            
            System.out.println("Table créée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
