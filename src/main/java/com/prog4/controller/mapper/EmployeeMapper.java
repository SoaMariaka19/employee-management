package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.*;
import com.prog4.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
        SocioPro socioPro = employee.getSocioPro() != null ? socioProService.getByCategory(employee.getSocioProCat()) : null;
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

    public Employee toModel(ModelEmployee employee) throws IOException {
        SocioPro socioPro = employee.getSocioPro() != null ? socioProService.getByCategory(employee.getSocioProCat()) : null;
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
                .nbrCnaps(cnaps)
                .nbrChildren(employee.getNbrChildren())
                .address(employee.getAddress())
                .build();
        service.save(employee1);
        return employee1;
    }
    public ModelEmployee convertToEmployeeForm(Employee employee) {
        ModelEmployee modelEmployee = new ModelEmployee();
        modelEmployee.setId(employee.getId());
        modelEmployee.setLastName(employee.getLastName());
        modelEmployee.setFirstName(employee.getFirstName());
        modelEmployee.setDateOfBirth(employee.getDateOfBirth());
        modelEmployee.setSex(employee.getSex());
        modelEmployee.setRegistrationNbr(employee.getRegistrationNbr());
        modelEmployee.setPhoneNbr(employee.getPhoneNbr());
        modelEmployee.setAddress(employee.getAddress());
        modelEmployee.setEmailPerso(employee.getEmailPerso());
        modelEmployee.setEmailPro(employee.getEmailPro());
        modelEmployee.setBeggingDate(employee.getBeggingDate());
        modelEmployee.setOutDate(employee.getOutDate());
        modelEmployee.setNbrChildren(employee.getNbrChildren());

        if (employee.getCateSocioPro() != null) {
            modelEmployee.setSocioProCat(socioProService.getById(employee.getCateSocioPro().getId()).getCategories());
        }
        if (employee.getCin() != null) {
            modelEmployee.setCinNumber(employee.getCin().getNumber());
            modelEmployee.setCinDate(employee.getCin().getDate());
            modelEmployee.setCinPlace(employee.getCin().getPlace());
        }
        if (employee.getPost() != null) {
            modelEmployee.setPost(employee.getPost().getNameOfPost());
        }
        if (employee.getNbrCnaps() != null) {
            modelEmployee.setNbrCnaps(employee.getNbrCnaps().getNbrCNAPS());
        }

        return modelEmployee;
    }
}