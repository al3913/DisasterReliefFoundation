package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.ufund.api.persistence.CupboardDAO;
import com.ufund.api.controller.CupboardController;
import com.ufund.api.model.Need;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Cupboard Controller Class
 * 
 * @author Andy Lin
 * 
 */
@Tag("Controller-tier")
public class CupboardControllerTest {
    @Mock
    private CupboardDAO mockCupboardDAO;

    @InjectMocks
    private CupboardController cupboardController;
    /**
     * Before each test, create a new HeroController object and inject
     * a mock Hero DAO
     */

    @BeforeEach
    public void setupCupboardController() {
        mockCupboardDAO = mock(CupboardDAO.class);
        cupboardController = new CupboardController(mockCupboardDAO);
    }

    @Test
    public void testGetNeed() throws IOException {
        //Setup
        setupCupboardController();
        Need need = new Need(1, "Apples", 1, 50, "food");
        // When the same id is passed in, our mock NeedDAO will return the Need object
        when(mockCupboardDAO.getNeed(need.getId())).thenReturn(need);

        // Invoke
        ResponseEntity<Need> response = cupboardController.getNeed(need.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(need,response.getBody());

    }  

    @Test
    public void testGetNeedNotFound() throws Exception {
        //Setup
        setupCupboardController();
        int needId = 0;
        // When the same id is passed in, our mock NeedDAO will return null,
        // simulating no need found
        when(mockCupboardDAO.getNeed((needId))).thenReturn(null);

        // Invoke
        ResponseEntity<Need> response = cupboardController.getNeed(needId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetNeedHandleException() throws Exception {
        //Setup
        setupCupboardController();
        int needId = 0;
        // When getNeed is called on the mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockCupboardDAO).getNeed(needId);
        // Invoke
        ResponseEntity<Need> response = cupboardController.getNeed(needId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
    @Test
    public void testCreateNeedSuccess() throws IOException {  // createNeed may throw IOException
        // Setup
        Need need = new Need(1, "Apples", 1, 50, "food");
        // when createNeed is called, return true simulating successful
        // creation and save
        when(mockCupboardDAO.createNeed(need)).thenReturn(need);

        // Invoke
        ResponseEntity<Need> response = cupboardController.createNeed(need);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(need,response.getBody());
    }

    @Test
    public void testCreateNeedFailed() throws IOException {  // createNeed may throw IOException
        // Setup
        Need need = new Need(1, "Apples", 1, 50, "food");
        // when createNeed is called, return false simulating failed
        // creation and save
        when(mockCupboardDAO.createNeed(need)).thenReturn(null);

        // Invoke
        ResponseEntity<Need> response = cupboardController.createNeed(need);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void testCreateNeedHandleException() throws IOException {  // createNeed may throw IOException
        // Setup
        setupCupboardController();
        Need need = new Need(1, "Apples", 1, 50, "food");

        // When createNeed is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockCupboardDAO).createNeed(need);

        // Invoke
        ResponseEntity<Need> response = cupboardController.createNeed(need);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testUpdateNeed() throws IOException { // updateNeed may throw IOException
        // Setup
        Need need = new Need(1, "Apples", 1, 50, "food");
        // when updateHero is called, return true simulating successful
        // update and save
        when(mockCupboardDAO.updateNeed(need)).thenReturn(need);
        ResponseEntity<Need> response = cupboardController.updateNeed(need);
        need.setName("Banana");

        // Invoke
        response = cupboardController.updateNeed(need);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(need,response.getBody());
    }

    @Test
    public void testUpdateNeedFailed() throws IOException { // updateNeed may throw IOException
        // Setup
        Need need = new Need(1, "Apples", 1, 50, "food");
        // when updateHero is called, return null simulating failure
        when(mockCupboardDAO.updateNeed(need)).thenReturn(null);
        
        // Invoke
        ResponseEntity<Need> response = cupboardController.updateNeed(need);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateNeedHandleException() throws IOException { // updateNeed may throw IOException
        // Setup
        setupCupboardController();
        Need need = new Need(1, "Apples", 1, 50, "food");
        // When updateHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockCupboardDAO).updateNeed(need);

        // Invoke
        ResponseEntity<Need> response = cupboardController.updateNeed(need);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetCupboard() throws IOException { // getNeeds may throw IOException
        // Setup
        Need[] needs = new Need[2];
        needs[0] = new Need(1, "Apples", 1, 50, "food");
        needs[1] = new Need(2, "Bananas", 2, 50, "food");
        // When getNeeds is called return the needs created above
        when(mockCupboardDAO.getNeeds()).thenReturn(needs);

        // Invoke
        ResponseEntity<Need[]> response = cupboardController.getCupboard();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(needs,response.getBody());
    }

    @Test
    public void testGetCupboardHandleException() throws IOException { // getNeeds may throw IOException
        // Setup
        setupCupboardController();
        // When getNeeds is called on the Mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockCupboardDAO).getNeeds();

        // Invoke
        ResponseEntity<Need[]> response = cupboardController.getCupboard();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchCupboard() throws IOException { // findNeeds may throw IOException
        // Setup
        String searchString = "ap";
        Need[] needs = new Need[2];
        needs[0] = new Need(1, "Apples", 1, 50, "food");
        needs[1] = new Need(2, "Caps", 2, 50, "clothing");
        // When findNeeds is called with the search string, return the two
        /// heroes above
        when(mockCupboardDAO.findNeeds(searchString)).thenReturn(needs);

        // Invoke
        ResponseEntity<Need[]> response = cupboardController.searchCupboard(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(needs,response.getBody());
    }

    @Test
    public void testSearchCupboardHandleException() throws IOException { // findNeeds may throw IOException
        // Setup
        setupCupboardController();
        String searchString = "an";
        // When findNeeds is called on the Mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockCupboardDAO).findNeeds(searchString);

        // Invoke
        ResponseEntity<Need[]> response = cupboardController.searchCupboard(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteNeed() throws IOException { // deleteNeed may throw IOException
        // Setup
        int needId = 99;
        // when deleteHero is called return true, simulating successful deletion
        when(mockCupboardDAO.deleteNeed(needId)).thenReturn(true);

        // Invoke
        ResponseEntity<Need> response = cupboardController.deleteNeed(needId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteNeedNotFound() throws IOException { // deleteNeed may throw IOException
        // Setup
        int needId = 99;
        // when deleteHero is called return true, simulating successful deletion
        when(mockCupboardDAO.deleteNeed(needId)).thenReturn(false);

        // Invoke
        ResponseEntity<Need> response = cupboardController.deleteNeed(needId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteNeedHandleException() throws IOException { // deleteNeed may throw IOException
        // Setup
        setupCupboardController();
        int needId = 99;
        // When deleteNeed is called on the Mock CupboardDAO, throw an IOException
        doThrow(new IOException()).when(mockCupboardDAO).deleteNeed(needId);

        // Invoke
        ResponseEntity<Need> response = cupboardController.deleteNeed(needId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
