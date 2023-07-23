package com.prog4.controller;

import com.prog4.controller.mapper.EmployeeMapper;
import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.Post;
import com.prog4.entity.Sex;
import com.prog4.entity.SocioPro;
import com.prog4.repository.EmployeeRepository;
import com.prog4.service.PostsService;
import com.prog4.service.SocioProService;
import com.prog4.service.validator.AlphanumericValidator;
import com.prog4.view.RestEmployee;
import com.prog4.entity.Employee;
import com.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PostsService postsService;
    private final EmployeeRepository repository;
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
    @GetMapping("/filter")
    public String filterAndSortEmployees(
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "sex", required = false) Sex sex,
            @RequestParam(name = "postName", required = false) String postName,
            @RequestParam(name = "minHireDate", required = false) String minHireDate,
            @RequestParam(name = "maxHireDate", required = false) String maxHireDate,
            @RequestParam(name = "minLeaveDate", required = false) String minLeaveDate,
            @RequestParam(name = "maxLeaveDate", required = false) String maxLeaveDate,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "sortOrder", required = false) String sortOrder,
            Model model
    ) {
        Specification<Employee> spec = employeeService.buildEmployeeSpecification(
                lastName, firstName, sex, postName, minHireDate, maxHireDate, minLeaveDate, maxLeaveDate);

        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isEmpty()) {
            sort = Sort.by(sortOrder.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        }

        List<Employee> filteredAndSortedEmployees = repository.findAll(spec, sort);
        model.addAttribute("employees", filteredAndSortedEmployees);

        return "employee/list_employee";
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

    @GetMapping("/{id}/update")
    public String showUpdateEmployeeForm(@PathVariable("id") Long employeeId, Model model) {
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("newEmployee", employee);
        return "employee/update-employee";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("newEmployee") Employee modelEmployee) throws IOException {
        SocioPro socioPro = socioProService.getByCategory(modelEmployee.getCateSocioPro().getCategories());
        socioPro.setCategories(modelEmployee.getCateSocioPro().getCategories());
        Employee employee = mapper.toUpdate(socioPro , modelEmployee);
        return "redirect:/employees/" + employee.getId();
    }

    @GetMapping("/export-csv")
    public void exportCsv(HttpServletResponse response) throws IOException {
        List<Employee> employees = employeeService.findAll();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("First Name,Last Name,Date of Birth,Registration Number");
            for (Employee employee : employees) {
                writer.println(
                        employee.getFirstName() + "," +
                                employee.getLastName() + "," +
                                employee.getDateOfBirth() + "," +
                                employee.getSex() + "," +
                                employee.getRegistrationNbr() + "," +
                                employee.getPhoneNbr() + "," +
                                employee.getAddress() + "," +
                                employee.getEmailPerso() + "," +
                                employee.getEmailPro() + "," +
                                employee.getBeggingDate() + "," +
                                employee.getOutDate() + "," +
                                employee.getNbrChildren() + "," +
                                employee.getCateSocioPro().getCategories() + "," +
                                employee.getCin().getNumber() + "," +
                                employee.getCin().getDate() + "," +
                                employee.getCin().getPlace() + "," +
                                employee.getNbrCnaps().getNbrCNAPS()
                );
            }
        }
    }

}