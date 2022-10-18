package com.cookingmama.cookingmamaclient.controller;

import com.cookingmama.cookingmamaclient.dto.Recipe;
import com.cookingmama.cookingmamaclient.service.RestClientService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RestClientController {


    @Autowired
    private RestClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<String> findUserStringById(@PathVariable("id") Long id){

        return service.getUserString(id);
    }
    @GetMapping("/object/{id}")
    public ResponseEntity<Recipe> findRecipeObject(@PathVariable("id") Long id){

        return service.getRecipeObject(id);
    }

    @GetMapping()
    public ResponseEntity<?> findAllRecipe(){
        return  service.getAll();
    }


}
