package com.ufund.api.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a "Need" entity that encapsulates information about a specific user.
 * This class is part of the application's model.
 * 
 * @author Team
 */
public class User {

    private static final Logger LOG = Logger.getLogger(Need.class.getName());

    // Package private for tests
    public static final String STRING_FORMAT = "Need [id=%s, username=%s, password=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String username;
    @JsonProperty("cost") private String password;

    /**
     * Constructs a "Need" object with the given parameters.
     * 
     * @param id       The unique identifier for the user.
     * @param username     The name or description of the user.
     * @param password     The cost associated with the user.
     * 
     * @JsonProperty is used in serialization and deserialization of the JSON object 
     * to the Java object, facilitating the mapping of fields.
     * If a field is not provided in the JSON object, the Java field gets the default Java value,
     * i.e., 0 for int.
     */
    public User(@JsonProperty("id") int id, @JsonProperty("username") String name,
                @JsonProperty("password") String password) {
        this.id = id;
        this.username = name;
        this.password = password;
    }

    /**
     * Retrieves the unique identifier of the user.
     * 
     * @return The unique identifier of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     * 
     * @param id The unique identifier for the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name or description of the user.
     * Necessary for JSON object to Java object deserialization.
     * 
     * @param username The name or description of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the name or description of the user.
     * 
     * @return The name or description of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the user.
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * Necessary for JSON object to Java object deserialization.
     * 
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, username, password);
    }


 
}
