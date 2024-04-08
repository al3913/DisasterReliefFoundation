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
        String expected_creator = "admin";
        String expected_body = "Request Body";

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_body);

        // Analyze
        assertEquals(expected_id,req.getId());
        assertEquals(expected_creator, req.getCreator());
        assertEquals(expected_body,req.getBody());
    }

    @Test
    public void testGetId() {
        // Setup
        int expected_id = 1;
        String expected_creator = "admin";
        String expected_body = "Request Body";

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_body);

        // Analyze
        assertEquals(expected_id,req.getId());
    }

    @Test
    public void testGetCreator() {
        // Setup
        int expected_id = 1;
        String expected_creator = "admin";
        String expected_body = "Request Body";

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_body);

        // Analyze
        assertEquals(expected_creator, req.getCreator());
    }


    @Test
    public void testGetBody() {
        // Setup
        int expected_id = 1;
        String expected_creator = "admin";
        String expected_body = "Request Body";

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_body);

        // Analyze
        assertEquals(expected_body,req.getBody());
    }

    @Test
    public void testToString(){
        // Setup
        int expected_id = 1;
        String expected_creator = "admin";
        String expected_body = "Request Body";

        // Invoke
        HelpRequest req = new HelpRequest(expected_id, expected_creator, expected_body);
        String expected_string = String.format(HelpRequest.STRING_FORMAT, expected_id, expected_creator, expected_body);

        // Analyze
        assertEquals(expected_string, req.toString());
    }
}