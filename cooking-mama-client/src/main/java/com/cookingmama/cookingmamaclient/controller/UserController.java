package com.cookingmama.cookingmamaclient.controller;

import com.cookingmama.cookingmamaclient.dto.Login;
import com.cookingmama.cookingmamaclient.dto.User;
import com.cookingmama.cookingmamaclient.service.impl.RecipeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@Controller
public class UserController {

    @Autowired
    private RecipeServiceImpl service;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/register")
    public String Regis(Model model){
        model.addAttribute("newUser",new User() );
        return "register";
    }

    @PostMapping(value = "/registerUser")
    public String Regis(@Validated @ModelAttribute("newUser") User user) {
        service.user(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String Login(Model model){
        model.addAttribute("newLogin",new Login() );
        return "login";
    }

    @PostMapping(value = "/loginUser")
    public String Login(@Validated @ModelAttribute("newLogin") Login login) {
        service.login(login);
        return "redirect:/home";
    }
}
