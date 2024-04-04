package com.ufund.api.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a "Need" entity that encapsulates information about a specific need.
 * This class is part of the application's model.
 * 
 * @author Team
 */
public class Need {

    private static final Logger LOG = Logger.getLogger(Need.class.getName());

    // Package private for tests
    public static final String STRING_FORMAT = "id:%d, name:%s, cost:%d, quantity:%d, type:%s";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("cost") private int cost;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("type") private String type;

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
    public Need(@JsonProperty("id") int id, @JsonProperty("name") String name,
                @JsonProperty("cost") int cost, @JsonProperty("quantity") int quantity,
                @JsonProperty("type") String type) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
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
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name or description of the need.
     * 
     * @return The name or description of the need.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the cost associated with the need.
     * 
     * @return The cost associated with the need.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the cost associated with the need.
     * 
     * @param cost The cost associated with the need.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Retrieves the quantity or amount required for the need.
     * 
     * @return The quantity or amount required for the need.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity or amount required for the need.
     * 
     * @param quantity The quantity or amount required for the need.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the type or category of the need.
     * 
     * @param type The type or category of the need.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the type or category of the need.
     * 
     * @return The type or category of the need.
     */
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, name, cost, quantity, type);
    }

    @Override
    public boolean equals(Object other){
        Need otherNeed = (Need) other;
        return this.id == otherNeed.getId() && this.name.equals(otherNeed.getName()) && this.cost == otherNeed.getCost()
        && this.quantity == otherNeed.getQuantity() && this.type.equals(otherNeed.getType());
    }


}
