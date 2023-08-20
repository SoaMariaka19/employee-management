package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.*;
import com.prog4.repository.PhoneNumberRepository;
import com.prog4.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class EmployeeMapper {

    private NationalCardService nationalCardService;
    private CnapsService cnapsService;
    private final PhoneNumberRepository phoneNumberRepository;
    private SocioProService socioProService;
    public Employee toEntity(ModelEmployee employee) throws IOException {
        SocioPro socioPro = socioProService.getByCategory(employee.getSocioProCat());
        socioPro.setCategories(employee.getSocioProCat());

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (String number : employee.getPhoneNbr()) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setNumber(employee.getPays() + number);
            phoneNumbers.add(phoneNumber);
        }

        Cnaps cnaps = new Cnaps();
        cnaps.setNbrCNAPS(employee.getNbrCnaps());
        NationalCard nationalCard = new NationalCard();
        nationalCard.setNumber(employee.getCinNumber());
        nationalCard.setDate(employee.getCinDate());
        nationalCard.setPlace(employee.getCinPlace());

        SocioPro socio = new SocioPro();
        NationalCard nationalCard1 = new NationalCard();
        Cnaps cnaps1 = new Cnaps();

        List<PhoneNumber> savedPhoneNumbers = phoneNumberRepository.saveAll(phoneNumbers);
        if(!savedPhoneNumbers.isEmpty()){
            socio = socioProService.saveSocioPro(socioPro);
            if(socio != null){
                nationalCard1 = nationalCardService.save(nationalCard);
                if(nationalCard1 != null){
                    cnaps1 = cnapsService.save(cnaps);
                }
            }
        }


        String photo = Base64.getEncoder().encodeToString(employee.getPhoto().getBytes());
        if (employee.getPhoto() != null && !photo.isEmpty()) {
            employee.setPhoto(employee.getPhoto());
        } else {
            employee.setPhoto(new CustomMultipartFile("aucune image"));
        }
        return Employee.builder()
                .cin(nationalCard1)
                .id(employee.getId())
                .dateOfBirth(employee.getDateOfBirth())
                .beggingDate(employee.getBeggingDate())
                .image(photo)
                .pays(employee.getPays())
                .cateSocioPro(socio)
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .emailPerso(employee.getEmailPerso())
                .emailPro(employee.getEmailPro())
                .phoneNumbers(savedPhoneNumbers)
                .outDate(employee.getOutDate())
                .sex(employee.getSex())
                .post(employee.getPost())
                .nbrCnaps(cnaps1)
                .nbrChildren(employee.getNbrChildren())
                .address(employee.getAddress())
                .build();
    }

    public ModelEmployee convertToEmployeeForm(Employee employee) {
        List<String> phoneNumbers = new ArrayList<>();
        for(PhoneNumber number : employee.getPhoneNumbers()){
            phoneNumbers.add(number.getNumber());
        }
        ModelEmployee modelEmployee = new ModelEmployee();
        modelEmployee.setId(employee.getId());
        modelEmployee.setLastName(employee.getLastName());
        modelEmployee.setFirstName(employee.getFirstName());
        modelEmployee.setSocioPro(employee.getCateSocioPro().getId());
        modelEmployee.setDateOfBirth(employee.getDateOfBirth());
        modelEmployee.setSex(employee.getSex());
        modelEmployee.setRegistrationNbr(employee.getRegistrationNbr());
        modelEmployee.setPhoneNbr(phoneNumbers);
        modelEmployee.setAddress(employee.getAddress());
        modelEmployee.setPays(employee.getPays());
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
            modelEmployee.setPost(employee.getPost());
        }
        if (employee.getNbrCnaps() != null) {
            modelEmployee.setNbrCnaps(employee.getNbrCnaps().getNbrCNAPS());
        }

        return modelEmployee;
    }
}