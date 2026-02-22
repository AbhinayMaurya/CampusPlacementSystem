package com.campus.model;

public class Student {
    private int studentId;
    private String fullName;
    private String email;
    private String password;
    private String branch;
    private double cgpa;
    private int passoutYear;

    public Student() {}

    public Student(String fullName, String email, String password, String branch, double cgpa, int passoutYear) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.branch = branch;
        this.cgpa = cgpa;
        this.passoutYear = passoutYear;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public int getPassoutYear() { return passoutYear; }
    public void setPassoutYear(int passoutYear) { this.passoutYear = passoutYear; }

    @Override
    public String toString() {
        return String.format("| %-3d | %-15s | %-6.2f | %-10s |", studentId, fullName, cgpa, branch);
    }
}