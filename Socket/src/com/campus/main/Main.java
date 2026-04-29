package com.campus.main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.campus.dao.AdminDAO;
import com.campus.dao.ApplicationDAO;
import com.campus.dao.CompanyDAO;
import com.campus.dao.StudentDAO;
import com.campus.model.Company;
import com.campus.model.Student;

public class Main {
    
    static Scanner sc = new Scanner(System.in);
    static CompanyDAO companyDAO = new CompanyDAO();
    static StudentDAO studentDAO = new StudentDAO();
    static AdminDAO adminDAO = new AdminDAO();
    static ApplicationDAO appDAO = new ApplicationDAO();

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  CAMPUS PLACEMENT MANAGEMENT SYSTEM  ");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Student Registration (New User)");
            System.out.println("4. Exit");
            System.out.print("Select Option: ");
            
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                
                switch (choice) {
                    case 1:
                        handleAdminLogin();
                        break;
                    case 2:
                        handleStudentLogin();
                        break;
                    case 3:
                        handleStudentRegistration();
                        break;
                    case 4:
                        System.out.println("Exiting System. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                sc.next();
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void handleAdminLogin() {
        System.out.print("\nEnter Admin Username: ");
        String user = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (adminDAO.verifyAdmin(user, pass)) {
            System.out.println("[SUCCESS] Login Successful! Welcome Admin.");
            adminMenu(); 
        } else {
            System.out.println("[FAILED] Invalid Credentials.");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n--- ADMIN DASHBOARD ---");
            System.out.println("1. Add New Drive");
            System.out.println("2. View All Drives");
            System.out.println("3. Update Drive Details");
            System.out.println("4. Delete Drive");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1: // CREATE
                    System.out.print("Company Name: "); String name = sc.next();
                    System.out.print("Role: "); String role = sc.next();
                    System.out.print("Package: "); double pkg = sc.nextDouble();
                    System.out.print("Date (YYYY-MM-DD): "); String date = sc.next();
                    System.out.print("Min CGPA: "); double cgpa = sc.nextDouble();
                    
                    Company c = new Company(name, role, pkg, Date.valueOf(date), cgpa);
                    if(companyDAO.addCompany(c)) System.out.println("[SUCCESS] Drive Added.");
                    else System.out.println("[FAILED] Could not add drive.");
                    break;

                case 2: // READ
                    List<Company> list = companyDAO.getAllCompanies();
                    System.out.println("\nList of Companies:");
                    System.out.println("----------------------------------------------------------");
                    for(Company comp : list) System.out.println(comp);
                    break;

                case 3: // UPDATE
                    System.out.print("Enter Company ID to Update: ");
                    int updateId = sc.nextInt();
                    
                    System.out.print("Enter New Package (LPA): ");
                    double newPkg = sc.nextDouble();
                    System.out.print("Enter New Date (YYYY-MM-DD): ");
                    String newDate = sc.next();
                    
                    if(companyDAO.updateCompany(updateId, newPkg, Date.valueOf(newDate))) 
                        System.out.println("[SUCCESS] Drive Updated!");
                    else 
                        System.out.println("[FAILED] Company ID not found.");
                    break;

                case 4: // DELETE
                    System.out.print("Enter Company ID to delete: ");
                    int deleteId = sc.nextInt();
                    if(companyDAO.deleteCompany(deleteId)) System.out.println("[SUCCESS] Deleted.");
                    else System.out.println("[FAILED] ID Not Found.");
                    break;

                case 5: // LOGOUT
                    return; 

                default:
                    System.out.println("Invalid Option.");
            }
        }
    }

    private static void handleStudentRegistration() {
        System.out.println("\n--- NEW STUDENT REGISTRATION ---");
        System.out.print("Full Name (Use_Underscores): "); String name = sc.next();
        System.out.print("Email: "); String email = sc.next();
        System.out.print("Password: "); String pass = sc.next();
        System.out.print("Branch: "); String branch = sc.next();
        System.out.print("CGPA: "); double cgpa = sc.nextDouble();
        System.out.print("Passout Year: "); int year = sc.nextInt();

        Student s = new Student(name, email, pass, branch, cgpa, year);
        if (studentDAO.addStudent(s)) {
            System.out.println("[SUCCESS] Registration Successful! Please Login.");
        } else {
            System.out.println("[FAILED] Registration Failed (Email might exist).");
        }
    }

    private static void handleStudentLogin() {
        System.out.print("\nEnter Student Email: ");
        String email = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        Student loggedInStudent = studentDAO.loginStudent(email, pass);

        if (loggedInStudent != null) {
            System.out.println("[SUCCESS] Welcome, " + loggedInStudent.getFullName());
            studentMenu(loggedInStudent); 
        } else {
            System.out.println("[FAILED] Login Failed.");
        }
    }

    private static void studentMenu(Student student) {
        while (true) {
            System.out.println("\n--- STUDENT DASHBOARD (" + student.getFullName() + ") ---");
            System.out.println("1. View Eligible Drives");
            System.out.println("2. Apply for Drive");
            System.out.println("3. View My Applications");
            System.out.println("4. My Profile");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    List<Company> list = companyDAO.getAllCompanies();
                    System.out.println("\nAvailable Drives:");
                    for(Company comp : list) {
                        String eligibility = (student.getCgpa() >= comp.getMinCgpa()) ? "[ELIGIBLE]" : "[LOW CGPA]";
                        System.out.println(comp + " " + eligibility);
                    }
                    break;

                case 2: 
                    System.out.print("Enter Company ID to Apply: ");
                    int companyId = sc.nextInt();
                    
                    if(appDAO.applyForJob(student.getStudentId(), companyId)) {
                        System.out.println("[SUCCESS] Application Submitted!");
                    } else {
                        System.out.println("[FAILED] Application Failed (or Duplicate).");
                    }
                    break;

                case 3:
                    appDAO.getMyApplications(student.getStudentId());
                    break;

                case 4:
                    System.out.println("\nYour Profile:");
                    System.out.println(student);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Option.");
            }
        }
    }
}