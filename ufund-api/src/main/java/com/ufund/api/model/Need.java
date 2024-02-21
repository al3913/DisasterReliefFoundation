package com.ufund.api.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a Hero entity
 * 
 * @author SWEN Faculty
 */
public class Need {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Need [id=%d, name=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("cost") private int cost;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("type") private String type;


    /**
     * Create a hero with the given id and name
     * @param id The id of the hero
     * @param name The name of the hero
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Need(@JsonProperty("id") int id,@JsonProperty("name") String name,@JsonProperty("cost") int cost,@JsonProperty("quantity") int quantity,@JsonProperty("type") String type) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
    }

    /**
     * Retrieves the id of the hero
     * @return The id of the hero
     */
    public int getId() {return id;}

    public void setId(int id){this.id =id;}

    /**
     * Sets the name of the hero - necessary for JSON object to Java object deserialization
     * @param name The name of the hero
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the name of the hero
     * @return The name of the hero
     */


    public String getName() {return name;}

    /**
     * {@inheritDoc}
     */
    public int getCost() {return cost;}

    public void setCost(int cost){this.cost = cost;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void setType(String type) {this.type = type;}

    public String getType() {return type;}

    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,cost,quantity,type);
    }
    
}
