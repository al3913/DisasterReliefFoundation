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
 * Handles the REST API requests for the Ngeed resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author SWEN Faculty
 */
@RestController
@RequestMapping("mailbox")
public class MailboxController {
    private static final Logger LOG = Logger.getLogger(MailboxController.class.getName());
    private MailboxDAO mailboxDao;

    /**
     * Creates a REST API controller to respond to requests
     * 
     * @param cupboardDAO The {@link CupboardDAO Need Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public MailboxController(MailboxDAO mailboxDAO) {
        this.mailboxDao = mailboxDAO;
    }

    /**
     * Responds to the GET request for a {@linkplain Need need} for the given id
     * 
     * @param id The id used to locate the {@link Need need}
     * 
     * @return ResponseEntity with {@link Need need} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<HelpRequest> getRequest(@PathVariable int id) {
        LOG.info("GET /mailbox/" + id);
        try {
            HelpRequest request = mailboxDao.getRequest(id);
            if (request != null)
                return new ResponseEntity<HelpRequest>(request, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Need needs}
     * 
     * @return ResponseEntity with array of {@link Need need} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
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
     * Responds to the GET request for all {@linkplain Need needs} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Need needs}
     * 
     * @return ResponseEntity with array of {@link Need need} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all needs that contain the text "ma"
     * GET http://localhost:8080/needs/?name=ma
     */
    @GetMapping("/")
    public ResponseEntity<HelpRequest[]> searchMailbox(@RequestParam String name) {
        LOG.info("GET /mailbox/?name=" + name);
        try {
            HelpRequest[] requests = mailboxDao.findRequests(name);
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
     * Creates a {@linkplain Need need} with the provided need object
     * 
     * @param need - The {@link Need need} to create
     * 
     * @return ResponseEntity with created {@link Need need} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Need need} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<HelpRequest> createRequest(@RequestBody HelpRequest request) {
        LOG.info("POST /mailbox " + request);
        try {
            HelpRequest newRequest = mailboxDao.createRequest(request);
            if (mailboxDao.findRequests(request.getTitle()).length != 0)
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
     * Updates the {@linkplain Need need} with the provided {@linkplain Need need} object, if it exists
     * 
     * @param need The {@link Need need} to update
     * 
     * @return ResponseEntity with updated {@link Need need} object and HTTP status of OK if updated<br>
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
     * Deletes a {@linkplain Need need} with the given id
     * 
     * @param id The id of the {@link Need need} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HelpRequest> deleteNeed(@PathVariable int id) {
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