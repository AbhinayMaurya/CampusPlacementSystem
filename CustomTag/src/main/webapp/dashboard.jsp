<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.campus.model.Student" %>

<%
    // 1. Security Check: Prevent direct access without login
    Student user = (Student) session.getAttribute("user");
    if (user == null || !"student".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.html");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Campus Placement</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="container" style="width: 650px; text-align: left;">
        <div style="text-align: center; margin-bottom: 20px;">
            <h2>Welcome, <%= user.getFullName() %>! 🎓</h2>
            <p style="color: #666;">Student Dashboard</p>
        </div>

        <div style="background: #f8f9fa; padding: 15px; border-radius: 5px; margin-bottom: 20px;">
            <p><strong>Student ID:</strong> <%= user.getStudentId() %></p>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>Branch:</strong> <%= user.getBranch() %></p>
            <p><strong>Current CGPA:</strong> <%= user.getCgpa() %></p>
            <p><strong>Passout Year:</strong> <%= user.getPassoutYear() %></p>
        </div>

        <h3 style="margin-bottom: 10px;">Quick Actions</h3>
        <div style="display: flex; gap: 10px;">
            <a href="view-jobs.jsp" class="button" style="text-align: center; background-color: #007bff; color: white; padding: 10px; border-radius: 5px; flex: 1; text-decoration: none;">
                View Drives
            </a>
            
            <a href="my-applications.jsp" class="button" style="text-align: center; background-color: #17a2b8; color: white; padding: 10px; border-radius: 5px; flex: 1; text-decoration: none;">
                My Applications
            </a>

            <a href="profile.jsp" class="button" style="text-align: center; background-color: #28a745; color: white; padding: 10px; border-radius: 5px; flex: 1; text-decoration: none;">
                Edit Profile
            </a>
        </div>

        <form action="auth" method="post" style="margin-top: 20px;">
            <input type="hidden" name="action" value="logout">
            <button type="submit" style="background-color: #dc3545;">Logout</button>
        </form>
    </div>

</body>
</html>