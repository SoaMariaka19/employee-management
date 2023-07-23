package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelBusiness;
import com.prog4.entity.Business;
import com.prog4.entity.CustomMultipartFile;
import com.prog4.entity.Fiscal;
import com.prog4.service.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component
@AllArgsConstructor
public class BusinessMapper {
    private final BusinessService service;
    public Business toEntity(ModelBusiness modelBusiness) throws IOException {
        Fiscal fiscal = new Fiscal();
        fiscal.setRCS(modelBusiness.getRcs());
        fiscal.setNIF(modelBusiness.getNif());
        fiscal.setSTAT(modelBusiness.getStat());

        String logo = Base64.getEncoder().encodeToString(modelBusiness.getLogo().getBytes());
        if (modelBusiness.getLogo() != null && !logo.isEmpty()) {
            modelBusiness.setLogo(modelBusiness.getLogo());
        } else {
            modelBusiness.setLogo(new CustomMultipartFile("aucune image"));
        }
        Business business = Business.builder()
                .logo(logo)
                .name(modelBusiness.getName())
                .email(modelBusiness.getEmail())
                .address(modelBusiness.getAddress())
                .slogan(modelBusiness.getSlogan())
                .idFiscal(fiscal)
                .build();
        service.save(business);
        return business;
    }
}
