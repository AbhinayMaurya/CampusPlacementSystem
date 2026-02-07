package com.campus.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {
        Connection Connection = null;
        Properties props = new Properties();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");

            if (in == null) {
                System.out.println("Error: db.properties file not found in src!");
                return null;
            }

            props.load(in);

            String URL = props.getProperty("db.url");
            String USER = props.getProperty("db.user"); 
            String PASS = props.getProperty("db.password");
            
            Connection = DriverManager.getConnection(URL, USER, PASS); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Connection;
    }
}