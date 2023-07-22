package com.prog4.repository;

import com.prog4.model.Cnaps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnapsRepository extends JpaRepository<Cnaps,Long> {
}
