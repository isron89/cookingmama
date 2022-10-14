package com.cookingmama.cookingmamaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class CookingMamaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookingMamaClientApplication.class, args);
    }

    @RequestMapping("/login")
    public String Login(){
        return "login";
    }
    @GetMapping("/register")
    public String Register(){
        return "register";
    }

    @GetMapping("/private")
    public String Private(){
        return "private";
    }
    @GetMapping("/detail")
    public String DetailRecipe(){
        return "detailrecipe";
    }

    @GetMapping("/create")
    public String Create(Model model){

        model.addAttribute("author", "username123");
        return "create";
    }

    @GetMapping("/edit")
    public String Edit(Model model){

        model.addAttribute("authorEdit", "Singgih");
        return "edit";
    }


}
