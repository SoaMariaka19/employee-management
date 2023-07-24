package com.prog4.controller.model;

import com.prog4.entity.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private String beggingDate;
    private String outDate;
    private int nbrChildren;
    private Long socioPro;
    private String cinNumber;
    private String cinDate;
    private String cinPlace;
    private String post;
    private String nbrCnaps;
}
