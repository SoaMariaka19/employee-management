package com.prog4.controller;

import com.prog4.entity.Business;
import com.prog4.entity.SocioPro;
import com.prog4.repository.BusinessRepository;
import com.prog4.service.SocioProService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class SocioProController {
    private final SocioProService service;
    private final BusinessRepository businessRepository;
    @PostMapping("/socio/save")
    public String save(@ModelAttribute("sociopro")SocioPro socioPro){
        service.saveSocioPro(socioPro);
        return "redirect:/employees";
    }

    @GetMapping("/socio/save")
    public String showSocioForm(Model model){
        List<Business> business = businessRepository.findAll();
        Business business1 = new Business();
        if (business.isEmpty()){
            model.addAttribute("business", business1);
        }
        model.addAttribute("business",business.get(0));
        model.addAttribute("sociopro",new SocioPro());
        return "socio-pro/add-socio-pro";
    }

}
