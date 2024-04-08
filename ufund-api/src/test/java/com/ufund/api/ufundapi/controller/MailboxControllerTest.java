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

    
}
