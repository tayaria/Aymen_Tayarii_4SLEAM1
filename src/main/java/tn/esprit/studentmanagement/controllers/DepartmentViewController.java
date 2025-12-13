package tn.esprit.studentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentViewController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/list")
    public String listDepartments(Model model) {
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return "department-list"; // Nom de la page HTML (department-list.html)
    }
}

