package com.prog4.entity;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Cnaps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nbrCNAPS;
}
