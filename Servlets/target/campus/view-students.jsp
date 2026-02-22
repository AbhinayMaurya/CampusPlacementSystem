<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.campus.model.Student" %>
<%@ page import="com.campus.dao.StudentDAO" %>

<%
    // Security Check: Only Admins can view this
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("admin-login.html");
        return;
    }

    // Fetch the students from the database
    StudentDAO dao = new StudentDAO();
    List<Student> students = dao.getAllStudents();
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Students - Admin Portal</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background-color: #17a2b8; color: white; }
        tr:hover { background-color: #f1f1f1; }
        .container-large { background: white; padding: 2rem; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); width: 800px; margin: 20px auto; }
    </style>
</head>
<body>
    <div class="container-large">
        <h2 style="color: #17a2b8; text-align: center;">👥 Registered Students</h2>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Branch</th>
                    <th>CGPA</th>
                    <th>Passout Year</th>
                </tr>
            </thead>
            <tbody>
                <% if (students != null && !students.isEmpty()) {
                    for (Student s : students) { %>
                <tr>
                    <td><%= s.getStudentId() %></td>
                    <td><strong><%= s.getFullName() %></strong></td>
                    <td><%= s.getEmail() %></td>
                    <td><%= s.getBranch() %></td>
                    <td><strong><%= s.getCgpa() %></strong></td>
                    <td><%= s.getPassoutYear() %></td>
                </tr>
                <%  } 
                   } else { %>
                <tr>
                    <td colspan="6" style="text-align: center;">No students registered yet.</td>
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