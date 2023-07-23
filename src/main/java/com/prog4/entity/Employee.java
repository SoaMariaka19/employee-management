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
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String lastName;
    @Column(unique = true)
    private String firstName;
    private String dateOfBirth;
    private Sex sex;
    @Column(unique = true)
    private String registrationNbr;
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

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private SocioPro cateSocioPro;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private NationalCard cin;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Post> postsList;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Cnaps nbrCnaps;
}
