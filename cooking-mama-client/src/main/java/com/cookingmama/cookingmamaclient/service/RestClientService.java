package com.cookingmama.cookingmamaclient.service;

import com.cookingmama.cookingmamaclient.dto.Recipe;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> getUserString(int id){
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/resep",String.class);
        return response;
    }
    public ResponseEntity<Recipe> getRecipeObject(int id){
        Recipe recipe = restTemplate.getForObject("http://localhost:8080/api/resep"+id,Recipe.class);
        return ResponseEntity.ok(recipe);
    }
    public ResponseEntity<Recipe[]> getAll(){
        ResponseEntity<Recipe[]> response= restTemplate.getForEntity("http://localhost:8080/api/resep",Recipe[].class);
        return response;

    }

}
