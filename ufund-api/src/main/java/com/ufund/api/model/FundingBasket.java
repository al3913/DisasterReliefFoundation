package com.ufund.api.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a FundingBasket entity.
 * 
 * This class represents a funding basket, which contains information about needs and bought items.
 * 
 * @author Andy Lin
 */
public class FundingBasket {
    private static final Logger LOG = Logger.getLogger(FundingBasket.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "FundingBasket[id=%d, needs=%s]";

    @JsonProperty("id")
    private int id;

    @JsonProperty("needs")
    private Need[] needs;

    @JsonProperty("bought")
    private Need[] bought;

    /**
     * Creates a FundingBasket with the given id, needs, and bought items.
     * 
     * @param id     The id of the FundingBasket.
     * @param needs  The array of needs associated with the FundingBasket.
     * @param bought The array of bought items associated with the FundingBasket.
     * 
     *               The {@literal @}JsonProperty annotation is used in serialization and deserialization
     *               of the JSON object to the Java object for mapping the fields. If a field
     *               is not provided in the JSON object, the Java field gets the default Java
     *               value, i.e., 0 for int.
     */
    public FundingBasket(@JsonProperty("id") int id, @JsonProperty("needs") Need[] needs,
            @JsonProperty("bought") Need[] bought) {
        this.id = id;
        this.needs = needs;
        this.bought = bought;
    }

    /**
     * Retrieves the id of the FundingBasket.
     * 
     * @return The id of the FundingBasket.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the array of needs associated with the FundingBasket.
     * 
     * @return The array of needs associated with the FundingBasket.
     */
    public Need[] getNeeds() {
        return needs;
    }

    /**
     * Sets the array of needs associated with the FundingBasket.
     * 
     * @param needs The array of needs to set.
     */
    public void setNeeds(Need[] needs) {
        this.needs = needs;
    }

    /**
     * Retrieves the array of bought items associated with the FundingBasket.
     * 
     * @return The array of bought items associated with the FundingBasket.
     */
    public Need[] getBought() {
        return bought;
    }

    /**
     * Sets the array of bought items associated with the FundingBasket.
     * 
     * @param bought The array of bought items to set.
     */
    public void setBought(Need[] bought) {
        this.bought = bought;
    }

    /**
     * Returns a string representation of the FundingBasket.
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, needs);
    }
}
