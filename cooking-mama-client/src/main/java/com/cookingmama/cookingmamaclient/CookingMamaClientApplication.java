package com.cookingmama.cookingmamaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@Controller
public class CookingMamaClientApplication {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }
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
