package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.model.User;
import com.ufund.api.persistence.UsersFileDAO;

@Tag("Persistence-tier")
public class UsersFileDAOTest {
    private UsersFileDAO usersFileDAO;
    private ObjectMapper mockObjectMapper;
    private User[] testUsers;
    // testDeleteUser, testDeleteUserNotFound, testUpdateUser, testUserNotFound
    // Untested -> certain methods like delete and get user still use Id as an input
    // Even though our mapping is based on String to User
    
    @BeforeEach
    public void setup() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new User[]{
            new User(1, "Alice", "password123"),
            new User(2, "Bob", "password456"),
            new User(3, "Charlie", "password789")
        };

        when(mockObjectMapper.readValue(new File("users.json"), User[].class))
            .thenReturn(testUsers);

        usersFileDAO = new UsersFileDAO("users.json", "currentUser.json", mockObjectMapper);
    }

    @Test
    public void testGetUsers() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        User[] users = usersFileDAO.getUsers();

        assertEquals(testUsers.length, users.length);
        assertTrue(Arrays.asList(users).containsAll(Arrays.asList(testUsers)));
    }

    @Test
    public void testCreateUser() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        User newUser = new User(4, "David", "password000");
        User createdUser = assertDoesNotThrow(() -> usersFileDAO.createUser(newUser));

        assertNotNull(createdUser);
        assertEquals(newUser.getUsername(), createdUser.getUsername());
    }

    // @Test
    // public void testDeleteUser() throws IOException {
    //     try {
    //         setup();
    //     }
    //     catch (IOException e){
    //         System.out.println("fucked up");
    //     };
    //     boolean result = assertDoesNotThrow(() -> usersFileDAO.deleteUser(1));
    //     assertTrue(result);
    // }

    @Test
    public void testGetUserByUsername() {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        User user = usersFileDAO.getUser("Alice");
        assertEquals("Alice", user.getUsername());
    }

    // @Test
    // public void testUpdateUser() throws IOException {
    //     try {
    //         setup();
    //     }
    //     catch (IOException e){
    //         System.out.println("fucked up");
    //     };
    //     User updatedUser = new User(1, "Alice", "newpassword123");
    //     User result = assertDoesNotThrow(() -> usersFileDAO.updateUser(updatedUser));

    //     assertNotNull(result);
    //     assertEquals(updatedUser.getPassword(), result.getPassword());
    // }

    @Test
    public void testSaveException() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        doThrow(new IOException()).when(mockObjectMapper).writeValue(any(File.class), any(User[].class));

        assertThrows(IOException.class, () -> usersFileDAO.createUser(new User(4, "Eve", "password1010")), "IOException not thrown");
    }

    @Test
    public void testLoadException() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        doThrow(new IOException()).when(mockObjectMapper).readValue(any(File.class), eq(User[].class));

        assertThrows(IOException.class, () -> new UsersFileDAO("users.json", "currentUser.json", mockObjectMapper), "IOException not thrown");
    }

    // @Test
    // public void testUserNotFound() {
    //     try {
    //         setup();
    //     }
    //     catch (IOException e){
    //         System.out.println("fucked up");
    //     };
    //     assertNull(usersFileDAO.getUser(99));
    // }

    // @Test
    // public void testDeleteUserNotFound() throws IOException {
    //     try {
    //         setup();
    //     }
    //     catch (IOException e){
    //         System.out.println("fucked up");
    //     };
    //     assertFalse(usersFileDAO.deleteUser(99));
    // }

    @Test
    public void testLogout() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        assertDoesNotThrow(() -> usersFileDAO.logout());
    }
}