package com.theironyard.services;

import com.theironyard.entities.Recipe;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johncrooks on 7/7/16.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    Iterable<Recipe> findByUser(User user);
    Recipe findByRecipeName(String name);
}
