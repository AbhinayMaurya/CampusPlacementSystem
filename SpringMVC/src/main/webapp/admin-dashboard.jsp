<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Security Check: Is it an Admin?
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("admin-login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container" style="width: 600px;">
        <h2 style="color: #c0392b;">Admin Control Panel 🛠️</h2>
        <p>Welcome, Administrator.</p>
        
<div class="dashboard-actions">
            <a href="add-drive.jsp" class="action-card card-add">
                Add Drive
            </a>
            
            <a href="admin-view-drives.jsp" class="action-card card-manage">
                Manage Drives
            </a>
            
            <a href="admin-view-applications.jsp" class="action-card card-apps">
                Manage Applications
            </a>
            
            <a href="view-students.jsp" class="action-card card-students">
                View Students
            </a>
        </div>

        <form action="auth" method="post" style="margin-top: 20px;">
            <input type="hidden" name="action" value="logout">
            <button type="submit" style="background-color: #333;">Logout</button>
        </form>
    </div>
</body>
</html>