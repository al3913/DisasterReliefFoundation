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

/**
 * Implements the functionality for JSON file-based persistence for Need objects.
 * The class provides methods to interact with a local cache of Need objects stored in a JSON file.
 *
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed.
 *
 * @author SWEN Faculty
 */
@Component
public class MailboxFileDAO implements MailboxDAO {

    private static final Logger LOG = Logger.getLogger(MailboxFileDAO.class.getName());
    
    Map<Integer, HelpRequest> requests;   // Provides a local cache of the Need objects
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
    public MailboxFileDAO(@Value("${mailbox.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the needs from the file
    }

    /**
     * Generates the next id for a new {@linkplain Need need}.
     *
     * @return The next id.
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Need needs} from the tree map.
     *
     * @return The array of {@link Need needs}, may be empty.
     */
    private HelpRequest[] getRequestsArray() {
        return getRequestsArray(null);
    }

    /**
     * Generates an array of {@linkplain Need needs} from the tree map for any
     * {@linkplain Need needs} that contain the text specified by containsText.
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Need needs}
     * in the tree map.
     *
     * @param containsText The text to filter the needs by (if null, no filter).
     * @return The array of {@link Need needs}, may be empty.
     */
    private HelpRequest[] getRequestsArray(String containsText) {
        ArrayList<HelpRequest> requestArrayList = new ArrayList<>();
        for (HelpRequest request : requests.values()) {
            if (containsText == null || request.getTitle().contains(containsText) || request.getBody().contains(containsText)) {
                requestArrayList.add(request);
                System.out.print(request);
            }
        }
        HelpRequest[] requestArray = new HelpRequest[requestArrayList.size()];
        requestArrayList.toArray(requestArray);
        return requestArray;
    }

    /**
     * Saves the {@linkplain Need needs} from the map into the file as an array of JSON objects.
     *
     * @return true if the {@link Need needs} were written successfully.
     *
     * @throws IOException when the file cannot be accessed or written to.
     */
    private boolean save() throws IOException {
        HelpRequest[] requestArray = getRequestsArray();
        // Serializes the Java Objects to JSON objects into the file
        // writeValue will throw an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename), requestArray);
        return true;
    }

    /**
     * Loads {@linkplain Need needs} from the JSON file into the map.
     * <br>
     * Also sets the next id to one more than the greatest id found in the file.
     *
     * @return true if the file was read successfully.
     *
     * @throws IOException when the file cannot be accessed or read from.
     */
    private boolean load() throws IOException {
        requests = new TreeMap<>();
        nextId = 0;
        // Deserializes the JSON objects from the file into an array of needs
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        HelpRequest[] requestArray = objectMapper.readValue(new File(filename), HelpRequest[].class);
        // Add each need to the tree map and keep track of the greatest id
        for (HelpRequest request : requestArray) {
            requests.put(request.getId(), request);
            if (request.getId() > nextId)
                nextId = request.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpRequest[] getRequests() {
        synchronized (requests) {
            return getRequestsArray();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpRequest[] findRequests(String containsText) {
        synchronized (requests) {
            return getRequestsArray(containsText);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpRequest getRequest(int id) { //May not be necessary
        synchronized (requests) {
            if (requests.containsKey(id))
                return requests.get(id);
            else
                return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpRequest createRequest(HelpRequest request) throws IOException {
        synchronized (requests) {
            // We create a new need object because the id field is immutable
            // and we need to assign the next unique id
            HelpRequest newRequest = new HelpRequest(request.getId(), request.getCreator(), request.getTitle(), request.getBody(), request.getResponse(), request.getCompleted());
            requests.put(newRequest.getId(), newRequest);
            save(); // may throw an IOException
            return newRequest;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpRequest updateRequest(HelpRequest request) throws IOException {
        synchronized (requests) {
            if (!requests.containsKey(request.getId()))
                return null;  // need does not exist
            requests.put(request.getId(), request);
            save(); // may throw an IOException
            return request;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteRequest(int id) throws IOException {
        synchronized (requests) {
            if (requests.containsKey(id)) {
                requests.remove(id);
                return save();
            } else
                return false;
        }
    }

    @Override
    public HelpRequest[] findMyRequests(int userID) throws IOException {
        synchronized (requests) {
            ArrayList<HelpRequest> requestArrayList = new ArrayList<>();
            for(HelpRequest request : requests.values()) {
                if ((request.getCreator() == userID))
                    requests.put(request.getId(), request);
                    requestArrayList.add(request);
            }
            HelpRequest[] requestArray = new HelpRequest[requestArrayList.size()];
            requestArrayList.toArray(requestArray);
            save(); // may throw an IOException
            return requestArray;
        }
    }

    @Override
    public boolean findCompleted(boolean completedStatus) throws IOException {
        synchronized (requests) {
            for(HelpRequest request : requests.values()) {
                if ((request.getCompleted() == completedStatus))
                    requests.put(request.getId(), request);
            }
            save(); // may throw an IOException
            return completedStatus;
        }
    }
}
