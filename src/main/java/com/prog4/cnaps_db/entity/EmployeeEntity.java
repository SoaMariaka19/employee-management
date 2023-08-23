package com.prog4.cnaps_db.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nbrCnaps;
    @Column(unique = true, nullable = false)
    private Long endToEndId;
}
