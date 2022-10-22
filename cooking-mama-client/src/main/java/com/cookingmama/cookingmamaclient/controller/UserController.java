package com.cookingmama.cookingmamaclient.controller;

import com.cookingmama.cookingmamaclient.dto.Login;
import com.cookingmama.cookingmamaclient.dto.User;
import com.cookingmama.cookingmamaclient.dto.UserLogin;
import com.cookingmama.cookingmamaclient.service.impl.RecipeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@Controller
public class UserController {

    @Autowired
    private RecipeServiceImpl service;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpSession session;

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
    public String Login(@Validated @ModelAttribute("newLogin") Login login, HttpServletRequest request) throws JsonProcessingException {
        //Login user = service.login(login);
        //ResponseEntity<UserLogin> response = restTemplate.postForEntity("http://localhost:8080/auth/signin", login, UserLogin.class);
        ResponseEntity<UserLogin> response = restTemplate.postForEntity("http://localhost:8080/api/auth/signin",login, UserLogin.class);
        //String token = String.valueOf(response.getBody());
        //System.out.println("token" + token + "response" + response.getBody().getAccessToken());
        UserLogin userLogin = response.getBody();
        @SuppressWarnings("unchecked")
        //List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        String token = (String) request.getSession().getAttribute("Token");
        String username = (String) request.getSession().getAttribute("Username");
        Long userid = (Long) request.getSession().getAttribute("Userid");
//        if (token == null) { // && username == null && userid == null
            //token = new ArrayList<>();
            request.getSession().setAttribute("Token", userLogin.getAccessToken());
            request.getSession().setAttribute("Username", userLogin.getUsername());
            request.getSession().setAttribute("Userid", userLogin.getId());
//        }
        //messages.add(msg);
//        request.getSession().setAttribute("Token", userLogin.getAccessToken());
//        request.getSession().setAttribute("Username", userLogin.getUsername());
//        request.getSession().setAttribute("Userid", userLogin.getId());

        //Work
        System.out.println(session.getAttribute("Userid"));
        System.out.println(session.getAttribute("Username"));
        System.out.println(session.getAttribute("Token"));
        System.out.println(userid);
        System.out.println(username);
        System.out.println(token);
        return "redirect:/home";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
//    @GetMapping("/testlogin")
//    public String testlogin(Model model, HttpSession session){
//        String username = (String) session.getAttribute("Username");
//        System.out.println(username);
//        model.addAttribute("username", username );
//        return username;
//    }
}
