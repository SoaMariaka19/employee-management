package com.prog4.controller;

import com.prog4.controller.mapper.EmployeeMapper;
import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.Post;
import com.prog4.entity.Sex;
import com.prog4.entity.SocioPro;
import com.prog4.service.PostsService;
import com.prog4.service.SocioProService;
import com.prog4.service.validator.AlphanumericValidator;
import com.prog4.view.RestEmployee;
import com.prog4.entity.Employee;
import com.prog4.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final PostsService postsService;
    private final SocioProService socioProService;
    private EmployeeMapper mapper;
    private final AlphanumericValidator validator;
    @GetMapping
    public String getAllEmployees(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "employee/list_employee";
    }

    @ModelAttribute("sexOptions")
    public Sex[] getSexOptions() {
        return Sex.values();
    }

    @ModelAttribute("suggestedSocioProOptions")
    public List<SocioPro> getSuggestedSocioProOptions() {
        return socioProService.findAll();
    }

    @ModelAttribute("suggestedPostProOptions")
    public List<Post> getSuggestedPostProOptions() {
        return postsService.findAll();
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(Model model){
        model.addAttribute("employee", new ModelEmployee());
        return "employee/add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(
            @ModelAttribute("employee") ModelEmployee modelEmployee,
            Model modelError
    ) throws IOException {
       try{
           if(validator.checkIfAlphanumeric(modelEmployee.getCinNumber())){
               Employee employee = mapper.toEntity(modelEmployee);
               return "redirect:/employees/" + employee.getId();
           }
            modelError.addAttribute("cnapsError","cnaps number must be alphanumeric only [a-zA-Z0-9]");
            return "employee/add-employee";
       }
       catch (DataIntegrityViolationException ex) {
           modelError.addAttribute("errorMessage", "Registration number must be unique.");
           return "employee/add-employee";
       }
    }


    @GetMapping("/{id}")
    public String showEmployeeDetails(
            @PathVariable("id") Long id, Model model
    ){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employee/profiles";
    }

}