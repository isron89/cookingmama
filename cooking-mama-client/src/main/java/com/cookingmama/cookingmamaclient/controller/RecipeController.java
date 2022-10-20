package com.cookingmama.cookingmamaclient.controller;

import com.cookingmama.cookingmamaclient.dto.MessageDTO;
import com.cookingmama.cookingmamaclient.dto.Recipe;
import com.cookingmama.cookingmamaclient.service.impl.RecipeService;
import com.cookingmama.cookingmamaclient.service.impl.RecipeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.Component
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@Controller
public class RecipeController {
    @Autowired
    private RecipeServiceImpl service;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/home")
    public String home (Model model){
        model.addAttribute("recipes", service.findAll());
        return "home";
    }
    @GetMapping("/private")
    public String Private(Model model){
        model.addAttribute("recipes", service.MyRecipes());
        return "private";
    }

    @GetMapping("/create")
    public String Create(Model model){
        model.addAttribute("newRecipe",new Recipe() );
        return "create";
    }

    @PostMapping(value = "/createRecipe")
    public String create(@Validated @ModelAttribute("newRecipe") Recipe recipe) {
        service.recipe(recipe);
        return "redirect:/home";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(HttpClientErrorException ex, Model model) throws IOException {
        MessageDTO dto = mapper.readValue(ex.getResponseBodyAsByteArray(), MessageDTO.class);
        model.addAttribute("error", dto.getMessage());
        return home(model);
    }
    @GetMapping("/detail/{id}")
    public String getRecipesId (@PathVariable Long id,String name, Model model, Recipe recipe){
        model.addAttribute("detail",service.getDetail( id, recipe));
        return "detailrecipe";
    }


}

