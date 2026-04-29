package com.campus.practical7.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.campus.practical7.model.Company;

public class HibernateTest {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        try (Session session = factory.openSession()) {
            String hql = "from Company order by companyId";
            List<Company> companies = session.createQuery(hql, Company.class).list();

            System.out.println("Practical 7: Hibernate ORM Mapping + HQL");
            System.out.println("HQL Query: " + hql);
            System.out.printf("%-3s %-20s %-20s %-8s %-12s %-8s%n",
                    "ID", "Company", "Role", "Package", "Date", "CGPA");

            for (Company company : companies) {
                System.out.printf("%-3d %-20s %-20s %-8.2f %-12s %-8.2f%n",
                        company.getCompanyId(),
                        company.getCompanyName(),
                        company.getJobRole(),
                        company.getPackageLpa(),
                        company.getArrivalDate(),
                        company.getMinCgpa());
            }
        } finally {
            factory.close();
        }
    }
}
