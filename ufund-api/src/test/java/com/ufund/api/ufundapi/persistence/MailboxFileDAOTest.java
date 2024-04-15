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
import com.ufund.api.model.HelpRequest;
import com.ufund.api.persistence.MailboxFileDAO;

@Tag("Persistence-tier")
public class MailboxFileDAOTest {
    private MailboxFileDAO mailboxFileDAO;
    private ObjectMapper mockObjectMapper;
    private HelpRequest[] testRequests;

    @BeforeEach
    public void setup() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testRequests = new HelpRequest[]{
            new HelpRequest(1, "101", "Need help with math homework"),
            new HelpRequest(2, "102", "Looking for a study group"),
            new HelpRequest(3, "103", "Requesting book recommendations")
        };

        when(mockObjectMapper.readValue(new File("requests.json"), HelpRequest[].class))
            .thenReturn(testRequests);

        mailboxFileDAO = new MailboxFileDAO("requests.json", mockObjectMapper);
    }

    @Test
    public void testGetRequests() {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        HelpRequest[] requests = mailboxFileDAO.getRequests();

        assertEquals(testRequests.length, requests.length);
        assertTrue(Arrays.asList(requests).containsAll(Arrays.asList(testRequests)));
    }

    @Test
    public void testFindRequests() {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        HelpRequest[] filteredRequests = mailboxFileDAO.findRequests("study");

        assertEquals(1, filteredRequests.length);
        assertEquals(testRequests[1], filteredRequests[0]);
    }

    @Test
    public void testCreateRequest() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        HelpRequest newRequest = new HelpRequest(0, "104", "Need advice on programming");
        HelpRequest createdRequest = assertDoesNotThrow(() -> mailboxFileDAO.createRequest(newRequest));

        assertNotNull(createdRequest);
        assertEquals(newRequest.getBody(), createdRequest.getBody());
    }

    @Test
    public void testUpdateRequest() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        HelpRequest updatedRequest = new HelpRequest(1, "101", "Updated help request for math homework");
        HelpRequest result = assertDoesNotThrow(() -> mailboxFileDAO.updateRequest(updatedRequest));

        assertNotNull(result);
        assertEquals(updatedRequest.getBody(), result.getBody());
    }

    @Test
    public void testDeleteRequest() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        assertTrue(mailboxFileDAO.deleteRequest(1));
    }

    @Test
    public void testSaveException() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        doThrow(new IOException()).when(mockObjectMapper).writeValue(any(File.class), any(HelpRequest[].class));

        assertThrows(IOException.class, () -> mailboxFileDAO.createRequest(new HelpRequest(4, "105", "Emergency assistance needed")), "IOException not thrown");
    }

    @Test
    public void testLoadException() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        doThrow(new IOException()).when(mockObjectMapper).readValue(any(File.class), eq(HelpRequest[].class));

        assertThrows(IOException.class, () -> new MailboxFileDAO("requests.json", mockObjectMapper), "IOException not thrown");
    }

    @Test
    public void testRequestNotFound() {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        assertNull(mailboxFileDAO.getRequest(99));
    }

    @Test
    public void testDeleteRequestNotFound() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        assertFalse(mailboxFileDAO.deleteRequest(99));
    }

    @Test
    public void testFindMyRequests() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        HelpRequest[] myRequests = mailboxFileDAO.findMyRequests(101);

        // Assuming all requests are from user 101 for simplicity in this example
        assertEquals(testRequests.length, myRequests.length);
    }
}