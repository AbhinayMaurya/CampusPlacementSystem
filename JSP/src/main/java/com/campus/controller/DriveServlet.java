package com.campus.controller;

import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.campus.dao.CompanyDAO;
import com.campus.model.Company;

@WebServlet("/drive")
public class DriveServlet extends HttpServlet {

    private CompanyDAO companyDAO = new CompanyDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add-drive".equals(action)) {
            handleAddDrive(req, resp);
        } else if ("delete-drive".equals(action)) {
            handleDeleteDrive(req, resp);
        } else if ("update-drive".equals(action)) { // NEW: Handle Update
            handleUpdateDrive(req, resp);
        } else {
            resp.sendRedirect("admin-dashboard.jsp");
        }
    }

    private void handleAddDrive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String name = req.getParameter("companyName");
            String role = req.getParameter("jobRole");
            double pkg = Double.parseDouble(req.getParameter("packageLpa"));
            double cgpa = Double.parseDouble(req.getParameter("minCgpa"));
            Date arrivalDate = Date.valueOf(req.getParameter("arrivalDate"));

            Company company = new Company(name, role, pkg, arrivalDate, cgpa);
            boolean success = companyDAO.addCompany(company);

            if (success) {
                resp.sendRedirect("add-drive.jsp?status=success");
            } else {
                resp.sendRedirect("add-drive.jsp?status=error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("add-drive.jsp?status=error");
        }
    }

    // NEW METHOD: Delete the drive
    private void handleDeleteDrive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int companyId = Integer.parseInt(req.getParameter("companyId"));
            
            boolean success = companyDAO.deleteCompany(companyId);

            if (success) {
                resp.sendRedirect("admin-view-drives.jsp?status=deleted");
            } else {
                resp.sendRedirect("admin-view-drives.jsp?status=error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("admin-view-drives.jsp?status=error");
        }
    }

    // NEW METHOD: Update the drive
    private void handleUpdateDrive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int companyId = Integer.parseInt(req.getParameter("companyId"));
            double newPackage = Double.parseDouble(req.getParameter("newPackage"));
            Date newDate = Date.valueOf(req.getParameter("newDate"));
            
            // Call the existing method in your CompanyDAO!
            boolean success = companyDAO.updateCompany(companyId, newPackage, newDate);

            if (success) {
                // Redirect back to manage drives page
                resp.sendRedirect("admin-view-drives.jsp");
            } else {
                resp.sendRedirect("edit-drive.jsp?status=error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("edit-drive.jsp?status=error");
        }
    }
}