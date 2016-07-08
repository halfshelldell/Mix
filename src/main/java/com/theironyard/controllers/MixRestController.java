package com.theironyard.controllers;

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
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Iterator;

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
    public void init() throws SQLException {
        Server.createWebServer().start();
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
    public void createRecipe(HttpSession session, MultipartFile file, String recipeName, int time, String instructions, String ingredients, String skill, int votes, String filename, String category) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new Exception("User not in database!");
        }

        File dir = new File("public/files");
        dir.mkdirs();

        File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(uploadedFile);
        fos.write(file.getBytes());

        Recipe recipe = new Recipe(recipeName, time, instructions, ingredients, skill, votes, category, uploadedFile.getName(), user);
        recipeRepo.save(recipe);
    }

}
