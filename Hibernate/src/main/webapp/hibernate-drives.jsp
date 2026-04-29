<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.campus.model.Company" %>
<%@ page import="com.campus.hibernate.CompanyHibernateDAO" %>
<%
    CompanyHibernateDAO dao = new CompanyHibernateDAO();
    String q = request.getParameter("q");
    List<Company> companies = (q == null || q.isBlank()) ? dao.getAllCompanies() : dao.searchCompanies(q);
%>
<!DOCTYPE html>
<html>
<head><title>Hibernate HQL Drives</title><link rel="stylesheet" href="css/style.css"></head>
<body>
<div class="container" style="width: 850px;">
    <h2>Hibernate Practical: HQL Placement Drives</h2>
    <form method="get"><input name="q" placeholder="Search company or role" value="<%= q == null ? "" : q %>"><button type="submit">Search</button></form>
    <table>
        <tr><th>ID</th><th>Company</th><th>Role</th><th>Package</th><th>Date</th><th>Min CGPA</th></tr>
        <% for (Company c : companies) { %>
            <tr><td><%= c.getCompanyId() %></td><td><%= c.getCompanyName() %></td><td><%= c.getJobRole() %></td><td><%= c.getPackageLpa() %></td><td><%= c.getArrivalDate() %></td><td><%= c.getMinCgpa() %></td></tr>
        <% } %>
    </table>
</div>
</body>
</html>
