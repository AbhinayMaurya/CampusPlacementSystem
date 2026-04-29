<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.campus.model.Company" %>
<%@ page import="com.campus.dao.CompanyDAO" %>

<%
    // Security Check: Only Admins
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("admin-login.html");
        return;
    }

    CompanyDAO companyDAO = new CompanyDAO();
    List<Company> companies = companyDAO.getAllCompanies();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Drives - Admin Portal</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background-color: #007bff; color: white; }
        tr:hover { background-color: #f1f1f1; }
        .container-large { background: white; padding: 2rem; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); width: 800px; margin: 20px auto; }
    </style>
</head>
<body>
    <div class="container-large">
        <h2 style="color: #007bff; text-align: center;">🏢 Manage Placement Drives</h2>

        <% if ("deleted".equals(request.getParameter("status"))) { %>
            <p style="color: green; text-align: center;">Drive deleted successfully!</p>
        <% } else if ("error".equals(request.getParameter("status"))) { %>
            <p style="color: red; text-align: center;">Failed to delete drive.</p>
        <% } %>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Company Name</th>
                    <th>Role</th>
                    <th>Package</th>
                    <th>Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% if (companies != null && !companies.isEmpty()) {
                    for (Company c : companies) { %>
                <tr>
                    <td><%= c.getCompanyId() %></td>
                    <td><strong><%= c.getCompanyName() %></strong></td>
                    <td><%= c.getJobRole() %></td>
                    <td><%= c.getPackageLpa() %> LPA</td>
                    <td><%= c.getArrivalDate() %></td>
                    <td style="display: flex; gap: 5px;">
                        <a href="edit-drive.jsp?id=<%= c.getCompanyId() %>&name=<%= c.getCompanyName() %>&pkg=<%= c.getPackageLpa() %>&date=<%= c.getArrivalDate() %>" 
                           class="button" style="background-color: #ffdf2b; padding: 5px 10px; margin: 0; font-size: 0.9em;">Edit</a>

                        <form action="drive" method="post" style="margin: 0;" onsubmit="return confirm('Are you sure you want to delete this drive?');">
                            <input type="hidden" name="action" value="delete-drive">
                            <input type="hidden" name="companyId" value="<%= c.getCompanyId() %>">
                            <button type="submit" style="background-color: #dc3545; padding: 5px 10px; margin: 0; font-size: 0.9em;">Delete</button>
                        </form>
                    </td>
                </tr>
                <%  } 
                   } else { %>
                <tr>
                    <td colspan="6" style="text-align: center;">No placement drives found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <div style="text-align: center; margin-top: 20px;">
            <a href="admin-dashboard.jsp" style="color: #666; text-decoration: none;">← Back to Dashboard</a>
        </div>
    </div>
</body>
</html>