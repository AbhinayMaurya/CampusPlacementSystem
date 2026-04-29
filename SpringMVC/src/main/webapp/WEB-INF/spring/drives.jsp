<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.campus.model.Company" %>
<%
    List<Company> companies = (List<Company>) request.getAttribute("companies");
%>
<!DOCTYPE html>
<html>
<head><title>Spring MVC Drive CRUD</title><link rel="stylesheet" href="../css/style.css"></head>
<body>
<div class="container" style="width: 900px;">
    <h2>Spring MVC Practical: Placement Drive CRUD</h2>
    <form action="drives/add" method="post">
        <input name="companyName" placeholder="Company" required>
        <input name="jobRole" placeholder="Role" required>
        <input name="packageLpa" type="number" step="0.01" placeholder="Package" required>
        <input name="arrivalDate" type="date" required>
        <input name="minCgpa" type="number" step="0.01" placeholder="Min CGPA" required>
        <button type="submit">Add</button>
    </form>
    <table>
        <tr><th>ID</th><th>Company</th><th>Role</th><th>Package</th><th>Date</th><th>Action</th></tr>
        <% if (companies != null) { for (Company c : companies) { %>
            <tr><td><%= c.getCompanyId() %></td><td><%= c.getCompanyName() %></td><td><%= c.getJobRole() %></td><td><%= c.getPackageLpa() %></td><td><%= c.getArrivalDate() %></td><td><form action="drives/delete" method="post"><input type="hidden" name="companyId" value="<%= c.getCompanyId() %>"><button type="submit">Delete</button></form></td></tr>
        <% }} %>
    </table>
</div>
</body>
</html>
