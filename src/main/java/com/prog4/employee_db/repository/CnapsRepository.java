package com.prog4.repository;

import com.prog4.entity.Cnaps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CnapsRepository extends JpaRepository<Cnaps,Long> {

    @Query("SELECT u FROM Cnaps u WHERE u.nbrCNAPS LIKE :number")
    Cnaps findByNbrCNAPS(@Param("number") String number);
}
