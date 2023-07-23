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
    @Column(columnDefinition = "text")
    private String logo;

    @OneToOne(cascade = CascadeType.ALL)
    private Fiscal idFiscal;
}
