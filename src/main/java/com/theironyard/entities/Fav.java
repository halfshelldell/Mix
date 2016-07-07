package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by johncrooks on 7/7/16.
 */
@Entity
@Table(name="recipes")
public class Fav {
    @Id
    @GeneratedValue
    int id;

    @Column (nullable = false)
    boolean isFav;

    @ManyToOne
    User user;

    @ManyToOne
    Recipe recipe;

    @Transient
    Integer recipeID;

    public Fav() {
    }

    public Fav(boolean isfav, User user, Recipe recipe, Integer recipeID) {
        this.isFav = isfav;
        this.user = user;
        this.recipe = recipe;
        this.recipeID = recipeID;
    }

    public Fav(int id, boolean isfav, User user, Recipe recipe, Integer recipeID) {
        this.id = id;
        this.isFav = isfav;
        this.user = user;
        this.recipe = recipe;
        this.recipeID = recipeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(boolean isFav) {
        this.isFav = isFav;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Integer getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(Integer recipeID) {
        this.recipeID = recipeID;
    }
}
