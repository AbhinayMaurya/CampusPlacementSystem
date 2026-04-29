package com.campus.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.campus.dao.StudentDAO;
import com.campus.dao.AdminDAO;
import com.campus.model.Student;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    
    private StudentDAO studentDAO = new StudentDAO();
    private AdminDAO adminDAO = new AdminDAO(); 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String action = req.getParameter("action");

        if ("login".equals(action)) {
            handleStudentLogin(req, resp);
        } else if ("admin-login".equals(action)) {
            handleAdminLogin(req, resp); // New Case
        } else if ("register".equals(action)) {
            handleRegister(req, resp);   // New Case
        } else if ("logout".equals(action)) {
            handleLogout(req, resp);
        } else {
            resp.sendRedirect("login.html");
        }
    }

    private void handleStudentLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        Student student = studentDAO.loginStudent(email, pass);

        if (student != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", student);
            session.setAttribute("role", "student"); // Add role
            resp.sendRedirect("dashboard.jsp");
        } else {
            resp.sendRedirect("login.html?error=1");
        }
    }

    private void handleAdminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        // Use the verifyAdmin method from your AdminDAO
        if (adminDAO.verifyAdmin(user, pass)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", "admin"); // Critical for security!
            resp.sendRedirect("admin-dashboard.jsp");
        } else {
            resp.sendRedirect("admin-login.html?error=1");
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Collect all form data
        String name = req.getParameter("fullName");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String branch = req.getParameter("branch");
        double cgpa = Double.parseDouble(req.getParameter("cgpa"));
        int year = Integer.parseInt(req.getParameter("passoutYear"));

        Student s = new Student(name, email, pass, branch, cgpa, year);

        boolean success = studentDAO.addStudent(s);

        if (success) {
            resp.sendRedirect("login.html?registered=true");
        } else {
            resp.sendRedirect("register.html?error=1");
        }
    }

    private void handleLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect("login.html");
    }
}