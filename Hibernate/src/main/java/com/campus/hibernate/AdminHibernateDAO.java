package com.campus.hibernate;

import org.hibernate.Session;

import com.campus.model.Admin;

public class AdminHibernateDAO {
    public boolean verifyAdmin(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Admin where username = :username and password = :password", Admin.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .uniqueResult() != null;
        }
    }
}
