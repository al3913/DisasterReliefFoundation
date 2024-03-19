package com.ufund.api.persistence;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.ufund.api.model.FundingBasket;

/**
 * A data access object interface for managing FundingBasket entities.
 */
public interface FundingBasketDAO {
    /**
     * Retrieves a FundingBasket with the given ID.
     * 
     * @param id The ID of the FundingBasket to retrieve.
     * @return A FundingBasket object with the matching ID, or null if not found.
     * @throws IOException if an issue occurs with underlying storage.
     */
    FundingBasket getFundingBasket(int id) throws IOException;

    /**
     * Finds all FundingBaskets whose name contains the given text.
     * 
     * @param containsText The text to match against.
     * @return An array of FundingBaskets whose names contain the given text; may be empty.
     * @throws IOException if an issue occurs with underlying storage.
     */
    FundingBasket updateFundingBasket(FundingBasket fundingBasket) throws IOException;

    /**
     * Creates and saves a new FundingBasket.
     * 
     * @param fundingBasket The FundingBasket object to be created and saved.
     *                      The ID of the fundingBasket object is ignored, and a new unique ID is assigned.
     * @return The newly created FundingBasket if successful, null otherwise.
     * @throws IOException if an issue occurs with underlying storage.
     */
    FundingBasket createFundingBasket(FundingBasket fundingBasket) throws IOException;
}
