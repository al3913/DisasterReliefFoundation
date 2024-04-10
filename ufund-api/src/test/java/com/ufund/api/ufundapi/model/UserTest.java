package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import com.ufund.api.model.User;
import com.ufund.api.model.Need;;;

/**
 * Test the HelpRequest Class
 * 
 * @author Andy Lin
 * 
 */
@Tag("Model-tier")
public class UserTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);

        // Analyze
        assertEquals(expected_id,user.getId());
        assertEquals(expected_name, user.getUsername());
        assertEquals(expected_password,user.getPassword());
    }

    @Test
    public void testGetId() {
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);

        // Analyze
        assertEquals(expected_id,user.getId());
    }

    @Test
    public void testGetUsername() {
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);

        // Analyze
        assertEquals(expected_name, user.getUsername());
    }


    @Test
    public void testGetPassword() {
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);

        // Analyze
        assertEquals(expected_password,user.getPassword());
    }

    @Test
    public void testGetBasketEmpty() {
        // Setup
        int expected_id = 1;
        String expected_name = "name";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);

        // Analyze
        assertEquals(user.getBasket().length, 0);
    }

    @Test
    public void testGetBasketWithNeeds() {
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);
        user.addToBasket(new Need(expected_id, expected_name, expected_id, expected_id, expected_password));

        // Analyze
        assertEquals(user.getBasket().length, 1);
    }

    @Test
    public void testGetTotal(){
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);
        // Analyze
        assertEquals(user.getTotal(), 0);
    }

    @Test
    public void testSetUsername() {
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, "expected_name", expected_password);
        user.setUsername(expected_name);

        // Analyze
        assertEquals(expected_name, user.getUsername());
    }


    @Test
    public void testSetPassword() {
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, "expected_password");
        user.setPassword(expected_password);

        // Analyze
        assertEquals(expected_password,user.getPassword());
    }

    @Test
    public void testSetTotal(){
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";
        int expected_total = 2;

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);
        user.setTotal(expected_total);
        // Analyze
        assertEquals(user.getTotal(), expected_total);
    }

    @Test
    public void testAddToBasket(){
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);
        Need need = new Need(expected_id, expected_name, expected_id, expected_id, expected_password);
        user.addToBasket(need);
        // Analyze
        assertEquals(user.getBasket()[0], need);
    }

    @Test
    public void testRemoveFromBasketNeed(){
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);
        Need need = new Need(expected_id, expected_name, expected_id, expected_id, expected_password);
        user.addToBasket(need);
        user.removeFromBasket(need);
        // Analyze
        assertEquals(user.getBasket().length, 0);
    }

    @Test
    public void testRemoveFromBasketId(){
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);
        Need need = new Need(2, expected_name, expected_id, expected_id, expected_password);
        user.addToBasket(need);
        user.removeFromBasket(need.getId());
        // Analyze
        assertEquals(user.getBasket().length, 0);
    }

    @Test
    public void testBasketCheckout(){
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);
        Need need = new Need(2, expected_name, expected_id, expected_id, expected_password);
        user.addToBasket(need);
        user.basketCheckout();
        // Analyze
        assertEquals(user.getBasket().length, 0);
    }

    @Test
    public void testToString(){
        // Setup
        int expected_id = 1;
        String expected_name = "admin";
        String expected_password = "password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);
        String expected_string = String.format(User.STRING_FORMAT, expected_id, expected_name, expected_password);

        // Analyze
        assertEquals(expected_string, user.toString());
    }
}