package com.campus.model;

public class Application {
    private int appId;
    private int studentId;
    private int companyId;
    private String studentName;
    private String companyName;
    private String jobRole;
    private String status;

    public Application() {}

    // Getters and Setters
    public int getAppId() { return appId; }
    public void setAppId(int appId) { this.appId = appId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getJobRole() { return jobRole; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
