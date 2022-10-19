package com.cookingmama.cookingmamaclient.service.impl;

import com.cookingmama.cookingmamaclient.dto.Recipe;
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

    @Value("${resource.getId}/{id}")
    private String idResource;

    @Value("${resource.myrecipes}")
    private String myrecipes;

    @Autowired
    private RestTemplate restTemplate;

    public List<Recipe> findAll() {
        System.out.println(Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList()));
        return Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList());
    }

    public Recipe recipe(Recipe recipe) {

        return restTemplate.postForObject(createResource, recipe, Recipe.class);

    }

//    public Optional<Recipe> getRecipes() {
////        System.out.println(Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList()));
//        return null;
//    }
public Recipe getDetail(Long id, Recipe recipe) {
//        System.out.println(restTemplate.getForObject(idResource, Recipe.class, id));
    return restTemplate.getForObject(idResource, Recipe.class, id);
}
//    public ResponseEntity<Recipe> getProductById(@PathVariable("id") long id) {
//        Optional<Recipe> recipesData = recipeRepository.findById(id);
//        return recipesData.isPresent() ? new ResponseEntity<>(recipesData.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    // MY RECIPES
    public List<Recipe> MyRecipes() {
    System.out.println(Arrays.stream(restTemplate.getForObject(myrecipes, Recipe[].class)).collect(Collectors.toList()));
    return Arrays.stream(restTemplate.getForObject(myrecipes, Recipe[].class)).collect(Collectors.toList());
}
}