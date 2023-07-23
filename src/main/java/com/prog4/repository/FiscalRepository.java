package com.prog4.repository;

import com.prog4.entity.Fiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiscalRepository extends JpaRepository<Fiscal , Long> {
}
