package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;

import com.ufund.api.model.Need;
import com.ufund.api.model.User;
import com.ufund.api.persistence.UsersDAO;
import com.ufund.api.controller.UsersController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Test the Mailbox Controller Class
 * 
 * @author Naif Alanazi, Matthew Peck, Andy Lin
 * 
 */
@Tag("Controller-tier")
@ExtendWith(SpringExtension.class)
public class UsersControllerTest {

    @Mock
    private UsersDAO mockUsersDAO;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void setUp() {
        mockUsersDAO = mock(UsersDAO.class);
        usersController = new UsersController(mockUsersDAO);
    }

    @Test
    public void testGetUsers() throws IOException { // findNeeds may throw IOException
        // Setup
        setUp();
        User[] users = new User[2];
        users[0] = new User(1,"admin","admin");
        users[1] = new User(2,"helper","helper");
        // When findMyRequests is called, return the two
        /// heroes above
        when(mockUsersDAO.getUsers()).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = usersController.getUsers();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
    }

    @Test
    public void testGetUsersHandleException() throws Exception {
        //Setup
        setUp();
        // When getNeed is called on the mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).getUsers();
        // Invoke
        ResponseEntity<User[]> response = usersController.getUsers();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetCurrentUsers() throws IOException { // findNeeds may throw IOException
        // Setup
        setUp();
        User currentUser = new User(1,"admin","admin");
        // When findMyRequests is called, return the two
        /// heroes above
        when(mockUsersDAO.getCurrentUser()).thenReturn(currentUser);

        // Invoke
        ResponseEntity<User> response = usersController.getCurrentUsers();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(currentUser,response.getBody());
    }

    @Test
    public void testGetCurrentUsersHandleException() throws Exception {
        //Setup
        setUp();
        // When getNeed is called on the mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).getCurrentUser();
        // Invoke
        ResponseEntity<User> response = usersController.getCurrentUsers();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetUserBasket() throws IOException { // findNeeds may throw IOException
        // Setup
        setUp();
        User user = new User(1,"admin","admin");
        user.addToBasket(new Need(0, "null", 0, 0, "null"));
        Need[] basket = user.getBasket();

        when(mockUsersDAO.getUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testGetUserBasketHandleException() throws Exception {
        //Setup
        setUp();
        User user = new User(1,"admin","admin");
        // When getNeed is called on the mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).getUser(user.getUsername());
        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetUserTotal() throws IOException { // findNeeds may throw IOException
        // Setup
        setUp();
        User user = new User(1,"admin","admin");

        when(mockUsersDAO.getUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testGetUserTotalHandleException() throws Exception {
        //Setup
        setUp();
        User user = new User(1,"admin","admin");
        // When getNeed is called on the mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).getUser(user.getUsername());
        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    
    @Test
    public void testAddToBasket() throws IOException { // findNeeds may throw IOException
        // Setup
        setUp();
        User user = new User(1,"admin","admin");

        when(mockUsersDAO.getUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testAddToBasketHandleException() throws Exception {
        //Setup
        setUp();
        User user = new User(1,"admin","admin");
        // When getNeed is called on the mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).getUser(user.getUsername());
        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    
    @Test
    public void testRemoveFromBasket() throws IOException { // findNeeds may throw IOException
        // Setup
        setUp();
        User user = new User(1,"admin","admin");

        when(mockUsersDAO.getUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testRemoveFromBasketHandleException() throws Exception {
        //Setup
        setUp();
        User user = new User(1,"admin","admin");
        // When getNeed is called on the mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).getUser(user.getUsername());
        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    
    @Test
    public void testCheckout() throws IOException { // findNeeds may throw IOException
        // Setup
        setUp();
        User user = new User(1,"admin","admin");

        when(mockUsersDAO.getUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testCheckoutHandleException() throws Exception {
        //Setup
        setUp();
        User user = new User(1,"admin","admin");
        // When getNeed is called on the mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).getUser(user.getUsername());
        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetUser() throws IOException {
        //Setup
        setUp();
        User user = new User(1,"admin","admin");
        // When the same id is passed in, our mock NeedDAO will return the Need object
        when(mockUsersDAO.getUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = usersController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());

    }  

    
    @Test
    public void testGetUserNotFound() throws Exception {
        //Setup
        setUp();
        String username = "";
        // When the same id is passed in, our mock NeedDAO will return null,
        // simulating no need found
        when(mockUsersDAO.getUser(username)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = usersController.getUser(username);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetUserHandleException() throws Exception {
        //Setup
        setUp();
        String username = "";
        // When getNeed is called on the mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).getUser(username);
        // Invoke
        ResponseEntity<User> response = usersController.getUser(username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testCreateUserSuccess() throws IOException {
        // Setup
        setUp();
        User user = new User(1,"admin","admin");        // when createRequest is called, return true simulating success
        when(mockUsersDAO.createUser(user)).thenReturn(user);

        //Invoke
        ResponseEntity<User> response = usersController.createUser(user);
        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(user,response.getBody());

    }

    @Test
    public void testCreateUserFailed() throws IOException {  // createNeed may throw IOException
        // Setup
        setUp();
        User user = new User(1,"admin","admin");
        // when createRequest is called, return true simulating success
        when(mockUsersDAO.createUser(user)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = usersController.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void testCreateUserHandleException() throws IOException {  // createNeed may throw IOException
        // Setup
        setUp();
        User user = new User(1,"admin","admin");

        // When createNeed is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).createUser(user);

        // Invoke
        ResponseEntity<User> response = usersController.createUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteUser() throws IOException { // deleteNeed may throw IOException
        // Setup
        setUp();
        int userId = 99;
        // when deleteHero is called return true, simulating successful deletion
        when(mockUsersDAO.deleteUser(userId)).thenReturn(true);

        // Invoke
        ResponseEntity<User> response = usersController.deleteUser(userId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteUserNotFound() throws IOException { // deleteNeed may throw IOException
        // Setup
        setUp();
        int userId = 99;
        // when deleteHero is called return true, simulating successful deletion
        when(mockUsersDAO.deleteUser(userId)).thenReturn(false);

        // Invoke
        ResponseEntity<User> response = usersController.deleteUser(userId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteUserHandleException() throws IOException { // deleteNeed may throw IOException
        // Setup
        setUp();
        int userId = 99;
        // When deleteNeed is called on the Mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockUsersDAO).deleteUser(userId);

        // Invoke
        ResponseEntity<User> response = usersController.deleteUser(userId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
    @Test
    public void testIsNewUser() throws IOException { // deleteNeed may throw IOException
        // Setup
        setUp();
        String username = "name";
        // when deleteHero is called return true, simulating successful deletion
        when(mockUsersDAO.isNewUser(username)).thenReturn(true);

        // Invoke
        ResponseEntity<Boolean> response = usersController.isNewUser(username);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
