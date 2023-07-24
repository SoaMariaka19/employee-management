package com.prog4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private Sex sex;
    @Column(unique = true)
    private Long registrationNbr;
    private String phoneNbr;
    private String address;
    @Column(columnDefinition = "text")
    private String image = "aucune image";
    private String emailPerso;
    private String emailPro;
    private String beggingDate;
    private String outDate;
    private int nbrChildren = 0;

    /*
    * All foreign keys
    *  */

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private SocioPro cateSocioPro;

    @OneToOne(cascade = CascadeType.ALL)
    private NationalCard cin;

    @OneToOne(cascade = CascadeType.ALL)
    private Post post;

    @OneToOne(cascade = CascadeType.ALL)
    private Cnaps nbrCnaps;
}
