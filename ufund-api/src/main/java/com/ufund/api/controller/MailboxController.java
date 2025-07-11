package com.ufund.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ufund.api.persistence.MailboxDAO;
import com.ufund.api.model.HelpRequest;

/**
 * Handles the REST API requests for the HelpRequest resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Andy Lin and Matthew Peck
 */
@RestController
@RequestMapping("mailbox")
public class MailboxController {
    private static final Logger LOG = Logger.getLogger(MailboxController.class.getName());
    private MailboxDAO mailboxDao;

    /**
     * Creates a REST API controller to respond to requests
     * 
     * @param mailboxDAO The {@link MailboxDAO HelpRequest Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public MailboxController(MailboxDAO mailboxDAO) {
        this.mailboxDao = mailboxDAO;
    }

    /**
     * Responds to the GET request for all {@linkplain HelpRequest request} for the given user
     * 
     * @return ResponseEntity with array of {@link HelpRequest request} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */

    //We need to edit this so that it lines up with JavaDoc above, or update JavaDoc
    @GetMapping("/{id}")
    public ResponseEntity<HelpRequest> getRequest(@PathVariable int id) {
        LOG.info("GET /mailbox/request");
        try {
            HelpRequest myRequest = mailboxDao.getRequest(id);
            if (myRequest != null)
                return new ResponseEntity<HelpRequest>(myRequest, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain HelpRequest request}
     * 
     * @return ResponseEntity with array of {@link HelpRequest request} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */

    //We need to edit this so that it lines up with JavaDoc above, or update JavaDoc
    @GetMapping("")
    public ResponseEntity<HelpRequest[]> getMailbox() {
        LOG.info("GET /mailbox");
        try {
            HelpRequest[] mailbox = mailboxDao.getRequests();
            if (mailbox != null)
                return new ResponseEntity<HelpRequest[]>(mailbox, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain HelpRequest request} whose title or body contains
     * the text in contains
     * 
     * @param contains The  parameter which contains the text used to find the {@link HelpRequest request}
     * 
     * @return ResponseEntity with array of {@link HelpRequest request} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all requests that contain the text "ma"
     * GET http://localhost:8080/mailbox/?contains=ma
     */

    //We need to edit this so that it lines up with JavaDoc above, or update JavaDoc
    @GetMapping("/")
    public ResponseEntity<HelpRequest[]> searchMailbox(@RequestParam String contains) {
        LOG.info("GET /mailbox/?contains=" + contains);
        try {
            HelpRequest[] requests = mailboxDao.findRequests(contains);
            if (requests != null)
                return new ResponseEntity<HelpRequest[]>(requests, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@linkplain HelpRequest request} with the provided request object
     * 
     * @param request - The {@link HelpRequest request} to create
     * 
     * @return ResponseEntity with created {@link HelpRequest request} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link HelpRequest request} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<HelpRequest> createRequest(@RequestBody HelpRequest request) {
        LOG.info("POST /mailbox " + request);
        try {
            HelpRequest newRequest = mailboxDao.createRequest(request);
            if (newRequest != null)
                return new ResponseEntity<HelpRequest>(newRequest, HttpStatus.CREATED);
            else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the {@linkplain HelpRequest request} with the provided {@linkplain HelpRequest request} object, if it exists
     * 
     * @param request The {@link HelpRequest request} to update
     * 
     * @return ResponseEntity with updated {@link HelpRequest request} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<HelpRequest> updateRequest(@RequestBody HelpRequest request) {
        LOG.info("PUT /mailbox " + request);
        try {
            HelpRequest newRequest = mailboxDao.updateRequest(request);
            if (newRequest != null)
                return new ResponseEntity<HelpRequest>(newRequest, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a {@linkplain HelpRequest request} with the given id
     * 
     * @param id The id of the {@link HelpRequest request} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HelpRequest> deleteRequest(@PathVariable int id) {
        LOG.info("DELETE /mailbox/" + id);
        try {
            boolean deleted = mailboxDao.deleteRequest(id);
            if (deleted)
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<HelpRequest>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}