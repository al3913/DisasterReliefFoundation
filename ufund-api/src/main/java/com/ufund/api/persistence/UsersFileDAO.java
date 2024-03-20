package com.ufund.api.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ufund.api.model.HelpRequest;
import com.ufund.api.model.Need;
import com.ufund.api.model.User;

/**
 * Implements the functionality for JSON file-based persistence for request objects.
 * The class provides methods to interact with a local cache of Request objects stored in a JSON file.
 *
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed.
 *
 * @author Matthew Peck
 */
@Component
public class UsersFileDAO implements UsersDAO {

    private static final Logger LOG = Logger.getLogger(MailboxFileDAO.class.getName());
    
    Map<Integer, User> users;   // Provides a local cache of the Need objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Need
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new Need
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Need File Data Access Object.
     *
     * @param filename      Filename to read from and write to.
     * @param objectMapper  Provides JSON Object to/from Java Object serialization and deserialization.
     *
     * @throws IOException when the file cannot be accessed or read from.
     */
    public UsersFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        //this.loggedInFile = loggedInFile;
        this.objectMapper = objectMapper;
        load();  // load the requests from the file
    }

    /**
     * Generates the next id for a new {@linkplain HelpRequest request}.
     *
     * @return The next id.
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain HelpRequest requests} from the tree map.
     *
     * @return The array of {@link HelpRequest requests}, may be empty.
     */
    private User[] getUsersArray() {
        return getUsersArray(null);
    }

    /**
     * Generates an array of {@linkplain HelpRequest requests} from the tree map for any
     * {@linkplain HelpRequest requests} that contain the text specified by containsText.
     * <br>
     * If containsText is null, the array contains all of the {@linkplain HelpRequest requests}
     * in the tree map.
     *
     * @param containsText The text to filter the requests by (if null, no filter).
     * @return The array of {@link HelpRequest requests}, may be empty.
     */
    private User[] getUsersArray(String containsText) {
        ArrayList<User> userArrayList = new ArrayList<>();
        for (User user : users.values()) {
            if (containsText == null || user.getUsername().contains(containsText)) {
                userArrayList.add(user);
            }
        }
        User[] userArray = new User[userArrayList.size()];
        userArrayList.toArray(userArray);
        return userArray;
    }
    /*
    private User[] getLoggedInArray() {
        ArrayList<User> userArrayList = new ArrayList<>();
        for (User user : loggedIn.values()) {
            userArrayList.add(user);
        }
        User[] userArray = new User[userArrayList.size()];
        userArrayList.toArray(userArray);
        return userArray;
    }
    */
    /**
     * Saves the {@linkplain HelpRequest requests} from the map into the file as an array of JSON objects.
     *
     * @return true if the {@link HelpRequest requests} were written successfully.
     *
     * @throws IOException when the file cannot be accessed or written to.
     */
    private boolean save() throws IOException {
        User[] usersArray = getUsersArray();
        //User[] loggedArray = getLoggedInArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will throw an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename), usersArray);
        //objectMapper.writeValue(new File(loggedInFile), loggedArray);
        return true;
    }

    /**
     * Loads {@linkplain HelpRequest requests} from the JSON file into the map.
     * <br>
     * Also sets the next id to one more than the greatest id found in the file.
     *
     * @return true if the file was read successfully.
     *
     * @throws IOException when the file cannot be accessed or read from.
     */
    private boolean load() throws IOException {
        users = new TreeMap<>();
        nextId = 0;
        // Deserializes the JSON objects from the file into an array of requests
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        User[] userArray = objectMapper.readValue(new File(filename), User[].class);
        //User[] loggedArray = objectMapper.readValue(new File(loggedInFile), User[].class);
        // Add each request to the tree map and keep track of the greatest id
        for (User user : userArray) {
            users.put(user.getId(), user);
            if (user.getId() > nextId)
                nextId = user.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    @Override
    public User[] getUsers() throws IOException {
        synchronized (users) {
            return getUsersArray();
        }
    }

        /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(User user) throws IOException {
        synchronized (users) {
            // We create a new request object because the id field is immutable
            // and we request to assign the next unique id
            User newUser = new User(user.getId(), user.getUsername(), user.getPassword());
            users.put(newUser.getId(), newUser);
            save(); // may throw an IOException
            return newUser;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUser(int id) throws IOException {
        synchronized (users) {
            if (users.containsKey(id)) {
                users.remove(id);
                return save();
            } else
                return false;
        }
    }

    @Override
    public User getUser(int id) {
        synchronized (users) {
            if (users.containsKey(id))
                return users.get(id);
            else
                return null;
        }
    }

    
}
