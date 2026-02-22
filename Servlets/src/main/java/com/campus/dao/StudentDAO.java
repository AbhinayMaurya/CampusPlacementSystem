package com.campus.dao;

import java.sql.*;
import com.campus.config.DBConnection;
import com.campus.model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean addStudent(Student student) {
        String query = "INSERT INTO students (full_name, email, password, branch, cgpa, passout_year) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, student.getFullName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPassword());
            pstmt.setString(4, student.getBranch());
            pstmt.setDouble(5, student.getCgpa());
            pstmt.setInt(6, student.getPassoutYear());

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Student loginStudent(String email, String password) {
        Student s = null;
        String query = "SELECT * FROM students WHERE email = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                s = new Student();
                s.setStudentId(rs.getInt("student_id"));
                s.setFullName(rs.getString("full_name"));
                s.setEmail(rs.getString("email"));
                s.setBranch(rs.getString("branch"));
                s.setCgpa(rs.getDouble("cgpa"));
                s.setPassoutYear(rs.getInt("passout_year"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    
    public boolean updateStudentCgpa(int studentId, double newCgpa) {
        String query = "UPDATE students SET cgpa = ? WHERE student_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setDouble(1, newCgpa);
            pstmt.setInt(2, studentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getInt("student_id"));
                s.setFullName(rs.getString("full_name"));
                s.setEmail(rs.getString("email"));
                s.setBranch(rs.getString("branch"));
                s.setCgpa(rs.getDouble("cgpa"));
                s.setPassoutYear(rs.getInt("passout_year"));
                
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}