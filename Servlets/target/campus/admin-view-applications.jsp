<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.campus.model.Application" %>
<%@ page import="com.campus.dao.ApplicationDAO" %>

<%
    // Security Check: Only Admins allowed here
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("admin-login.html");
        return;
    }

    // Fetch all applications
    ApplicationDAO dao = new ApplicationDAO();
    List<Application> applicationsList = dao.getAllApplications();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Applications - Admin Portal</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background-color: #6f42c1; color: white; } /* Purple theme */
        tr:hover { background-color: #f1f1f1; }
        .container-large { background: white; padding: 2rem; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); width: 900px; margin: 20px auto; }
        select { padding: 5px; border-radius: 4px; border: 1px solid #ccc; }
    </style>
</head>
<body>
    <div class="container-large">
        <h2 style="color: #6f42c1; text-align: center;">📋 Manage Student Applications</h2>

        <% if ("updated".equals(request.getParameter("status"))) { %>
            <p style="color: green; text-align: center;">Application status updated successfully!</p>
        <% } else if ("error".equals(request.getParameter("status"))) { %>
            <p style="color: red; text-align: center;">Failed to update application status.</p>
        <% } %>

        <table>
            <thead>
                <tr>
                    <th>Student Name</th>
                    <th>Company</th>
                    <th>Role</th>
                    <th>Current Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% if (applicationsList != null && !applicationsList.isEmpty()) {
                    for (Application app : applicationsList) { %>
                <tr>
                    <td><strong><%= app.getStudentName() %></strong> <br><small>(ID: <%= app.getStudentId() %>)</small></td>
                    <td><%= app.getCompanyName() %></td>
                    <td><%= app.getJobRole() %></td>
                    <td>
                        <span style="font-weight: bold; color: <%= app.getStatus().equals("Selected") ? "green" : (app.getStatus().equals("Rejected") ? "red" : "#d35400") %>;">
                            <%= app.getStatus() %>
                        </span>
                    </td>
                    <td>
                        <form action="apply" method="post" style="margin: 0; display: flex; gap: 5px;">
                            <input type="hidden" name="action" value="update-status">
                            <input type="hidden" name="studentId" value="<%= app.getStudentId() %>">
                            <input type="hidden" name="companyId" value="<%= app.getCompanyId() %>">
                            
                            <select name="status">
                                <option value="Applied" <%= app.getStatus().equals("Applied") ? "selected" : "" %>>Applied</option>
                                <option value="Selected" <%= app.getStatus().equals("Selected") ? "selected" : "" %>>Selected</option>
                                <option value="Rejected" <%= app.getStatus().equals("Rejected") ? "selected" : "" %>>Rejected</option>
                            </select>
                            
                            <button type="submit" style="background-color: #6f42c1; padding: 5px 10px; margin: 0; font-size: 0.9em;">Update</button>
                        </form>
                    </td>
                </tr>
                <%  } 
                   } else { %>
                <tr>
                    <td colspan="5" style="text-align: center;">No applications found.</td>
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