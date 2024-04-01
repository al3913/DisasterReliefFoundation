package com.ufund.api.persistence;
import java.io.IOException;

import com.ufund.api.model.HelpRequest;
import com.ufund.api.model.Need;
import com.ufund.api.model.User;

/**
 * Defines the interface for Need object persistence
 * 
 * @author Team
 */
public interface UsersDAO {
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
    User createUser(User user) throws IOException;

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
    boolean deleteUser(int id) throws IOException;



    User getUser(int id) throws IOException;

    User getUser(String username) throws IOException;

    User[] getUsers() throws IOException;

    User getCurrentUser() throws IOException;



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
    User updateUser(User user) throws IOException;

    void logout() throws IOException;
}
