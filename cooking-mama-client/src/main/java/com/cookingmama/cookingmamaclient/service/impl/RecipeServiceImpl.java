package com.cookingmama.cookingmamaclient.service.impl;

import com.cookingmama.cookingmamaclient.dto.Login;
import com.cookingmama.cookingmamaclient.dto.Recipe;
import com.cookingmama.cookingmamaclient.dto.User;
import com.cookingmama.cookingmamaclient.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl {
    @Value("${resource.recipes}")
    private String resource;

    @Value("${resource.create}")
    private String createResource;

//    Buat regis
    @Value("${resource.register}")
    private String resourceRegister;

//    Buat login
    @Value("${resource.login}")
    private String resourceLogin;

    @Autowired
    private RestTemplate restTemplate;


    public List<Recipe> findAll(){
        System.out.println(Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList()));
        return Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList());
    }

    public Recipe recipe(Recipe recipe) {
        return restTemplate.postForObject(createResource, recipe, Recipe.class);
    }

//    Buat regis
    public User user(User user) {
        return restTemplate.postForObject(resourceRegister, user, User.class);
    }

//    Buat login
    public Login login(Login login) {
        return restTemplate.postForObject(resourceLogin, login, Login.class);
    }
}
