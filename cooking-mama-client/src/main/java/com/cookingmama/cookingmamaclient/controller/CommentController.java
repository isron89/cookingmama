package com.cookingmama.cookingmamaclient.controller;


import com.cookingmama.cookingmamaclient.dto.Rating;
import com.cookingmama.cookingmamaclient.dto.Recipe;
import com.cookingmama.cookingmamaclient.dto.Comment;
import com.cookingmama.cookingmamaclient.service.impl.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@Controller
public class CommentController {
    @Autowired
    private RecipeServiceImpl service;

    @RequestMapping(value = "/postComment/{recipeid}/{userid}")
    public String postComment(@PathVariable String recipeid, @PathVariable String userid, Model model, Comment comment, String text) { //, @PathVariable String "77"
//        System.out.println(rate + "adaa gak bintangnya");
        model.addAttribute("postcomment", service.comment(text, recipeid, userid, comment));
        return "redirect:/detail/{recipeid}";
    }
}
