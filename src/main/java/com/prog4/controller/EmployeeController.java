package com.prog4.controller;

import com.prog4.controller.mapper.EmployeeMapper;
import com.prog4.view.RestEmployee;
import com.prog4.model.CustomMultipartFile;
import com.prog4.model.Employee;
import com.prog4.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private EmployeeMapper mapper;
    @GetMapping
    public String getAllEmployees(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "list_employee";
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(Model model){
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(
            @ModelAttribute("employee") RestEmployee restEmployee,
            @RequestParam(value = "image", required = false) MultipartFile photo
    ) throws IOException {
        if (photo != null && !photo.isEmpty()) {
            restEmployee.setPhoto(photo);
        } else {
            restEmployee.setPhoto(new CustomMultipartFile("aucune image"));
        }
        Employee employee = mapper.toEmployee(restEmployee);

        Employee savedEmployee = employeeService.save(employee);
        return "redirect:/employees/" + savedEmployee.getId();
    }


    @GetMapping("/{id}")
    public String showEmployeeDetails(
            @PathVariable("id") Long id, Model model
    ){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "profiles";
    }

}