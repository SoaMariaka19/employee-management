package com.prog4.employee_db.service.facade;

import com.prog4.cnaps_db.entity.EmployeeEntity;
import com.prog4.cnaps_db.repositories.EmployeeEntityRepository;
import com.prog4.employee_db.controller.model.ModelEmployee;
import com.prog4.employee_db.entity.Employee;
import com.prog4.employee_db.repository.EmployeeRepository;
import com.prog4.employee_db.repository.RepositoryBaseEmployee;
import com.prog4.employee_db.repository.RepositoryImplementation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FacadeEmployeeService implements RepositoryBaseEmployee {
    private EmployeeRepository employeeRepository;
    private RepositoryImplementation repositoryImplementation;
    private EmployeeEntityRepository employeeEntityRepository;
    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id) ;
    }

    @Override
    public Optional<EmployeeEntity> findByEndToEndId(Long id) {
        return employeeEntityRepository.findEmployeeEntityByEndToEndId(id);
    }

    private ModelEmployee combineToModel(Employee employee, EmployeeEntity employeeEntity){
        return repositoryImplementation.mapToDto(employee, employeeEntity);
    }

    public ModelEmployee getModelEmployeeResult(Long id) {
        EmployeeEntity employeeEntity = findEmployeeEntityByEndToEndId(id);
        Employee employee = findEmployeeById(id);
        return combineToModel(employee, employeeEntity);
    }

    private EmployeeEntity findEmployeeEntityByEndToEndId(Long id) {
        return findByEndToEndId(id).orElse(new EmployeeEntity());
    }

    private Employee findEmployeeById(Long id) {
        return findById(id).orElse(new Employee());
    }
}
