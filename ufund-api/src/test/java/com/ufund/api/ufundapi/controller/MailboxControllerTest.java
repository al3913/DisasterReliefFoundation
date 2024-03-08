package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import com.ufund.api.model.HelpRequest;
import com.ufund.api.persistence.MailboxDAO;
import com.ufund.api.controller.MailboxController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MailboxControllerTest {

    @Mock
    private MailboxDAO mailboxDAO;

    @InjectMocks
    private MailboxController mailboxController;

    @BeforeEach
    void setUp() {
        mailboxDAO = mock(MailboxDAO.class);
        mailboxController = new MailboxController(mailboxDAO);
    }

    @Test
    public void testCreateRequest_Success() throws IOException {
        // Setup
        HelpRequest request = new HelpRequest(0, 0, "test", "test", "test", false);
        // when createRequest is called, return true simulating success
        when(mailboxDAO.createRequest(request)).thenReturn(request);

        //Invoke
        ResponseEntity<HelpRequest> response = mailboxController.createRequest(request);
        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(request,response.getBody());

    }

    
}
