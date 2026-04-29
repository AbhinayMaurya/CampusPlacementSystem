<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.campus.model.Application" %>
<%@ page import="com.campus.dao.ApplicationDAO" %>
<%@ page import="com.campus.model.Student" %>

<%
    Student user = (Student) session.getAttribute("user");
    if (user == null || !"student".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.html");
        return;
    }

    ApplicationDAO dao = new ApplicationDAO();
    List<Application> myApps = dao.getStudentApplications(user.getStudentId());
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Applications</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background-color: #28a745; color: white; }
        .status-badge { background-color: #ffc107; color: #000; padding: 4px 8px; border-radius: 4px; font-weight: bold; font-size: 0.8em; }
        .container-large { background: white; padding: 2rem; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); width: 700px; margin: 20px auto; }
    </style>
</head>
<body>
    <div class="container-large">
        <h2 style="color: #28a745; text-align: center;">📝 My Applications</h2>
        
        <table>
            <thead>
                <tr>
                    <th>Company Name</th>
                    <th>Job Role</th>
                    <th>Application Status</th>
                </tr>
            </thead>
            <tbody>
                <% if (myApps != null && !myApps.isEmpty()) {
                    for (Application app : myApps) { %>
                <tr>
                    <td><strong><%= app.getCompanyName() %></strong></td>
                    <td><%= app.getJobRole() %></td>
                    <td><span class="status-badge"><%= app.getStatus() %></span></td>
                </tr>
                <%  } 
                   } else { %>
                <tr>
                    <td colspan="3" style="text-align: center;">You haven't applied to any drives yet.</td>
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