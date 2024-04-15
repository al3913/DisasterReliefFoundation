package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.ArgumentMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.model.Need;
import com.ufund.api.persistence.CupboardFileDAO;

@Tag("Persistence-tier")
public class CupboardFileDAOTest {
    private CupboardFileDAO cupboardFileDAO;
    private ObjectMapper mockObjectMapper;
    private Need[] testNeeds;

    @BeforeEach
    public void setup() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testNeeds = new Need[]{
            new Need(1, "Pencils", 1, 100, "Stationery"),
            new Need(2, "Erasers", 1, 50, "Stationery"),
            new Need(3, "Notebooks", 2, 150, "Stationery")
        };

        when(mockObjectMapper.readValue(new File("needs.json"), Need[].class))
            .thenReturn(testNeeds);

        cupboardFileDAO = new CupboardFileDAO("needs.json", mockObjectMapper);
    }

    @Test
    public void testGetNeeds() {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        Need[] needs = cupboardFileDAO.getNeeds();
        assertEquals(testNeeds.length, needs.length);
        assertTrue(Arrays.stream(needs).collect(Collectors.toList()).containsAll(Arrays.asList(testNeeds)));
    }

    @Test
    public void testFindNeeds() {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        Need[] filteredNeeds = cupboardFileDAO.findNeeds("Era");

        assertEquals(1, filteredNeeds.length);
        assertEquals(testNeeds[1], filteredNeeds[0]);
    }

    @Test
    public void testGetNeed() {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        Need need = cupboardFileDAO.getNeed(1);

        assertEquals(testNeeds[0], need);
    }

    @Test
    public void testCreateNeed() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        Need newNeed = new Need(4, "Markers", 1, 80, "Stationery");
        Need createdNeed = assertDoesNotThrow(() -> cupboardFileDAO.createNeed(newNeed));

        assertNotNull(createdNeed);
        Need actual = cupboardFileDAO.getNeed(createdNeed.getId());
        assertEquals(actual.getId(), newNeed.getId());
        assertEquals(actual.getName(), newNeed.getName());
    }

    @Test
    public void testUpdateNeed() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        Need updatedNeed = new Need(1, "Colored Pencils", 3, 120, "Stationery");
        Need result = assertDoesNotThrow(() -> cupboardFileDAO.updateNeed(updatedNeed));

        assertNotNull(result);
        assertEquals(updatedNeed.getName(), result.getName());
    }

    @Test
    public void testDeleteNeed() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        boolean result = assertDoesNotThrow(() -> cupboardFileDAO.deleteNeed(1));

        assertTrue(result);
    }

    @Test
    public void testSaveException() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        // Setup
        Need newNeed = new Need(0, "Markers", 1, 80, "Stationery");

        // Arrange to throw an IOException when writeValue attempts to serialize the Need array
        doThrow(new IOException()).when(mockObjectMapper).writeValue(any(File.class), any(Need[].class));

        // Act & Assert
        assertThrows(IOException.class, () -> cupboardFileDAO.createNeed(newNeed), "IOException not thrown");
    }

    @Test
    public void testLoadException() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        doThrow(new IOException()).when(mockObjectMapper).readValue(ArgumentMatchers.any(File.class), ArgumentMatchers.eq(Need[].class));

        assertThrows(IOException.class, () -> new CupboardFileDAO("needs.json", mockObjectMapper));
    }

    @Test
    public void testGetNeedNotFound() {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        Need need = cupboardFileDAO.getNeed(99);

        assertNull(need);
    }

    @Test
    public void testDeleteNeedNotFound() throws IOException {
        try {
            setup();
        }
        catch (IOException e){
            System.out.println("fucked up");
        };
        boolean result = assertDoesNotThrow(() -> cupboardFileDAO.deleteNeed(99));

        assertFalse(result);
    }
}