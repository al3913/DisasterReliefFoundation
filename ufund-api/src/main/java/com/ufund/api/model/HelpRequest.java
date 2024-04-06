package com.ufund.api.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a "Request" entity that encapsulates information about a specific request.
 * This class is part of the application's model.
 * 
 * @author Team
 */
public class HelpRequest {

    private static final Logger LOG = Logger.getLogger(HelpRequest.class.getName());

    // Package private for tests
    public static final String STRING_FORMAT = "Request [id=%d, creator=%s, body=%s]"; 

    @JsonProperty("id") private int id;
    @JsonProperty("creator") private String creator;
    @JsonProperty("body") private String body;

    /**
     * Constructs a "Request" object with the given parameters.
     * 
     * @param id         The unique identifier for the request.
     * @param creator    The creator ID of the request.
     * @param title      The title of the request.
     * @param body       The body of the request.
     * @param response   The response to the request.
     * @param completed  The completion status of the request. 
     * 
     * @JsonProperty is used in serialization and deserialization of the JSON object 
     * to the Java object, facilitating the mapping of fields.
     * If a field is not provided in the JSON object, the Java field gets the default Java value,
     * i.e., 0 for int.
     */
    public HelpRequest(@JsonProperty("id") int id, @JsonProperty("creator") String creator, @JsonProperty("body") String body) {
        this.id = id;
        this.creator = creator;
        this.body = body;

    }

    /**
     * Retrieves the unique identifier of the request.
     * 
     * @return The unique identifier of the request.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the request.
     * 
     * @param id The unique identifier for the request.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the unique identifier of the request's creator.
     * 
     * @return The unique identifier of the request's creator.
     */

    public String getCreator() {
        return creator;
    }

    /**
     * Sets the unique identifier of the request's creator.
     * Necessary for JSON object to Java object deserialization.
     * 
     * @param creator The unique identifier of the request's creator.
     */

    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Retrieves the name or description of the request.
     * 
     * @return The name or description of the request.
     */


    /**
     * Sets the name or description of the request.
     * Necessary for JSON object to Java object deserialization.
     * 
     * @param title The name or description of the request.
     */


    /**
     * Retrieves the body of the request.
     * 
     * @return The body of the request.
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body of the request.
     * 
     * @param body The body of the request.
     */
    public void setBody(String body) {
        this.body = body;
    }




    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, creator, body);
    }
}
