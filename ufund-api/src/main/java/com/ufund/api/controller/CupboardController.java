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

import com.ufund.api.persistence.CupboardDAO;
import com.ufund.api.model.Need;

/**
 * Handles the REST API requests for the Need resource
 * 
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * </p>
 * 
 * @author Team
 * 
 */
@RestController
@RequestMapping("cupboard")
public class CupboardController {
    private static final Logger LOG = Logger.getLogger(CupboardController.class.getName());
    private CupboardDAO cupboardDao;

    /**
     * 
     * Creates a REST API controller to respond to requests
‌     *
     * @param cupboardDAO The {@link CupboardDAO Need Data Access Object} to perform CRUD operations.
     * This dependency is injected by the Spring Framework
     */
    public CupboardController(CupboardDAO cupboardDAO) {
        this.cupboardDao = cupboardDAO;
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
    public ResponseEntity<Need> getNeed(@PathVariable int id) {
        LOG.info("GET /cupboard/" + id);
        try {
            Need need = cupboardDao.getNeed(id);
            if (need != null)
                return new ResponseEntity<Need>(need, HttpStatus.OK);
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

    //We need to edit this so that it lines up with JavaDoc above, or update JavaDoc
    @GetMapping("")
    public ResponseEntity<Need[]> getCupboard() {
        LOG.info("GET /cupboard");
        try {
            Need[] cupboard = cupboardDao.getNeeds();
            if (cupboard != null)
                return new ResponseEntity<Need[]>(cupboard, HttpStatus.OK);
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
    //We need to edit this so that it lines up with JavaDoc above, or update JavaDoc
    @GetMapping("/")
    public ResponseEntity<Need[]> searchCupboard(@RequestParam String name) {
        LOG.info("GET /cupboard/?name=" + name);
        try {
            Need[] needs = cupboardDao.findNeeds(name);
            if (needs != null)
                return new ResponseEntity<Need[]>(needs, HttpStatus.OK);
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
    public ResponseEntity<Need> createNeed(@RequestBody Need need) {
        LOG.info("POST /cupboard " + need);
        try {
            Need newNeed = cupboardDao.createNeed(need);
            if (newNeed != null)
                return new ResponseEntity<Need>(newNeed, HttpStatus.CREATED);
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
    public ResponseEntity<Need> updateNeed(@RequestBody Need need) {
        LOG.info("PUT /cupboard " + need);
        try {
            Need newNeed = cupboardDao.updateNeed(need);
            if (newNeed != null)
                return new ResponseEntity<Need>(newNeed, HttpStatus.OK);
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
    public ResponseEntity<Need> deleteNeed(@PathVariable int id) {
        LOG.info("DELETE /cupboard/" + id);
        try {
            boolean deleted = cupboardDao.deleteNeed(id);
            if (deleted)
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<Need>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}