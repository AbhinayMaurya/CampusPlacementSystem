package com.campus.hibernate;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.campus.model.Company;

public class CompanyHibernateDAO {
    public boolean addCompany(Company company) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(company);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<Company> getAllCompanies() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Company order by companyId", Company.class).list();
        }
    }

    public List<Company> searchCompanies(String text) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Company where companyName like :text or jobRole like :text", Company.class)
                    .setParameter("text", "%" + text + "%").list();
        }
    }

    public boolean updateCompany(int id, double newPackage, Date newDate) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            int rows = session.createMutationQuery("update Company set packageLpa = :pkg, arrivalDate = :date where companyId = :id")
                    .setParameter("pkg", newPackage).setParameter("date", newDate).setParameter("id", id).executeUpdate();
            tx.commit();
            return rows > 0;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCompany(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            int rows = session.createMutationQuery("delete from Company where companyId = :id")
                    .setParameter("id", id).executeUpdate();
            tx.commit();
            return rows > 0;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
