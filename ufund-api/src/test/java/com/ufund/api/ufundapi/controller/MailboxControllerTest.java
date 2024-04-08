package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import com.ufund.api.model.HelpRequest;
import com.ufund.api.persistence.MailboxDAO;
import com.ufund.api.controller.MailboxController;
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
public class MailboxControllerTest {

    @Mock
    private MailboxDAO mockMailboxDAO;

    @InjectMocks
    private MailboxController mailboxController;

    @BeforeEach
    public void setUp() {
        mockMailboxDAO = mock(MailboxDAO.class);
        mailboxController = new MailboxController(mockMailboxDAO);
    }

    @Test
    public void testGetRequest() throws IOException {
        //Setup
        setUp();
        HelpRequest request = new HelpRequest(0, "admin", "test");
        // When the same id is passed in, our mock HelpRequestDAO will return the HelpRequest object
        when(mockMailboxDAO.getRequest(request.getId())).thenReturn(request);

        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.getRequest(request.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(request,response.getBody());

    }  

    @Test
    public void testGetRequestNotFound() throws Exception {
        //Setup
        setUp();
        int requestId = 0;
        // When the same id is passed in, our mock NeedDAO will return null,
        // simulating no request found
        when(mockMailboxDAO.getRequest((requestId))).thenReturn(null);

        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.getRequest(requestId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetRequestHandleException() throws Exception {
        //Setup
        setUp();
        int requestId = 0;
        // When getRequest is called on the mock MailboxDAO, throw an IOException
        doThrow(new IOException()).when(mockMailboxDAO).getRequest(requestId);
        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.getRequest(requestId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
    @Test
    public void testCreateRequestSuccess() throws IOException {
        // Setup
        setUp();
        HelpRequest request = new HelpRequest(0, "admin", "test");
        // when createRequest is called, return true simulating success
        when(mockMailboxDAO.createRequest(request)).thenReturn(request);

        //Invoke
        ResponseEntity<HelpRequest> response = mailboxController.createRequest(request);
        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(request,response.getBody());

    }

    @Test
    public void testCreateRequestFailed() throws IOException {  // createRequest may throw IOException
        // Setup
        setUp();
        HelpRequest request = new HelpRequest(0, "admin", "test");
        // when createRequest is called, return false simulating failed
        // creation and save
        when(mockMailboxDAO.createRequest(request)).thenReturn(null);

        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.createRequest(request);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void testCreateRequestHandleException() throws IOException {  // createRequest may throw IOException
        // Setup
        setUp();
        HelpRequest request = new HelpRequest(0, "admin", "test");

        // When createRequest is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockMailboxDAO).createRequest(request);

        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.createRequest(request);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testUpdateRequest() throws IOException { // updateRequest may throw IOException
        // Setup
        HelpRequest request = new HelpRequest(0, "admin", "test");
        // when updateHero is called, return true simulating successful
        // update and save
        when(mockMailboxDAO.updateRequest(request)).thenReturn(request);
        ResponseEntity<HelpRequest> response = mailboxController.updateRequest(request);
        request.setBody("Banana");

        // Invoke
        response = mailboxController.updateRequest(request);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(request,response.getBody());
    }

    @Test
    public void testUpdateRequestFailed() throws IOException { // updateRequest may throw IOException
        // Setup
        setUp();
        HelpRequest request = new HelpRequest(0, "admin", "test");
        // when updateHero is called, return null simulating failure
        when(mockMailboxDAO.updateRequest(request)).thenReturn(null);
        
        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.updateRequest(request);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateRequestHandleException() throws IOException { // updateRequest may throw IOException
        // Setup
        setUp();
        HelpRequest request = new HelpRequest(0, "admin", "test");
        // When updateHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockMailboxDAO).updateRequest(request);

        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.updateRequest(request);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetMailbox() throws IOException { // getRequests may throw IOException
        // Setup
        HelpRequest[] requests = new HelpRequest[2];
        requests[0] = new HelpRequest(0, "admin", "test");
        requests[1] = new HelpRequest(0, "helper", "test");
        // When getRequests is called return the requests created above
        when(mockMailboxDAO.getRequests()).thenReturn(requests);

        // Invoke
        ResponseEntity<HelpRequest[]> response = mailboxController.getMailbox();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(requests,response.getBody());
    }

    @Test
    public void testGetMailboxHandleException() throws IOException { // getRequests may throw IOException
        // Setup
        setUp();
        // When getRequests is called on the Mock MailboxDAO, throw an IOException
        doThrow(new IOException()).when(mockMailboxDAO).getRequests();

        // Invoke
        ResponseEntity<HelpRequest[]> response = mailboxController.getMailbox();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchMailbox() throws IOException { // findRequests may throw IOException
        // Setup
        setUp();
        String searchString = "ap";
        HelpRequest[] requests = new HelpRequest[2];
        requests[0] = new HelpRequest(0, "admin", "test");
        requests[1] = new HelpRequest(0, "helper", "test");
        // When findRequests is called with the search string, return the two
        /// heroes above
        when(mockMailboxDAO.findRequests(searchString)).thenReturn(requests);

        // Invoke
        ResponseEntity<HelpRequest[]> response = mailboxController.searchMailbox(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(requests,response.getBody());
    }

    @Test
    public void testSearchMailboxHandleException() throws IOException { // findRequests may throw IOException
        // Setup
        setUp();

        String searchString = "an";
        // When findRequests is called on the Mock MailboxDAO, throw an IOException
        doThrow(new IOException()).when(mockMailboxDAO).findRequests(searchString);

        // Invoke
        ResponseEntity<HelpRequest[]> response = mailboxController.searchMailbox(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteRequest() throws IOException { // deleteRequest may throw IOException
        // Setup
        int requestId = 99;
        // when deleteHero is called return true, simulating successful deletion
        when(mockMailboxDAO.deleteRequest(requestId)).thenReturn(true);

        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.deleteRequest(requestId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteRequestNotFound() throws IOException { // deleteRequest may throw IOException
        // Setup
        int requestId = 99;
        // when deleteHero is called return true, simulating successful deletion
        when(mockMailboxDAO.deleteRequest(requestId)).thenReturn(false);

        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.deleteRequest(requestId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteRequestHandleException() throws IOException { // deleteRequest may throw IOException
        // Setup
        int requestId = 99;
        // When deleteRequest is called on the Mock MailboxDAO, throw an IOException
        doThrow(new IOException()).when(mockMailboxDAO).deleteRequest(requestId);

        // Invoke
        ResponseEntity<HelpRequest> response = mailboxController.deleteRequest(requestId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}