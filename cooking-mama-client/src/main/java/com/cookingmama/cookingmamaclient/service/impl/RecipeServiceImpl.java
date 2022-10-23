package com.cookingmama.cookingmamaclient.service.impl;

import com.cookingmama.cookingmamaclient.dto.Login;
import com.cookingmama.cookingmamaclient.dto.Recipe;
import com.cookingmama.cookingmamaclient.dto.User;
import com.cookingmama.cookingmamaclient.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.stream.events.Comment;
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


    @Value("${resource.getId}/{id}")
    private String idResource;


    @Value("${resource.myrecipes}")
    private String myrecipes;

    @Value("${resource.delete}/{id}")
    private String delete;

    @Value("${resource.edit}/{id}")
    private String updateRecipe;

    @Value("${resource.search}{search}") //+
    private String recipeSearch;

    @Value("${resource.getcomment}/{id}")
    private String recipeComment;


    @Autowired
    private RestTemplate restTemplate;

    public List<Recipe> findAll() {
        System.out.println(Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList()));
        return Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList());
    }
//    public List<Recipe> findAllPrivate() {
////        System.out.println(Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList()));
//        return Arrays.stream(restTemplate.getForObject(resourcePrivate, Recipe[].class)).collect(Collectors.toList());
//    }

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

    public Recipe getDetail(Long id, Recipe recipe) {
//        System.out.println(restTemplate.getForObject(idResource, Recipe.class, id));
        return restTemplate.getForObject(idResource, Recipe.class, id);
    }

    // MY RECIPES
//    public List<Recipe> MyRecipes() {
//    System.out.println(Arrays.stream(restTemplate.getForObject(myrecipes, Recipe[].class)).collect(Collectors.toList()));
//    return Arrays.stream(restTemplate.getForObject(myrecipes, Recipe[].class)).collect(Collectors.toList());
//}
    public List<Recipe> MyRecipes() {

        System.out.println(Arrays.stream(restTemplate.getForObject(myrecipes, Recipe[].class)).collect(Collectors.toList()));
        return Arrays.stream(restTemplate.getForObject(myrecipes, Recipe[].class)).collect(Collectors.toList());
    }

    public Recipe update(Long id, Recipe update) {
        System.out.println(update + "<<< data sent");
        return restTemplate.postForObject(updateRecipe, update, Recipe.class, id);
    }

    public void delete(Long id) {
        restTemplate.delete(delete, id);
    }

//    public Recipe update(Long id, Recipe update) {
//        return restTemplate.postForObject(updateRecipe, update, Recipe.class,id);
//    }

    public List<Recipe> searchRecipe(String search) {
//        System.out.println(Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList()));
        return Arrays.stream(restTemplate.getForObject(recipeSearch, Recipe[].class, search)).collect(Collectors.toList());
    }



}