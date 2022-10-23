package com.cookingmama.cookingmamaclient.controller;

import com.cookingmama.cookingmamaclient.dto.Comment;
import com.cookingmama.cookingmamaclient.dto.MessageDTO;
import com.cookingmama.cookingmamaclient.dto.Recipe;
import com.cookingmama.cookingmamaclient.dto.Rating;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    HttpSession session;

    @GetMapping("/home")
    public String Home(Model model) {
        String username = (String) session.getAttribute("Username");
        Long userid = (Long) session.getAttribute("Userid");
//        Long id = (Long) session.getAttribute("Userid");
        //HttpSession
        model.addAttribute("recipes", service.findAll());
        model.addAttribute("username", username );
        model.addAttribute("userid", userid );
        //model.addAttribute("username", username);
        return "home";
    }
//    @GetMapping("/private")
//    public String Private(Model model){
//        model.addAttribute("recipes", service.MyRecipes());
//        return "private";
//    }

    @GetMapping("/private")
    public String Private(Model model) {
        String username = (String) session.getAttribute("Username");
        Long userid = (Long) session.getAttribute("Userid");
        model.addAttribute("recipes", service.MyRecipes(userid));
        model.addAttribute("userid", userid );
        return "private";
    }

    @GetMapping("/create")
    public String Create(Model model) {
        Long useridSession = (Long) session.getAttribute("Userid");
        model.addAttribute("newRecipe", new Recipe());
        model.addAttribute("useridSession", useridSession );
        return "create";
    }

    @PostMapping(value = "/createRecipe")
    public String create(@Validated @ModelAttribute("newRecipe") Recipe recipe) {
//        String name=recipe.getName();
//        String ingredients=recipe.getIngredients();
//        String howto=recipe.getHowto();

        service.recipe(recipe);
        return "redirect:/home";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(HttpClientErrorException ex, Model model) throws IOException {
        MessageDTO dto = mapper.readValue(ex.getResponseBodyAsByteArray(), MessageDTO.class);
        model.addAttribute("error", dto.getMessage());
        return Home(model);
    }

    @GetMapping("/detail/{id}")
    public String getRecipesId (@PathVariable Long id, Model model, Recipe recipe){
        // For get Detail Recipe
        try{
            model.addAttribute("detail",service.getDetail( id, recipe));

            // For new post Rating
            model.addAttribute("postRating", new Rating());

            // For get all the comment for this recipe

            String recipeid = Long.toString(id);
            System.out.println(recipeid + "ini id resep");
            model.addAttribute("recipeComments", service.getComment(String.valueOf(id)));
            System.out.println( service.getComment(String.valueOf(id)) +"<<<<<<list comment");
            return "detailrecipe";
        }catch(Exception err){
            System.out.println(err.getMessage() + "<<<<error");
            return "detailrecipe";
        }

    }

    @RequestMapping(value = "/deleteRecipe/{id}")
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String Edit(@PathVariable Long id, Model model, Recipe recipe) {
        model.addAttribute("editRecipe", service.getDetail(id, recipe));
        return "edit";
    }

//    @PostMapping(value = "/updateRecipe/{id}")
//    public String saveEdit(@PathVariable Long id ,@Validated @ModelAttribute("updateRecipe") Recipe saveEdit) {
//        service.update(saveEdit);
//        return "redirect:/home";
//    }


    @PostMapping(value = "/updateRecipe/{id}")
    public String saveEdit(@PathVariable Long id, @Validated @ModelAttribute("editRecipe") Recipe saveEdit) {
        try {
            System.out.println(saveEdit + "<<<<< save edit log");
            service.update(saveEdit.getId(), saveEdit);
            // idedit
            return "redirect:/detail/{id}";
        } catch (Exception err) {
            System.out.println(saveEdit + "<<<<< save edit log");
            System.out.println(err.getMessage());
            return "edit";
        }

    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String Home(@RequestParam (value = "search", required = false) String search, Model model) {
        model.addAttribute("recipes", service.searchRecipe(search));
        return "home";
    }


}

