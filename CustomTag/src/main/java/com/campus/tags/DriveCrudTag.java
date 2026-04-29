package com.campus.tags;

import java.io.IOException;
import java.util.List;

import com.campus.dao.CompanyDAO;
import com.campus.model.Company;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class DriveCrudTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        List<Company> companies = new CompanyDAO().getAllCompanies();
        try {
            JspWriter out = pageContext.getOut();
            out.println("<h3>Add Placement Drive</h3>");
            out.println("<form action='drive' method='post'>");
            out.println("<input type='hidden' name='action' value='add-drive'>");
            out.println("<input name='companyName' placeholder='Company name' required>");
            out.println("<input name='jobRole' placeholder='Job role' required>");
            out.println("<input name='packageLpa' type='number' step='0.01' placeholder='Package LPA' required>");
            out.println("<input name='arrivalDate' type='date' required>");
            out.println("<input name='minCgpa' type='number' step='0.01' placeholder='Min CGPA' required>");
            out.println("<button type='submit'>Add</button></form>");
            out.println("<h3>View / Modify / Delete Drives</h3>");
            out.println("<table><tr><th>ID</th><th>Company</th><th>Role</th><th>Package</th><th>Date</th><th>Action</th></tr>");
            for (Company c : companies) {
                out.println("<tr><td>" + c.getCompanyId() + "</td><td>" + escape(c.getCompanyName()) + "</td><td>"
                        + escape(c.getJobRole()) + "</td><td>" + c.getPackageLpa() + " LPA</td><td>"
                        + c.getArrivalDate() + "</td><td>");
                out.println("<a href='edit-drive.jsp?id=" + c.getCompanyId() + "&name=" + escape(c.getCompanyName())
                        + "&pkg=" + c.getPackageLpa() + "&date=" + c.getArrivalDate() + "'>Edit</a>");
                out.println("<form action='drive' method='post' style='display:inline'>");
                out.println("<input type='hidden' name='action' value='delete-drive'>");
                out.println("<input type='hidden' name='companyId' value='" + c.getCompanyId() + "'>");
                out.println("<button type='submit'>Delete</button></form></td></tr>");
            }
            out.println("</table>");
        } catch (IOException e) {
            throw new JspException("Unable to render drive CRUD tag", e);
        }
        return SKIP_BODY;
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
