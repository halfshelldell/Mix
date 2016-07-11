package com.theironyard.controllers;

import com.theironyard.entities.Fav;
import com.theironyard.entities.Recipe;
import com.theironyard.entities.User;
import com.theironyard.services.FavRepository;
import com.theironyard.services.RecipeRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by johncrooks on 7/7/16.
 */

@RestController
public class MixRestController {
    @Autowired
    UserRepository userRepo;

    @Autowired
    RecipeRepository recipeRepo;

    @Autowired
    FavRepository favRepo;

    @PostConstruct
    public void init() throws SQLException, FileNotFoundException {
        Server.createWebServer().start();
        /*User user = new User("Dell", "abc");
        userRepo.save(user);
        Recipe recipe = new Recipe("pizza", 60, "pizzapizzapizza", "doughmeatcheese", "easy", 1, "hello", "italian", user);
        recipeRepo.save(recipe);
        Fav fav = new Fav(true, user, recipe, recipe.getId());
        favRepo.save(fav);*/
//        parseRecipes();
    }

    @RequestMapping (path ="/recipes", method = RequestMethod.GET)
    public Iterable<Recipe> home(HttpSession session) throws Exception {

        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new Exception("User not in database!");
        }

       return recipeRepo.findAll();

    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(HttpSession session, @RequestBody User user) throws Exception {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb == null) {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            userRepo.save(user);
        }
        else if (!PasswordStorage.verifyPassword(user.getPassword(), userFromDb.getPassword())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("username", user.getUsername());
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session, HttpServletResponse response) throws Exception {
        session.invalidate();
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/create-recipe", method = RequestMethod.POST)
    public void createRecipe(HttpSession session, HttpServletResponse response, MultipartFile file, String recipeName, Integer time, String instructions, String ingredients, String skill, Integer votes, String category) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new Exception("User not in database!");
        }

        if (!file.getContentType().contains("image")){
            throw new Exception("only images allowed");
        }

        File dir = new File("public/files");
        dir.mkdirs();

        File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(uploadedFile);
        fos.write(file.getBytes());

        if (recipeName == null){
            recipeName = "Unknown";
        }
        if (time == null){
            time = 0;
        }
        if (ingredients == null){
            ingredients = "Unknown";
        }
        if (instructions == null){
            instructions = "Unknown";
        }
        if (skill == null){
            skill = "Unknown";
        }
        if (category == null){
            category = "Unknown";
        }

        if (votes == null) {
            votes = 0;
        }

        Recipe recipe = new Recipe(recipeName, time, instructions, ingredients, skill, votes, category, uploadedFile.getName(), user);
        recipeRepo.save(recipe);

        Fav fav = new Fav(false, user,recipe);
        favRepo.save(fav);

        response.sendRedirect("/#/rating");

    }

    @RequestMapping(path = "/get-mine", method = RequestMethod.GET)
    public Iterable<Recipe> getMyRecipes(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new Exception("User not in database!");
        }

        return recipeRepo.findByUser(user);
    }

    @RequestMapping(path = "/edit-recipe", method = RequestMethod.POST)
    public void editRecipe(int id, HttpSession session, MultipartFile file, String recipeName, Integer time, String instructions, String ingredients, String skill, String filename, String category) throws Exception {
        Recipe r = recipeRepo.findOne(id);

        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new Exception("User not in database!");
        }
        else if (user != r.getUser()){
            throw new Exception("logged in user and recipe creator do not match");
        }

        if (category != null) {
            r.setCategory(category);
        }
        if (ingredients != null) {
            r.setIngredients(ingredients);
        }
        if (instructions != null) {
            r.setInstructions(instructions);
        }
        if (recipeName != null) {
            r.setRecipeName(recipeName);
        }
        if (skill != null) {
            r.setSkill(skill);
        }
        if (time != null) {
            r.setTime(time);
        }
        if (file != null){

            if (!file.getContentType().contains("image")){
                throw new Exception("only images allowed");
            }

            File f = new File("public/files/" + r.getFileName());
            f.delete();

            File dir = new File("public/files");
            dir.mkdirs();

            File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
            FileOutputStream fos = new FileOutputStream(uploadedFile);
            fos.write(file.getBytes());

            r.setFileName(uploadedFile.getName());
        }

        recipeRepo.save(r);
    }

    @RequestMapping(path = "/delete-recipe", method = RequestMethod.POST)
    public void deleteRecipe(HttpSession session, @RequestBody Recipe recipe) throws Exception {
        Recipe r = recipeRepo.findOne(recipe.getId());

        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new Exception("User not in database!");
        }
        else if (user != r.getUser()){
            throw new Exception("logged in user and recipe creator do not match");
        }

        File f = new File("public/files/" + r.getFileName());
        f.delete();
        recipeRepo.delete(r);
    }

    @RequestMapping(path = "/favs", method = RequestMethod.POST)
    public void favoriteRecipe(HttpSession session, @RequestBody Fav fav) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new Exception("User not in database, try again!");
        }


        Recipe recipe = recipeRepo.findOne(fav.getRecipeId());

        if (recipe == null) {
            throw new Exception("Can't find the recipe");
        }

        recipe.setVotes(recipe.getVotes() + (fav.getIsFav() ? 1 : -1));
        fav.setRecipe(recipe);
        fav.setUser(user);
        favRepo.save(fav);
    }
    public void parseRecipes() throws FileNotFoundException {
        User user = new User("a", "a");
        if(!userRepo.findByUsername(user.getUsername()).equals("a")){
            userRepo.save(user);
        }else{

            File f = new File("Mix-delimited.csv");
            Scanner scanner = new Scanner(f);
            scanner.nextLine();
            while(scanner.hasNext()) {
                String[] recipeString = scanner.nextLine().split("\\|");
                Recipe recipe1 = new Recipe(recipeString[0], Integer.valueOf(recipeString[1]), recipeString[2], recipeString[3], recipeString[4], Integer.valueOf(recipeString[5]), recipeString[6], recipeString[7], user);
                recipeRepo.save(recipe1);

                Fav fav = new Fav(false, user, recipe1);
                favRepo.save(fav);

                System.out.println(" ");
            }

        }
    }


}

