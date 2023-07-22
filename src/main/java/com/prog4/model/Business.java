package com.prog4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String slogan;
    @Column(unique = true)
    private String address;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private String logo;

    @OneToOne
    private Fiscal idFiscal;
}
