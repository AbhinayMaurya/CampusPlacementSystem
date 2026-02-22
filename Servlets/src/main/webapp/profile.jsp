<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.campus.model.Student" %>

<%
    // Security Check: Only Students
    Student user = (Student) session.getAttribute("user");
    if (user == null || !"student".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.html");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Profile - Campus Placement</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container" style="width: 450px;">
        <h2 style="color: #28a745;">👤 Edit Profile</h2>
        
        <% if ("success".equals(request.getParameter("status"))) { %>
            <p style="color: green; margin-bottom: 15px;">Profile updated successfully!</p>
        <% } else if ("error".equals(request.getParameter("status"))) { %>
            <p style="color: red; margin-bottom: 15px;">Failed to update profile.</p>
        <% } %>

        <div style="background: #f8f9fa; padding: 15px; border-radius: 5px; margin-bottom: 20px; text-align: left;">
            <p><strong>Name:</strong> <%= user.getFullName() %></p>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>Branch:</strong> <%= user.getBranch() %></p>
        </div>

        <form action="profile" method="post" style="text-align: left;">
            <div class="form-group">
                <label><strong>Update CGPA</strong></label>
                <input type="number" step="0.01" name="newCgpa" value="<%= user.getCgpa() %>" required>
            </div>

            <input type="hidden" name="action" value="update-cgpa">
            <button type="submit" style="background-color: #28a745;">Save Changes</button>
        </form>
        
        <div style="text-align: center; margin-top: 15px;">
            <a href="dashboard.jsp" style="color: #666; text-decoration: none;">← Back to Dashboard</a>
        </div>
    </div>
</body>
</html>