package com.cookingmama.cookingmamaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.CacheRequest;
    import java.util.HashMap;
import java.util.Map;

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
//        CacheRequest object;
//        String body = object.getBody();
//        Gson gson = new Gson();
//        Map<String, Object> map = gson.fromJson(body, HashMap.class);
//        Map<String, Object> data = (Map<String, Object>) map.get("data");
//        Map<String, Object> creditTerm = (Map<String, Object>) data.get("MS8B50FHS");
//        String creditTermValue = creditTerm.get("creditTerm").toString();
//        toStringSystem.out.println(creditTermValue);
        return "edit";
    }


}
