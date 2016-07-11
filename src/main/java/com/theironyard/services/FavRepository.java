package com.theironyard.services;

import com.theironyard.entities.Fav;
import com.theironyard.entities.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johncrooks on 7/7/16.
 */
public interface FavRepository extends CrudRepository<Fav, Integer> {
    Fav findByRecipe(Recipe recipe);
}
