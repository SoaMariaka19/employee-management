package com.prog4.service;

import com.prog4.entity.Employee;
import com.prog4.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    public List<Employee> findAll(){
        return repository.findAll();
    }

    public Employee findById(Long id){
        Optional<Employee> optionalEmployee = repository.findById(id);

        return optionalEmployee.orElseThrow(()->new RuntimeException("no employee with id " +id+ " found"));
    }

    public Employee save(Employee employees){
        repository.save(employees);
        return this.findById(employees.getId());
    }

    public Employee findByRegisterNumber(Long number){
        return repository.findByRegistrationNbr(number);
    }
}
