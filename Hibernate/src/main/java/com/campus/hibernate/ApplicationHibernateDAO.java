package com.campus.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.campus.model.Application;

public class ApplicationHibernateDAO {
    public boolean applyForJob(int studentId, int companyId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Application app = new Application();
            app.setStudentId(studentId);
            app.setCompanyId(companyId);
            app.setStatus("Applied");
            session.persist(app);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<Application> getAllApplications() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Application order by appId", Application.class).list();
        }
    }
}
