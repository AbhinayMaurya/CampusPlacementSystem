package com.campus.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.campus.dao.ApplicationDAO;
import com.campus.model.Student;

@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {
    
    private ApplicationDAO applicationDAO = new ApplicationDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null) {
            resp.sendRedirect("login.html");
            return;
        }

        String action = req.getParameter("action");

        // --- ADMIN ACTION: Update Status ---
        if ("admin".equals(role) && "update-status".equals(action)) {
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            int companyId = Integer.parseInt(req.getParameter("companyId"));
            String status = req.getParameter("status");

            boolean success = applicationDAO.updateStatus(studentId, companyId, status);
            if (success) {
                resp.sendRedirect("admin-view-applications.jsp?status=updated");
            } else {
                resp.sendRedirect("admin-view-applications.jsp?status=error");
            }
            return;
        }

        // --- STUDENT ACTION: Apply for a job ---
        if ("student".equals(role)) {
            Student user = (Student) session.getAttribute("user");
            int companyId = Integer.parseInt(req.getParameter("companyId"));
            int studentId = user.getStudentId();

            if (applicationDAO.hasApplied(studentId, companyId)) {
                resp.sendRedirect("view-jobs.jsp?status=exists");
            } else {
                boolean success = applicationDAO.applyForJob(studentId, companyId);
                if (success) {
                    resp.sendRedirect("view-jobs.jsp?status=applied");
                } else {
                    resp.sendRedirect("view-jobs.jsp?status=error");
                }
            }
        }
    }
}