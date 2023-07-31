package com.prog4.controller.model;

import com.prog4.entity.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ModelEmployee {
    private Long id;
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private Sex sex;
    private Long registrationNbr;
    private String phoneNbr;
    private String address;
    private MultipartFile photo;
    public MultipartFile getPhoto() {
        return photo;
    }
    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
    private String emailPerso;
    private String emailPro;
    private LocalDate beggingDate;
    private LocalDate outDate;
    private int nbrChildren;
    private Long socioPro;
    private String socioProCat;
    private String cinNumber;
    private String cinDate;
    private String cinPlace;
    private String post;
    private String nbrCnaps;
    // to update form
    private String formattedBeggingDate;
    private String formattedOutDate;
}
