package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.model.HelpRequest;

/**
 * Test the HelpRequest Class
 * 
 * @author Andy Lin
 * 
 */
@Tag("Model-tier")
public class HelpRequestTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_id = 1;
        int expected_creator = 10;
        String expected_title = "Test";
        String expected_body = "Request Body";
        String expected_response = "Request Response";
        boolean expected_completed = false;

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_title, expected_body, expected_response, expected_completed);

        // Analyze
        assertEquals(expected_id,req.getId());
        assertEquals(expected_creator, req.getCreator());
        assertEquals(expected_title,req.getTitle());
        assertEquals(expected_body,req.getBody());
        assertEquals(expected_response,req.getResponse());
        assertEquals(expected_completed,req.getCompleted());
    }

    @Test
    public void testId() {
        // Setup
        int expected_id = 1;
        int expected_creator = 10;
        String expected_title = "Test";
        String expected_body = "Request Body";
        String expected_response = "Request Response";
        boolean expected_completed = false;

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_title, expected_body, expected_response, expected_completed);

        // Analyze
        assertEquals(expected_id,req.getId());
    }

    @Test
    public void testCreator() {
        // Setup
        int expected_id = 1;
        int expected_creator = 10;
        String expected_title = "Test";
        String expected_body = "Request Body";
        String expected_response = "Request Response";
        boolean expected_completed = false;

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_title, expected_body, expected_response, expected_completed);

        // Analyze
        assertEquals(expected_creator, req.getCreator());
    }

    @Test
    public void testTitle() {
        // Setup
        int expected_id = 1;
        int expected_creator = 10;
        String expected_title = "Test";
        String expected_body = "Request Body";
        String expected_response = "Request Response";
        boolean expected_completed = false;

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_title, expected_body, expected_response, expected_completed);

        // Analyze
        assertEquals(expected_title,req.getTitle());
    }

    @Test
    public void testBody() {
        // Setup
        int expected_id = 1;
        int expected_creator = 10;
        String expected_title = "Test";
        String expected_body = "Request Body";
        String expected_response = "Request Response";
        boolean expected_completed = false;

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_title, expected_body, expected_response, expected_completed);

        // Analyze
        assertEquals(expected_body,req.getBody());
    }

    @Test
    public void testResponse() {
        // Setup
        int expected_id = 1;
        int expected_creator = 10;
        String expected_title = "Test";
        String expected_body = "Request Body";
        String expected_response = "Request Response";
        boolean expected_completed = false;

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_title, expected_body, expected_response, expected_completed);

        // Analyze
        assertEquals(expected_response,req.getResponse());
    }

    @Test
    public void testCompleted() {
        // Setup
        int expected_id = 1;
        int expected_creator = 10;
        String expected_title = "Test";
        String expected_body = "Request Body";
        String expected_response = "Request Response";
        boolean expected_completed = false;

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_title, expected_body, expected_response, expected_completed);

        // Analyze
        assertEquals(expected_completed,req.getCompleted());
    }

    @Test
    public void testToString(){
        // Setup
        int expected_id = 1;
        int expected_creator = 10;
        String expected_title = "Test";
        String expected_body = "Request Body";
        String expected_response = "Request Response";
        boolean expected_completed = false;

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_title, expected_body, expected_response, expected_completed);
        String expected_string = String.format(HelpRequest.STRING_FORMAT, expected_id, expected_creator, expected_title, expected_body, expected_response, expected_completed);

        // Analyze
        assertEquals(expected_string, req.toString());
    }
}