package com.campus.hibernate;

import java.io.InputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Properties props = new Properties();
            try (InputStream in = HibernateUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
                props.load(in);
            }
            Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
            cfg.setProperty("hibernate.connection.url", props.getProperty("db.url"));
            cfg.setProperty("hibernate.connection.username", props.getProperty("db.user"));
            cfg.setProperty("hibernate.connection.password", props.getProperty("db.password"));
            return cfg.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
