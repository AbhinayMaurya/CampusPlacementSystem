package com.campus.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        Properties props = new Properties();

        try {
            // Load properties file
            InputStream in = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (in == null) {
                throw new RuntimeException("db.properties file not found in classpath");
            }

            props.load(in);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            // ⚠️ Driver loading (optional in modern Java, but okay to keep)
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            throw new RuntimeException("Database connection failed", e);
        }

        return con;
    }
}