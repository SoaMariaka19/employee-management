package com.prog4.controller;

import com.prog4.model.SocioPro;
import com.prog4.service.SocioProService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class SocioProController {
    private final SocioProService service;

    @PostMapping("/socio/save")
    public String save(@ModelAttribute("sociopro")SocioPro socioPro){
        service.saveSocioPro(socioPro);
        return "redirect:/employees";
    }

    @GetMapping("/socio/save")
    public String showSocioForm(Model model){
        model.addAttribute("sociopro",new SocioPro());
        return "socio-pro/add-socio-pro";
    }

}
