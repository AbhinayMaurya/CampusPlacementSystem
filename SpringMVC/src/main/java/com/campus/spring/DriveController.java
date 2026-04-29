package com.campus.spring;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.campus.dao.CompanyDAO;
import com.campus.model.Company;

@Controller
@RequestMapping("/drives")
public class DriveController {
    private final CompanyDAO companyDAO = new CompanyDAO();

    @GetMapping
    public String listDrives(Model model) {
        model.addAttribute("companies", companyDAO.getAllCompanies());
        return "drives";
    }

    @PostMapping("/add")
    public String addDrive(@RequestParam String companyName,
                           @RequestParam String jobRole,
                           @RequestParam double packageLpa,
                           @RequestParam String arrivalDate,
                           @RequestParam double minCgpa) {
        companyDAO.addCompany(new Company(companyName, jobRole, packageLpa, Date.valueOf(arrivalDate), minCgpa));
        return "redirect:/spring/drives";
    }

    @PostMapping("/delete")
    public String deleteDrive(@RequestParam int companyId) {
        companyDAO.deleteCompany(companyId);
        return "redirect:/spring/drives";
    }
}
