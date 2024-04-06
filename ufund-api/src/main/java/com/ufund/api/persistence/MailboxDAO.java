package com.ufund.api.persistence;
import java.io.IOException;
import com.ufund.api.model.HelpRequest;

/**
 * Defines the interface for request object persistence
 * 
 * @author Matthew Peck
 */
public interface MailboxDAO {
    /**
     * Retrieves all {@linkplain HelpRequest requests}
     * 
     * @return An array of {@link HelpRequest request} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest[] getRequests() throws IOException;

    /**
     * Finds all {@linkplain HelpRequest requests} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link HelpRequest requests} whose names contain the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest[] findRequests(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain HelpRequest request} with the given id
     * 
     * @param id The id of the {@link HelpRequest request} to get
     * 
     * @return a {@link HelpRequest request} object with the matching id
     * <br>
     * null if no {@link HelpRequest request} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest[] findMyRequests(int userID) throws IOException;


    /**
     * Retrieves a {@linkplain HelpRequest request} with the given id
     * 
     * @param id The id of the {@link HelpRequest request} to get
     * 
     * @return a {@link HelpRequest request} object with the matching id
     * <br>
     * null if no {@link HelpRequest request} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest getRequest(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain HelpRequest request}
     * 
     * @param request {@linkplain HelpRequest request} object to be created and saved
     * <br>
     * The id of the request object is ignored, and a new unique id is assigned
     *
     * @return new {@link HelpRequest request} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest createRequest(HelpRequest request) throws IOException;

    /**
     * Updates and saves a {@linkplain HelpRequest request}
     * 
     * @param request {@link HelpRequest request} object to be updated and saved
     * 
     * @return updated {@link HelpRequest request} if successful, null if
     * {@link HelpRequest request} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    HelpRequest updateRequest(HelpRequest request) throws IOException;

    /**
     * Deletes a {@linkplain HelpRequest request} with the given id
     * 
     * @param id The id of the {@link HelpRequest request}
     * 
     * @return true if the {@link HelpRequest request} was deleted
     * <br>
     * false if the request with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteRequest(int id) throws IOException;
}
