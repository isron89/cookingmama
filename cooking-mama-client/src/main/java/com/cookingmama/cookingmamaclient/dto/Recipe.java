package com.cookingmama.cookingmamaclient.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "ingredients")

    private String ingredients;

    @Column(name = "howto")

    private String howto;

    @Column(name = "publik")
    private Boolean publik;

    @Column(name = "userid")
    private String userid;
//    private String steps;
//    private String image;

    public Recipe() {
    }
    //long id, String name, String ingredients, String recipeModelName, Boolean publik, String userid
    public Recipe(Long id, String name, String ingredients, String howto, Boolean publik, String userid) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.howto = howto;
        this.publik = publik;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "RecipeModel [Recipe id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", howto="
                + howto + ", publik=" + publik + ", iduser=" + userid + "]";
    }
}


