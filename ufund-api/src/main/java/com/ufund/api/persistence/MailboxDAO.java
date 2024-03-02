package com.ufund.api.persistence;
import java.io.IOException;
import com.ufund.api.model.HelpRequest;

/**
 * Defines the interface for Need object persistence
 * 
 * @author SWEN Faculty
 */
public interface MailboxDAO {
    /**
     * Retrieves all {@linkplain Need needs}
     * 
     * @return An array of {@link Need need} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest[] getRequests() throws IOException;

    /**
     * Finds all {@linkplain Need needs} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Need needs} whose names contain the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest[] findRequests(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need} to get
     * 
     * @return a {@link Need need} object with the matching id
     * <br>
     * null if no {@link Need need} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest[] findMyRequests(int userID) throws IOException;

    /**
     * Retrieves a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need} to get
     * 
     * @return a {@link Need need} object with the matching id
     * <br>
     * null if no {@link Need need} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */

    boolean findCompleted(boolean completedStatus) throws IOException;

    /**
     * Retrieves a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need} to get
     * 
     * @return a {@link Need need} object with the matching id
     * <br>
     * null if no {@link Need need} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest getRequest(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain Need need}
     * 
     * @param need {@linkplain Need need} object to be created and saved
     * <br>
     * The id of the need object is ignored, and a new unique id is assigned
     *
     * @return new {@link Need need} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    HelpRequest createRequest(HelpRequest request) throws IOException;

    /**
     * Updates and saves a {@linkplain Need need}
     * 
     * @param need {@link Need need} object to be updated and saved
     * 
     * @return updated {@link Need need} if successful, null if
     * {@link Need need} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    HelpRequest updateRequest(HelpRequest request) throws IOException;

    /**
     * Deletes a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need}
     * 
     * @return true if the {@link Need need} was deleted
     * <br>
     * false if the need with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteRequest(int id) throws IOException;
}
