package com.cookingmama.cookingmamaclient.controller;

import com.cookingmama.cookingmamaclient.dto.Recipe;
import com.cookingmama.cookingmamaclient.service.impl.RecipeService;
import com.cookingmama.cookingmamaclient.service.impl.RecipeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
        return "private";
    }
}
