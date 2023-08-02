package com.prog4.repository;

import com.prog4.entity.SocioPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocioProRepository extends JpaRepository<SocioPro,Long> {
    @Query("SELECT u FROM SocioPro u WHERE u.categories = :categories")
    SocioPro findSocioProByCategoriesIgnoreCase(@Param("categories")String categories);
}
