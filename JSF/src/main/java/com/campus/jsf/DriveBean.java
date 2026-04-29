package com.campus.jsf;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.campus.dao.CompanyDAO;
import com.campus.model.Company;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("driveBean")
@SessionScoped
public class DriveBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private final CompanyDAO companyDAO = new CompanyDAO();
    private String companyName;
    private String jobRole;
    private double packageLpa;
    private String arrivalDate;
    private double minCgpa;

    public List<Company> getCompanies() {
        return companyDAO.getAllCompanies();
    }

    public String addDrive() {
        companyDAO.addCompany(new Company(companyName, jobRole, packageLpa, Date.valueOf(arrivalDate), minCgpa));
        return "jsf-drives?faces-redirect=true";
    }

    public String deleteDrive(int companyId) {
        companyDAO.deleteCompany(companyId);
        return "jsf-drives?faces-redirect=true";
    }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getJobRole() { return jobRole; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }
    public double getPackageLpa() { return packageLpa; }
    public void setPackageLpa(double packageLpa) { this.packageLpa = packageLpa; }
    public String getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(String arrivalDate) { this.arrivalDate = arrivalDate; }
    public double getMinCgpa() { return minCgpa; }
    public void setMinCgpa(double minCgpa) { this.minCgpa = minCgpa; }
}
