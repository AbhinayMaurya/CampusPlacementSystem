package com.campus.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.campus.config.DBConnection;
import com.campus.model.Company;

public class CompanyDAO {

    public boolean addCompany(Company company) {
        boolean isAdded = false;
        
        String query = "INSERT INTO companies (company_name, job_role, package_lpa, arrival_date, min_cgpa) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();
            
            PreparedStatement pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, company.getCompanyName());
            pstmt.setString(2, company.getJobRole());
            pstmt.setDouble(3, company.getPackageLpa());
            pstmt.setDate(4, company.getArrivalDate());
            pstmt.setDouble(5, company.getMinCgpa());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                isAdded = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    public List<Company> getAllCompanies() {
        List<Company> list = new ArrayList<>();
        String query = "SELECT * FROM companies";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Company c = new Company();
                c.setCompanyId(rs.getInt("company_id"));
                c.setCompanyName(rs.getString("company_name"));
                c.setJobRole(rs.getString("job_role"));
                c.setPackageLpa(rs.getDouble("package_lpa"));
                c.setArrivalDate(rs.getDate("arrival_date"));
                c.setMinCgpa(rs.getDouble("min_cgpa"));
                
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Company> searchCompanies(String searchText) {
        List<Company> list = new ArrayList<>();
        String query = "SELECT * FROM companies WHERE company_name LIKE ? OR job_role LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            String pattern = "%" + searchText + "%";
            pstmt.setString(1, pattern);
            pstmt.setString(2, pattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Company c = new Company();
                    c.setCompanyId(rs.getInt("company_id"));
                    c.setCompanyName(rs.getString("company_name"));
                    c.setJobRole(rs.getString("job_role"));
                    c.setPackageLpa(rs.getDouble("package_lpa"));
                    c.setArrivalDate(rs.getDate("arrival_date"));
                    c.setMinCgpa(rs.getDouble("min_cgpa"));
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateCompany(int id, double newPackage, Date newDate) {
        String query = "UPDATE companies SET package_lpa = ?, arrival_date = ? WHERE company_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setDouble(1, newPackage);
            pstmt.setDate(2, newDate);
            pstmt.setInt(3, id);

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCompany(int id) {
        String query = "DELETE FROM companies WHERE company_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
