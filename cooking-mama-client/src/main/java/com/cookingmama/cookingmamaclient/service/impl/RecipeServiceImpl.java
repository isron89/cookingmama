package com.cookingmama.cookingmamaclient.service.impl;

import com.cookingmama.cookingmamaclient.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
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

    @Value("${resource.getcomment}/{recipeid}")
    private String recipeComment;

    @Value("${resource.postRating}")
    private String postRating;

    @Value("${resource.getRating}/{recipeid}")
    private String getRating;

    @Value("${resource.postComment}/{recipeid}")
    private String postComment;


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


    public Rating rating(int rate, String recipeid,String userid,  Rating rating) {
//        System.out.printf(userid + "ini bintangnya");
        return restTemplate.postForObject(postRating, rating, Rating.class, rate, recipeid);
    }
    public List<Rating> getRating(String recipeid, int rate, Rating rating) {
//        System.out.println(restTemplate.getForObject(idResource, Recipe.class, id));
        return Arrays.stream(restTemplate.getForObject(getRating, Rating[].class)).collect(Collectors.toList());
    }

    public Comment comment(String text, String recipeid,String userid, Comment comment) {
//        System.out.printf(userid + "ini commentnya");
        return restTemplate.postForObject(postComment, comment, Comment.class, text, recipeid);
    }

    // LIST COMMENT
    public List<Comment> getComment(String recipeid) {
//        System.out.println(restTemplate.getForObject(idResource, Recipe.class, id));
        return Arrays.stream(restTemplate.getForObject(recipeComment, Comment[].class, recipeid)).collect(Collectors.toList());
    }


}