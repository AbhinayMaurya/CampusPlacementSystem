package com.campus.dao;

import java.sql.*;
import com.campus.config.DBConnection;

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

    public void getMyApplications(int studentId) {
        String query = "SELECT c.company_name, c.job_role, a.status " +
                       "FROM applications a " +
                       "JOIN companies c ON a.company_id = c.company_id " +
                       "WHERE a.student_id = ?";

        System.out.println("\n--- My Applications ---");
        System.out.println("| Company         | Role                 | Status      |");
        System.out.println("--------------------------------------------------------");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                String comp = rs.getString("company_name");
                String role = rs.getString("job_role");
                String status = rs.getString("status");
                
                System.out.printf("| %-15s | %-20s | %-11s |\n", comp, role, status);
            }
            if (!found) System.out.println("| No applications found.                       |");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}