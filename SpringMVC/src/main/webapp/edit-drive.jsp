<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Security Check
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("admin-login.html");
        return;
    }

    // Get the data passed from the previous page's URL
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String pkg = request.getParameter("pkg");
    String date = request.getParameter("date");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Drive - Admin Portal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container" style="width: 450px;">
        <h2 style="color: #ffc107;">✏️ Edit Drive: <%= name %></h2>
        
        <form action="drive" method="post">
            <input type="hidden" name="companyId" value="<%= id %>">
            
            <div class="form-group">
                <label>Company Name</label>
                <input type="text" value="<%= name %>" disabled>
                <small style="color: gray;">(Name cannot be changed)</small>
            </div>

            <div class="form-group">
                <label>Update Package (LPA)</label>
                <input type="number" step="0.01" name="newPackage" value="<%= pkg %>" required>
            </div>

            <div class="form-group">
                <label>Update Arrival Date</label>
                <input type="date" name="newDate" value="<%= date %>" required>
            </div>

            <input type="hidden" name="action" value="update-drive">
            <button type="submit" style="background-color: #ffc107; color: black; font-weight: bold;">Save Changes</button>
        </form>
        
        <div style="text-align: center; margin-top: 15px;">
            <a href="admin-view-drives.jsp" style="color: #666; text-decoration: none;">Cancel</a>
        </div>
    </div>
</body>
</html>