package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    }

    @Test
    public void testCreateRequest_Success() {
        HelpRequest mockRequest = mock(HelpRequest.class);
        when(mailboxDAO.createRequest(any(HelpRequest.class))).thenReturn(mockRequest);
        ResponseEntity<HelpRequest> response = mailboxController.createRequest(new HelpRequest());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(mailboxDAO, times(1)).createRequest(any(HelpRequest.class));
    }
}
