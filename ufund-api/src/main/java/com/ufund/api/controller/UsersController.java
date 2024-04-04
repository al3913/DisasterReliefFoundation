package com.ufund.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.Mapping;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ufund.api.persistence.UsersDAO;
import com.ufund.api.model.Need;
import com.ufund.api.model.User;

/**
 * Handles the REST API requests for the User resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Andy Lin and Matthew Peck
 */
@RestController
@RequestMapping("users")
public class UsersController {
    private static final Logger LOG = Logger.getLogger(UsersController.class.getName());
    private UsersDAO usersDao;

    /**
     * Creates a REST API controller to respond to requests
     * 
     * @param usersDAO The {@link UsersDAO User Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public UsersController(UsersDAO usersDAO) {
        this.usersDao = usersDAO;
    }

    @GetMapping("")
    public ResponseEntity<User[]> getUsers() {
        LOG.info("GET /users");
        try {
            User[] users = usersDao.getUsers();
            if (users != null)
                return new ResponseEntity<User[]>(users, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<User> getCurrentUsers() {
        LOG.info("GET /currentUsers");
        try {
            User currentUser = usersDao.getCurrentUser();
            if (currentUser != null)
                return new ResponseEntity<User>(currentUser, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username)
    {
        LOG.info("GET /user/" + username);
        try {
            User user = usersDao.getUser(username);
            if (user != null)
                return new ResponseEntity<User>(user, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{username}/basket")
    public ResponseEntity<Need[]> getUserBasket(@PathVariable String username)
    {
        LOG.info("GET /user/" + username + "/basket");
        try {
            User user = usersDao.getUser(username);
            Need[] basket = user.getBasket();
            if (user != null)
                return new ResponseEntity<Need[]>(basket, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@linkplain User user} with the provided user object
     * 
     * @param user - The {@link User user} to create
     * 
     * @return ResponseEntity with created {@link User user} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link User user} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        LOG.info("POST /users " + user);
        try {
            User newUser = usersDao.createUser(user);
            if (newUser != null)
                return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
            else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{username}/basketadd")
    public ResponseEntity<User> addToBasket(@PathVariable String username, @RequestBody Need need) {
        LOG.info("POST /users " + username);
        try {
            User user = usersDao.getUser(username);
            user.addToBasket(need);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{username}/basketremove/{need}")
    public ResponseEntity<User> removeFromBasket(@PathVariable String username, @RequestBody Need need) {
        LOG.info("DELETE /users " + username);
        try {
            User user = usersDao.getUser(username);
            if(user != null){
                user.removeFromBasket(need);
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/isNewUser")
    public ResponseEntity<Boolean> isNewUser(@RequestParam String username){
        boolean isNewUser = usersDao.isNewUser(username);
        if(isNewUser){
            return new ResponseEntity<Boolean>(isNewUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } 
    /**
     * Deletes a {@linkplain User user} with the given id
     * 
     * @param id The id of the {@link User user} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        LOG.info("DELETE /users/" + id);
        try {
            boolean deleted = usersDao.deleteUser(id);
            if (deleted)
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}