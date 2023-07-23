package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.*;
import com.prog4.service.*;
import com.prog4.view.RestEmployee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeMapper {

    private PostsService postsService;
    private NationalCardService nationalCardService;
    private CnapsService cnapsService;
    private EmployeeService service;
    private SocioProService socioProService;

    public Employee toEmployee(RestEmployee restEmployee) throws IOException {
        Employee employee = new Employee();
        List<Post> postsList = new ArrayList<>();
        NationalCard nationalCard = nationalCardService.findById(restEmployee.getCin().getId());
        SocioPro socioPro = socioProService.getById(restEmployee.getSocioPro().getId());
        Cnaps cnaps = cnapsService.getById(restEmployee.getNbrCnaps().getId());

        String photo = Base64.getEncoder().encodeToString(restEmployee.getPhoto().getBytes());
        if (restEmployee.getPhoto() != null && !photo.isEmpty()) {
            restEmployee.setPhoto(restEmployee.getPhoto());
        } else {
            restEmployee.setPhoto(new CustomMultipartFile("aucune image"));
        }

        for (Post post : restEmployee.getPostsList()){
            Post tmp = postsService.getById(post.getId());
            postsList.add(tmp);
        }

        employee.setId(restEmployee.getId());
        employee.setFirstName((restEmployee.getFirstName()));
        employee.setLastName(restEmployee.getLastName());
        employee.setDateOfBirth(restEmployee.getDateOfBirth());
        employee.setRegistrationNbr(restEmployee.getRegistrationNbr());
        employee.setImage(photo);
        employee.setAddress(restEmployee.getAddress());
        employee.setSex(restEmployee.getSex());
        employee.setBeggingDate(restEmployee.getBeggingDate());
        employee.setEmailPerso(restEmployee.getEmailPerso());
        employee.setEmailPro(restEmployee.getEmailPro());
        employee.setNbrChildren(restEmployee.getNbrChildren());

        employee.setCateSocioPro(socioPro);
        employee.setNbrCnaps(cnaps);
        employee.setCin(nationalCard);
        employee.setOutDate(restEmployee.getOutDate());
        employee.setPhoneNbr(restEmployee.getPhoneNbr());
        employee.setPostsList(postsList);

        return employee;
    }

    public Employee toEntity(ModelEmployee employee) throws IOException {
        Cnaps cnaps = cnapsService.getByNumber(employee.getNbrCnaps());

        SocioPro socioPro = employee.getSocioPro() != null ? socioProService.getById(employee.getSocioPro()) : null;
        socioProService.saveSocioPro(socioPro);
        NationalCard nationalCard = nationalCardService.getByNumber(employee.getCinNumber());
        nationalCard.setDate(employee.getCinDate());
        nationalCard.setPlace(employee.getCinPlace());
        nationalCard.setNumber(employee.getCinNumber());

        String photo = Base64.getEncoder().encodeToString(employee.getPhoto().getBytes());
        if (employee.getPhoto() != null && !photo.isEmpty()) {
            employee.setPhoto(employee.getPhoto());
        } else {
            employee.setPhoto(new CustomMultipartFile("aucune image"));
        }
        List<Post> postList = new ArrayList<>();
        for (String post : employee.getPostsList()){
            Post tmp = postsService.getByName(post);
            postList.add(tmp);
        }
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
                .postsList(postList)
                .registrationNbr(employee.getRegistrationNbr())
                .nbrCnaps(cnaps)
                .nbrChildren(employee.getNbrChildren())
                .address(employee.getAddress())
                .build();
        service.save(employee1);
        return employee1;
    }
}