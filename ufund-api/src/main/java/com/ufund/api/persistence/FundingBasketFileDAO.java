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
import com.ufund.api.model.FundingBasket;

@Component
public class FundingBasketFileDAO implements FundingBasketDAO {

    private static final Logger LOG = Logger.getLogger(FundingBasketFileDAO.class.getName());
    
    private Map<Integer, FundingBasket> fundingBaskets; // Provides a local cache of the FundingBasket objects
                                                         // so that we don't need to read from the file
                                                         // each time
    private ObjectMapper objectMapper; // Provides conversion between FundingBasket
                                         // objects and JSON text format written
                                         // to the file
    private String filename; // Filename to read from and write to

    /**
     * Creates a FundingBasket File Data Access Object.
     *
     * @param filename     Filename to read from and write to.
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization.
     *
     * @throws IOException when the file cannot be accessed or read from.
     */
    public FundingBasketFileDAO(@Value("${fundingbasket.file}") String filename, ObjectMapper objectMapper)
            throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load(); // load the FundingBaskets from the file
    }

    private FundingBasket[] getFundingBasketsArray() {
        return getFundingBasketsArray(0);
    }

    /**
     * Generates an array of FundingBaskets from the tree map for any
     * FundingBaskets that contain the ID specified by id.
     * <br>
     * If id is 0, the array contains all of the FundingBaskets in the tree map.
     *
     * @param id The ID to filter the FundingBaskets by (if 0, no filter).
     * @return The array of FundingBaskets, may be empty.
     */
    private FundingBasket[] getFundingBasketsArray(int id) {
        ArrayList<FundingBasket> fundingBasketArrayList = new ArrayList<>();
        for (FundingBasket basket : fundingBaskets.values()) {
            if (id == 0 || basket.getId() == id) {
                fundingBasketArrayList.add(basket);
            }
        }
        FundingBasket[] fundingBasketArray = new FundingBasket[fundingBasketArrayList.size()];
        fundingBasketArrayList.toArray(fundingBasketArray);
        return fundingBasketArray;
    }

    /**
     * Saves the FundingBaskets from the map into the file as an array of JSON objects.
     *
     * @return true if the FundingBaskets were written successfully.
     *
     * @throws IOException when the file cannot be accessed or written to.
     */
    private boolean save() throws IOException {
        FundingBasket[] fundingBasketArray = getFundingBasketsArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will throw an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename), fundingBasketArray);
        return true;
    }

    /**
     * Loads FundingBaskets from the JSON file into the map.
     * <br>
     * Also sets the next ID to one more than the greatest ID found in the file.
     *
     * @return true if the file was read successfully.
     *
     * @throws IOException when the file cannot be accessed or read from.
     */
    private boolean load() throws IOException {
        fundingBaskets = new TreeMap<>();
        // Deserializes the JSON objects from the file into an array of FundingBaskets
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        FundingBasket[] fundingBasketArray = objectMapper.readValue(new File(filename), FundingBasket[].class);
        // Add each FundingBasket to the tree map and keep track of the greatest ID
        for (FundingBasket basket : fundingBasketArray) {
            fundingBaskets.put(basket.getId(), basket);
        }
        // Make the next ID one greater than the maximum from the file
        return true;
    }

    @Override
    public FundingBasket getFundingBasket(int id) {
        synchronized (fundingBaskets) {
            return fundingBaskets.getOrDefault(id, null);
        }
    }

    @Override
    public FundingBasket createFundingBasket(FundingBasket basket) throws IOException {
        synchronized (fundingBaskets) {
            // We create a new FundingBasket object because the ID field is immutable
            // and we need to assign the next unique ID
            FundingBasket newBasket = new FundingBasket(basket.getId(), basket.getNeeds(), basket.getBought());
            fundingBaskets.put(newBasket.getId(), newBasket);
            save(); // may throw an IOException
            return newBasket;
        }
    }

    @Override
    public FundingBasket updateFundingBasket(FundingBasket basket) throws IOException {
        synchronized (fundingBaskets) {
            if (!fundingBaskets.containsKey(basket.getId()))
                return null; // FundingBasket does not exist
            fundingBaskets.put(basket.getId(), basket);
            save(); // may throw an IOException
            return basket;
        }
    }
}
