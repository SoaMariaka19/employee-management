package com.prog4.controller;

import com.prog4.controller.mapper.BusinessMapper;
import com.prog4.controller.model.ModelBusiness;
import com.prog4.entity.Business;
import com.prog4.repository.BusinessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@Controller
public class BusinessController {
    private final BusinessMapper mapper;
    private final BusinessRepository repository;
    @GetMapping("/business/create")
    public String showBusinessForm(Model model) {
        model.addAttribute("business", new ModelBusiness());
        return "business/business";
    }
    @PostMapping("/business/create")
    public String createBusiness(@ModelAttribute("business") ModelBusiness business,
                                 @RequestParam("logoFile") MultipartFile logoFile,
                                 RedirectAttributes redirectAttributes) throws IOException {
        business.setLogo(logoFile);
        mapper.toEntity(business);
        redirectAttributes.addFlashAttribute("successMessage", "Business created successfully!");
        return "redirect:/";
    }
    @GetMapping("/")
    public String getBusiness(Model model) throws IOException {
        List<Business> business = repository.findAll();
        Business business1 = new Business();
        if (!business.isEmpty()){
            model.addAttribute("business", business.get(0));
        }
        model.addAttribute("business",business1);
        return "/index";
    }
}
