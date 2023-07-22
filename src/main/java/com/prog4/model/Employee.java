package com.prog4.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private Sex sex;
    private String registrationNbr;
    private String phoneNbr;
    private String address;
    @Column(columnDefinition = "text")
    private String image = "aucune image";
    private String emailPerso;
    private String emailPro;
    private String beggingDate;
    private String outDate;
    private int nbrChildren;

    /*
    * All foreign keys
    *  */

    @OneToOne
    private SocioPro cateSocioPro;

    @OneToOne
    private NationalCard cin;

    @OneToMany
    private List<Posts> postsList;

    @OneToOne
    private Cnaps nbrCnaps;
}
