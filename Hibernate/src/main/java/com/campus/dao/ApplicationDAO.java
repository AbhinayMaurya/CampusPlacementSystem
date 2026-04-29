package com.campus.dao;

import java.sql.*;
import com.campus.config.DBConnection;
import java.util.ArrayList;
import java.util.List;
import com.campus.model.Application;

public class ApplicationDAO {

    public boolean applyForJob(int studentId, int companyId) {
        if (hasApplied(studentId, companyId)) {
            System.out.println("[INFO] You have already applied for this drive.");
            return false;
        }

        String query = "INSERT INTO applications (student_id, company_id, status) VALUES (?, ?, 'Applied')";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, companyId);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasApplied(int studentId, int companyId) {
        String query = "SELECT * FROM applications WHERE student_id = ? AND company_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, companyId);
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Application> getStudentApplications(int studentId) {
        List<Application> list = new ArrayList<>();
        String query = "SELECT c.company_name, c.job_role, a.status " +
                       "FROM applications a " +
                       "JOIN companies c ON a.company_id = c.company_id " +
                       "WHERE a.student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Application app = new Application();
                app.setCompanyName(rs.getString("company_name"));
                app.setJobRole(rs.getString("job_role"));
                app.setStatus(rs.getString("status"));
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 1. Fetch all applications for the Admin
    public List<Application> getAllApplications() {
        List<Application> list = new ArrayList<>();
        String query = "SELECT a.student_id, a.company_id, s.full_name, c.company_name, c.job_role, a.status " +
                       "FROM applications a " +
                       "JOIN students s ON a.student_id = s.student_id " +
                       "JOIN companies c ON a.company_id = c.company_id";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Application app = new Application();
                app.setStudentId(rs.getInt("student_id"));
                app.setCompanyId(rs.getInt("company_id"));
                app.setStudentName(rs.getString("full_name"));
                app.setCompanyName(rs.getString("company_name"));
                app.setJobRole(rs.getString("job_role"));
                app.setStatus(rs.getString("status"));
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Update Application Status
    public boolean updateStatus(int studentId, int companyId, String newStatus) {
        String query = "UPDATE applications SET status = ? WHERE student_id = ? AND company_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, studentId);
            pstmt.setInt(3, companyId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}