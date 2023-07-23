package com.prog4.repository;

import com.prog4.entity.Cnaps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CnapsRepository extends JpaRepository<Cnaps,Long> {
    Optional<Cnaps> findByNbrCNAPS(String number);
}
