package com.ufund.api.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Represents a "Request" entity that encapsulates information about a specific need.
 * This class is part of the application's model.
 * 
 * @author SWEN Faculty
 */
public class HelpRequest {

    private static final Logger LOG = Logger.getLogger(HelpRequest.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Request [id=%d, title=%s, body=%s, response=%s, completed=%b]"; 

    @JsonProperty("id") private int id;
    @JsonProperty("creator") private int creator;
    @JsonProperty("title") private String title;
    @JsonProperty("body") private String body;
    @JsonProperty("response") private String response;
    @JsonProperty("completed") private boolean completed;
// Have not edited anything past this line.
    /**
     * Constructs a "Need" object with the given parameters.
     * 
     * @param id       The unique identifier for the need.
     * @param name     The name or description of the need.
     * @param cost     The cost associated with the need.
     * @param quantity The quantity or amount required for the need.
     * @param type     The type or category of the need.
     * 
     * @JsonProperty is used in serialization and deserialization of the JSON object 
     * to the Java object, facilitating the mapping of fields.
     * If a field is not provided in the JSON object, the Java field gets the default Java value,
     * i.e., 0 for int.
     */
    public HelpRequest(@JsonProperty("id") int id, @JsonProperty("creator") int creator, @JsonProperty("title") String title, @JsonProperty("body") String body, @JsonProperty("response") String response, @JsonProperty("completed") boolean completed) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.body = body;
        this.response = response;
        this.completed = completed;
    }

    /**
     * Retrieves the unique identifier of the need.
     * 
     * @return The unique identifier of the need.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the need.
     * 
     * @param id The unique identifier for the need.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name or description of the need.
     * Necessary for JSON object to Java object deserialization.
     * 
     * @param name The name or description of the need.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the name or description of the need.
     * 
     * @return The name or description of the need.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the cost associated with the need.
     * 
     * @return The cost associated with the need.
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the cost associated with the need.
     * 
     * @param cost The cost associated with the need.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Retrieves the quantity or amount required for the need.
     * 
     * @return The quantity or amount required for the need.
     */
    public String getReponse() {
        return response;
    }

    /**
     * Sets the quantity or amount required for the need.
     * 
     * @param quantity The quantity or amount required for the need.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Sets the type or category of the need.
     * 
     * @param type The type or category of the need.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Retrieves the type or category of the need.
     * 
     * @return The type or category of the need.
     */
    public boolean getCompleted() {
        return completed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, title, body, response, completed);
    }
    
    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getResponse() {
        return response;
    }
    
}
