package com.campus.model;

import java.sql.Date;

public class Company {
    private int companyId;
    private String companyName;
    private String jobRole;
    private double packageLpa;
    private Date arrivalDate;
    private double minCgpa;

    public Company() {}

    public Company(String companyName, String jobRole, double packageLpa, Date arrivalDate, double minCgpa) {
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.packageLpa = packageLpa;
        this.arrivalDate = arrivalDate;
        this.minCgpa = minCgpa;
    }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getJobRole() { return jobRole; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }

    public double getPackageLpa() { return packageLpa; }
    public void setPackageLpa(double packageLpa) { this.packageLpa = packageLpa; }

    public Date getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(Date arrivalDate) { this.arrivalDate = arrivalDate; }

    public double getMinCgpa() { return minCgpa; }
    public void setMinCgpa(double minCgpa) { this.minCgpa = minCgpa; }

    @Override
    public String toString() {
        return String.format("| %-3d | %-15s | %-20s | %-6.2f LPA | %-10s |", 
            companyId, companyName, jobRole, packageLpa, arrivalDate);
    }
}