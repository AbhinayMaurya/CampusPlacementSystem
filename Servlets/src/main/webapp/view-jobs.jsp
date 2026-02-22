<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.campus.model.Company" %>
<%@ page import="com.campus.dao.CompanyDAO" %>
<%@ page import="com.campus.model.Student" %>

<%
    // 1. Security Check: Ensure a student is logged in
    Student user = (Student) session.getAttribute("user");
    if (user == null || !"student".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.html");
        return;
    }

    // 2. Fetch all companies from the database
    CompanyDAO companyDAO = new CompanyDAO();
    List<Company> companies = companyDAO.getAllCompanies();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Available Drives - Campus Placement</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* Table Styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .container-large {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            width: 800px; /* Wider container for the table */
            margin: 20px auto;
        }
    </style>
</head>
<body>
    <div class="container-large">
        <h2 style="color: #007bff; text-align: center;">🏢 Upcoming Placement Drives</h2>
        
        <% if ("applied".equals(request.getParameter("status"))) { %>
            <p style="color: green; text-align: center;">Successfully applied for the drive!</p>
        <% } else if ("exists".equals(request.getParameter("status"))) { %>
            <p style="color: #d35400; text-align: center;">You have already applied for this company.</p>
        <% } %>

        <table>
            <thead>
                <tr>
                    <th>Company Name</th>
                    <th>Role</th>
                    <th>Package (LPA)</th>
                    <th>Min CGPA</th>
                    <th>Arrival Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if (companies != null && !companies.isEmpty()) {
                        for (Company c : companies) { 
                            // Check if student is eligible based on CGPA
                            boolean isEligible = user.getCgpa() >= c.getMinCgpa();
                %>
                <tr>
                    <td><strong><%= c.getCompanyName() %></strong></td>
                    <td><%= c.getJobRole() %></td>
                    <td><%= c.getPackageLpa() %></td>
                    <td><%= c.getMinCgpa() %></td>
                    <td><%= c.getArrivalDate() %></td>
                    <td>
                        <% if (isEligible) { %>
                            <form action="apply" method="post" style="margin: 0;">
                                <input type="hidden" name="companyId" value="<%= c.getCompanyId() %>">
                                <button type="submit" style="padding: 5px 10px; margin: 0; background-color: #28a745;">Apply</button>
                            </form>
                        <% } else { %>
                            <span style="color: red; font-size: 0.9em;">Not Eligible</span>
                        <% } %>
                    </td>
                </tr>
                <% 
                        } 
                    } else { 
                %>
                <tr>
                    <td colspan="6" style="text-align: center;">No placement drives scheduled yet.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <div style="text-align: center; margin-top: 20px;">
            <a href="dashboard.jsp" style="color: #666; text-decoration: none;">← Back to Dashboard</a>
        </div>
    </div>
</body>
</html>