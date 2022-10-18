package com.cookingmama.cookingmamaclient.service.impl;

import com.cookingmama.cookingmamaclient.dto.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl {
    @Value("${resource.recipes}")
    private String resource;

    @Value("${resource.create}")
    private String createResource;

    @Value("${resource.recipes}/{id}")
    private String idResource;

    @Autowired
    private RestTemplate restTemplate;


    public List<Recipe> findAll() {
        System.out.println(Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList()));
        return Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList());
    }

    public Recipe recipe(Recipe recipe) {

        return restTemplate.postForObject(createResource, recipe, Recipe.class);
    }

    public List<Recipe> getId() {
        return Arrays.stream(restTemplate.getForObject(idResource, Recipe[].class)).collect(Collectors.toList());
    }
    public ResponseEntity<Recipe> getProductById(@PathVariable("id") long id) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}