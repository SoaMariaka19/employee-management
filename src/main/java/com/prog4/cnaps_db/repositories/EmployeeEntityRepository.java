package com.prog4.cnaps_db.repositories;

import com.prog4.cnaps_db.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long> {
}
