package com.cookingmama.cookingmamaclient.repository;


import com.cookingmama.cookingmamaclient.dto.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@Repository
public interface recipeRepository extends JpaRepository<Recipe, Long> {

}
