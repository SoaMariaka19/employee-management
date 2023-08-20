package com.prog4.employee_db.service;

import com.prog4.cnaps_db.entity.EmployeeEntity;
import com.prog4.cnaps_db.repositories.EmployeeEntityRepository;
import com.prog4.employee_db.controller.model.ModelEmployee;
import com.prog4.employee_db.entity.Employee;
import com.prog4.employee_db.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeDtoFacade {
    private EmployeeRepository employeeRepository; // Repository for employee_db

    private EmployeeEntityRepository employeeEntityRepository; // Repository for cnaps_db

    public ModelEmployee getEmployeeDtoByEndToEndId(String endToEndId) {
        Employee employee = employeeRepository.findByEnd_to_end_id(endToEndId);
        EmployeeEntity employeeEntity = employeeEntityRepository.findByEnd_to_end_id(endToEndId);

        return mapToDto(employee, employeeEntity);
    }

    private ModelEmployee mapToDto(Employee employee, EmployeeEntity employeeEntity) {
        ModelEmployee dto = new ModelEmployee();
        dto.setId(employee.getId());
        dto.setLastName(employee.getLastName());
        dto.setFirstName(employee.getFirstName());
        dto.setEnd_to_end_id(employee.getEnd_to_end_id());
        return dto;
    }
}
