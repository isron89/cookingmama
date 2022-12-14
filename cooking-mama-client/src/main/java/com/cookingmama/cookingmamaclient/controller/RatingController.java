package com.cookingmama.cookingmamaclient.controller;

import com.cookingmama.cookingmamaclient.dto.Rating;
import com.cookingmama.cookingmamaclient.dto.Recipe;
import com.cookingmama.cookingmamaclient.service.impl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@Controller
public class RatingController {
    @Autowired
    private RecipeServiceImpl service;

    @RequestMapping(value = "/postRating/{recipeid}/{userid}")
    public String postRating(@PathVariable String recipeid, @PathVariable String userid, Model model, Rating rating, int rate) { //, @PathVariable String "77"
//        System.out.println(rate + "adaa gak bintangnya");
        model.addAttribute("postrating", service.rating(rate, recipeid, userid,rating));
        return "redirect:/detail/{recipeid}";
    }
//    @RequestMapping(value = "rating", method = RequestMethod.GET)
//    public String Home(@RequestParam String recipeid, Model model, Rating rating, int rate) {
//        model.addAttribute("ratings", service.getRating(recipeid, rate, rating));
//        return "home";
//    }
}
