package com.prog4.controller.mapper;

import com.prog4.model.*;
import com.prog4.service.CnapsService;
import com.prog4.service.NationalCardService;
import com.prog4.service.PostsService;
import com.prog4.service.SocioProService;
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

}