package com.prog4.controller;

import com.prog4.controller.mapper.UserMapper;
import com.prog4.controller.model.ModelUSer;
import com.prog4.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping( "/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/main")
    public String index(){
        return "index";
    }

    @GetMapping("/registry")
    public String registryForm(Model model) {
        model.addAttribute("member", new ModelUSer());
        return "auth/registration";
    }

    @PostMapping("/registry")
    public String registry(@ModelAttribute ModelUSer registryRequest) {
        service.save(mapper.toEntity(registryRequest));
        return "redirect:/login";
    }
}
