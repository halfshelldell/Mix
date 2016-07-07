package com.theironyard.services;

import com.theironyard.entities.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johncrooks on 7/7/16.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
