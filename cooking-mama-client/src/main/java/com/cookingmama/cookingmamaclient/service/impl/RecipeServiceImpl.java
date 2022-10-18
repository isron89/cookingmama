package com.cookingmama.cookingmamaclient.service.impl;

import com.cookingmama.cookingmamaclient.dto.Recipe;
import com.cookingmama.cookingmamaclient.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl {
    @Value("${resource.recipes}")
    private String resource;

    @Value("${resource.register}")
    private String resourceregister;

    @Autowired
    private RestTemplate restTemplate;

    public List<Recipe> findAll(){
        return Arrays.stream(restTemplate.getForObject(resource, Recipe[].class)).collect(Collectors.toList());
    }
}
