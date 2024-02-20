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
import com.ufund.api.model.Need;
/**
 * Implements the functionality for JSON file-based peristance for Heroes
 *
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 *
 * @author SWEN Faculty
 */
@Component
public class CupboardFileDAO implements CupboardDAO {
    private static final Logger LOG = Logger.getLogger(CupboardFileDAO.class.getName());
    Map<Integer,Need> needs;   // Provides a local cache of the hero objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Hero
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new hero
    private String filename;    // Filename to read from and write to
    /**
     * Creates a Need File Data Access Object
     *
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     *
     * @throws IOException when file cannot be accessed or read from
     */
    public CupboardFileDAO(@Value("${cupboard.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the heroes from the file
    }
    /**
     * Generates the next id for a new {@linkplain Hero hero}
     *
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }
    /**
     * Generates an array of {@linkplain Hero heroes} from the tree map
     *
     * @return  The array of {@link Hero heroes}, may be empty
     */
    private Need[] getNeedsArray() {
        return getNeedsArray(null);
    }
    /**
     * Generates an array of {@linkplain Hero heroes} from the tree map for any
     * {@linkplain Hero heroes} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Hero heroes}
     * in the tree map
     *
     * @return  The array of {@link Hero heroes}, may be empty
     */
    private Need[] getNeedsArray(String containsText) { // if containsText == null, no filter
        ArrayList<Need> needArrayList = new ArrayList<>();
        for (Need need : needs.values()) {
            if (containsText == null || need.getName().contains(containsText)) {
                needArrayList.add(need);
            }
        }
        Need[] needArray = new Need[needArrayList.size()];
        needArrayList.toArray(needArray);
        return needArray;
    }
    /**
     * Saves the {@linkplain Hero heroes} from the map into the file as an array of JSON objects
     *
     * @return true if the {@link Hero heroes} were written successfully
     *
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Need[] needArray = getNeedsArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename), needArray);
        return true;
    }
    /**
     * Loads {@linkplain Hero heroes} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     *
     * @return true if the file was read successfully
     *
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        needs = new TreeMap<>();
        nextId = 0;
        // Deserializes the JSON objects from the file into an array of heroes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Need[] needArray = objectMapper.readValue(new File(filename),Need[].class);
        // Add each hero to the tree map and keep track of the greatest id
        for (Need need : needArray) {
            needs.put(need.getId(),need);
            if (need.getId() > nextId)
                nextId = need.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }
    /**
    ** {@inheritDoc}
     */
    @Override
    public Need[] getNeeds() {
        synchronized(needs) {
            return getNeedsArray();
        }
    }
    /**
    ** {@inheritDoc}
     */
    @Override
    public Need[] findNeeds(String containsText) {
        synchronized(needs) {
            return getNeedsArray(containsText);
        }
    }
    /**
    ** {@inheritDoc}
     */
    @Override
    public Need getNeed(int id) {
        synchronized(needs) {
            if (needs.containsKey(id))
                return needs.get(id);
            else
                return null;
        }
    }
    /**
    ** {@inheritDoc}
     */
    @Override
    public Need createNeed(Need need) throws IOException {
        synchronized(needs) {
            // We create a new hero object because the id field is immutable
            // and we need to assign the next unique id
            Need newNeed = new Need(nextId(),need.getName(),need.getCost(),need.getQuantity(),need.getType()); // Need to add the rest of the inputs needed for a new Need
            needs.put(newNeed.getId(),newNeed);
            save(); // may throw an IOException
            return newNeed;
        }
    }
    /**
    ** {@inheritDoc}
     */
    @Override
    public Need updateNeed(Need need) throws IOException {
        synchronized(needs) {
            if (needs.containsKey(need.getId()) == false)
                return null;  // hero does not exist
            needs.put(need.getId(),need);
            save(); // may throw an IOException
            return need;
        }
    }
    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteNeed(int id) throws IOException {
        synchronized(needs) {
            if (needs.containsKey(id)) {
                needs.remove(id);
                return save();
            }
            else
                return false;
        }
    }
}