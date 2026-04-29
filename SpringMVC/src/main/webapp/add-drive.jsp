<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Security Check: Only Admins can access this page
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("admin-login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Drive - Admin Portal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container" style="width: 500px;">
        <h2 style="color: #c0392b;">➕ Add New Placement Drive</h2>
        
        <% if ("success".equals(request.getParameter("status"))) { %>
            <p style="color: green; margin-bottom: 10px;">Drive added successfully!</p>
        <% } else if ("error".equals(request.getParameter("status"))) { %>
            <p style="color: red; margin-bottom: 10px;">Failed to add drive. Please check your inputs.</p>
        <% } %>

        <form action="drive" method="post">
            <div class="form-group">
                <label>Company Name</label>
                <input type="text" name="companyName" required>
            </div>
            <div class="form-group">
                <label>Job Role</label>
                <input type="text" name="jobRole" required>
            </div>
            <div style="display: flex; gap: 10px;">
                <div class="form-group" style="flex: 1;">
                    <label>Package (LPA)</label>
                    <input type="number" step="0.01" name="packageLpa" required>
                </div>
                <div class="form-group" style="flex: 1;">
                    <label>Min CGPA Required</label>
                    <input type="number" step="0.01" name="minCgpa" required>
                </div>
            </div>
            <div class="form-group">
                <label>Arrival Date</label>
                <input type="date" name="arrivalDate" required>
            </div>

            <input type="hidden" name="action" value="add-drive">
            <button type="submit" style="background-color: #c0392b;">Submit Drive Details</button>
        </form>
        
        <a href="admin-dashboard.jsp" style="display: block; text-align: center; margin-top: 15px; color: #666;">← Back to Dashboard</a>
    </div>
</body>
</html>