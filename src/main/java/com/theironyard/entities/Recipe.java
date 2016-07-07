package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by johncrooks on 7/7/16.
 */
@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String recipeName;

    @Column (nullable = false)
    int time;

    @Column (nullable = false)
    String instructions;

    @Column (nullable = false)
    String ingredients;

    @Column (nullable = false)
    String skill;

    @Column
    String fileName;

    @ManyToOne
    User user;

    public Recipe() {
    }

    public Recipe(String recipeName, int time, String instructions, String ingredients, String skill, String fileName, User user) {
        this.recipeName = recipeName;
        this.time = time;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.skill = skill;
        this.fileName = fileName;
        this.user = user;
    }

    public Recipe(int id, String recipeName, int time, String instructions, String ingredients, String skill, String fileName, User user) {
        this.id = id;
        this.recipeName = recipeName;
        this.time = time;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.skill = skill;
        this.fileName = fileName;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getTime() {
        return time;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
