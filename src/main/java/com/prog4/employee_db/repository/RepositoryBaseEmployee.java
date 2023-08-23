package com.prog4.employee_db.repository;

import com.prog4.cnaps_db.entity.EmployeeEntity;
import com.prog4.employee_db.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryBaseEmployee {
    Optional<Employee> findById(Long id);

    Optional<EmployeeEntity> findByEndToEndId(Long id);
}
