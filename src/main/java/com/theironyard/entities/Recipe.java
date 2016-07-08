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

    @Column (nullable = false)
    int votes;

    @Column (nullable = false)
    String category;

    @Column
    String fileName;

    @ManyToOne
    User user;

    public Recipe() {
    }

    public Recipe(String recipeName, int time, String instructions, String ingredients, String skill, int votes, String category, String fileName, User user) {
        this.recipeName = recipeName;
        this.time = time;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.skill = skill;
        this.votes = votes;
        this.category = category;
        this.fileName = fileName;
        this.user = user;
    }

    public Recipe(int id, String recipeName, int time, String instructions, String ingredients, String skill, int votes, String category, String fileName, User user) {
        this.id = id;
        this.recipeName = recipeName;
        this.time = time;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.skill = skill;
        this.votes = votes;
        this.category = category;
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

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
