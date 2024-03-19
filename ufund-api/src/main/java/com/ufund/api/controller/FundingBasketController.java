package com.ufund.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ufund.api.persistence.FundingBasketDAO;
import com.ufund.api.model.FundingBasket;

/**
 * Handles the REST API requests for the FundingBasket resource.
 *
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework.
 * 
 * @author Andy Lin
 */
@RestController
@RequestMapping("fundingbasket")
//@CrossOrigin(origins = "http://localhost:4200")
public class FundingBasketController {
    private static final Logger LOG = Logger.getLogger(FundingBasketController.class.getName());
    private final FundingBasketDAO fundingBasketDAO;

    /**
     * Creates a REST API controller to respond to requests.
     * 
     * @param fundingBasketDAO The {@link FundingBasketDAO FundingBasket Data Access Object} to perform CRUD operations.
     *                         This dependency is injected by the Spring Framework.
     */
    public FundingBasketController(FundingBasketDAO fundingBasketDAO) {
        this.fundingBasketDAO = fundingBasketDAO;
    }

    /**
     * Responds to the GET request for a FundingBasket for the given id.
     * 
     * @param id The id used to locate the FundingBasket.
     * @return ResponseEntity with FundingBasket object and HTTP status of OK if found,
     *         ResponseEntity with HTTP status of NOT_FOUND if not found,
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FundingBasket> getFundingBasket(@PathVariable int id) {
        LOG.info("GET /funding-basket/" + id);
        try {
            FundingBasket fundingBasket = fundingBasketDAO.getFundingBasket(id);
            if (fundingBasket != null)
                return new ResponseEntity<>(fundingBasket, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the PUT request to update a FundingBasket.
     * 
     * @param fundingBasket The FundingBasket object to be updated.
     * @return ResponseEntity with updated FundingBasket object and HTTP status of OK if successful,
     *         ResponseEntity with HTTP status of NOT_FOUND if the FundingBasket to be updated is not found,
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise.
     */
    @PutMapping("")
    public ResponseEntity<FundingBasket> updateFundingBasket(@RequestBody FundingBasket fundingBasket) {
        LOG.info("PUT /funding-basket " + fundingBasket);

        try {
            FundingBasket currentFundingBasket = fundingBasketDAO.updateFundingBasket(fundingBasket);
            if (currentFundingBasket != null)
                return new ResponseEntity<>(fundingBasket, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the POST request to create a new FundingBasket.
     * 
     * @param fundingBasket The FundingBasket object to be created.
     * @return ResponseEntity with the created FundingBasket object and HTTP status of CREATED if successful,
     *         ResponseEntity with HTTP status of CONFLICT if there is a conflict during creation,
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise.
     */
    @PostMapping("/funding")
    public ResponseEntity<FundingBasket> createFundingBasket(@RequestBody FundingBasket fundingBasket) {
        LOG.info("POST /funding?name = " + fundingBasket);

        try {
            FundingBasket status = fundingBasketDAO.createFundingBasket(fundingBasket);
            if (status != null)
                return new ResponseEntity<>(status, HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
