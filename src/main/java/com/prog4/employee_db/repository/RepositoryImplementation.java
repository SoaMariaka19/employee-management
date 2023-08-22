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
        EmployeeEntity employeeEntity = employeeEntityRepository.findEmployeeEntityByEndToEndId(endToEndId);

        return mapToDto(employeeEntity);
    }

    private ModelEmployee mapToDto(EmployeeEntity employeeEntity) {
        return ModelEmployee.builder()
                .nbrCnaps(employeeEntity.getNbrCnaps())
                .build();
    }
}
