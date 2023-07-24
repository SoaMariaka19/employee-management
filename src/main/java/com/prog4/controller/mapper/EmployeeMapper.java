package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.*;
import com.prog4.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component
@AllArgsConstructor
public class EmployeeMapper {

    private PostsService postsService;
    private NationalCardService nationalCardService;
    private CnapsService cnapsService;
    private EmployeeService service;
    private SocioProService socioProService;
    public Employee toEntity(ModelEmployee employee) throws IOException {
        SocioPro socioPro = employee.getSocioPro() != null ? socioProService.getById(employee.getSocioPro()) : null;
        NationalCard nationalCard = nationalCardService.getByNumber(employee.getCinNumber());
        nationalCard.setDate(employee.getCinDate());
        nationalCard.setPlace(employee.getCinPlace());
        nationalCard.setNumber(employee.getCinNumber());
        Cnaps cnaps = cnapsService.getByNumber(employee.getNbrCnaps());

        String photo = Base64.getEncoder().encodeToString(employee.getPhoto().getBytes());
        if (employee.getPhoto() != null && !photo.isEmpty()) {
            employee.setPhoto(employee.getPhoto());
        } else {
            employee.setPhoto(new CustomMultipartFile("aucune image"));
        }
        Post post = postsService.getByName(employee.getPost());
        Employee employee1 = Employee.builder()
                .cin(nationalCard)
                .id(employee.getId())
                .dateOfBirth(employee.getDateOfBirth())
                .beggingDate(employee.getBeggingDate())
                .image(photo)
                .cateSocioPro(socioPro)
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .emailPerso(employee.getEmailPerso())
                .emailPro(employee.getEmailPro())
                .phoneNbr(employee.getPhoneNbr())
                .outDate(employee.getOutDate())
                .sex(employee.getSex())
                .post(post)
                .registrationNbr(employee.getRegistrationNbr())
                .nbrCnaps(cnaps)
                .nbrChildren(employee.getNbrChildren())
                .address(employee.getAddress())
                .build();
        service.save(employee1);
        return employee1;
    }
    public Employee toUpdate(SocioPro socioPro , Employee employee) throws IOException {
        Employee employee1 = service.findByRegisterNumber(employee.getRegistrationNbr());
        employee1.setSex(employee.getSex());
        employee1.setPhoneNbr(employee.getPhoneNbr());
        employee1.setPost(employee.getPost());
        employee1.setOutDate(employee.getOutDate());
        employee1.setNbrChildren(employee.getNbrChildren());
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setImage(employee.getImage());
        employee1.setCin(employee.getCin());
        employee1.setAddress(employee.getAddress());
        employee1.setEmailPro(employee.getEmailPro());
        employee1.setEmailPerso(employee.getEmailPerso());
        employee1.setDateOfBirth(employee.getDateOfBirth());
        employee1.setRegistrationNbr(employee.getRegistrationNbr());
        employee1.setCateSocioPro(socioPro);
        employee1.setNbrCnaps(employee.getNbrCnaps());
        service.save(employee1);
        return employee1;
    }
}