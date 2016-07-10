package com.theironyard.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by johncrooks on 7/7/16.
 */
@Entity
@Table(name="favs")
public class Fav {
    @Id
    @GeneratedValue
    int id;

    @Column (nullable = false)
    Boolean isFav;

    @ManyToOne
    User user;

    @Transient
    Integer recipeId;

    @ManyToOne
    Recipe recipe;



    public Fav() {
    }

    public Fav(Boolean isFav, User user, Recipe recipe) {
        this.isFav = isFav;
        this.user = user;
        this.recipe = recipe;
    }

    public Fav(int id, Boolean isFav, User user, Recipe recipe) {
        this.id = id;
        this.isFav = isFav;
        this.user = user;
        this.recipe = recipe;
    }


    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(Boolean isFav) {
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

}
