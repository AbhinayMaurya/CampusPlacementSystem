package com.campus.practical7.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Company {
    private int companyId;
    private String companyName;
    private String jobRole;
    private BigDecimal packageLpa;   // 🔥 changed
    private Date arrivalDate;
    private BigDecimal minCgpa;      // 🔥 changed

    public Company() {}

    public Company(String companyName, String jobRole,
                   BigDecimal packageLpa, Date arrivalDate, BigDecimal minCgpa) {
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

    public BigDecimal getPackageLpa() { return packageLpa; }
    public void setPackageLpa(BigDecimal packageLpa) { this.packageLpa = packageLpa; }

    public Date getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(Date arrivalDate) { this.arrivalDate = arrivalDate; }

    public BigDecimal getMinCgpa() { return minCgpa; }
    public void setMinCgpa(BigDecimal minCgpa) { this.minCgpa = minCgpa; }

    @Override
    public String toString() {
        return String.format("%-3d %-20s %-20s %-6.2f %-10s %.2f",
                companyId,
                companyName,
                jobRole,
                packageLpa.doubleValue(),   // convert for printing
                arrivalDate,
                minCgpa.doubleValue());
    }
}