package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.model.Need;

public class NeedTest {
    @Test
    public void testCtor(){
        //setting up
        int expected_id = 5;
        String expected_name = "Eric"; 
        int expected_cost = 1;
        int expected_quantity = 10;
        String expected_type = "food";

        //Invoke
        Need need = new Need(expected_id,expected_name,expected_cost,
        expected_quantity, expected_type);

        //Analyze
        assertEquals(expected_id, need.getId());
        assertEquals(expected_name, need.getName());
        assertEquals(expected_cost, need.getCost());
        assertEquals(expected_quantity, need.getQuantity());
        assertEquals(expected_type, need.getType());


    }
    @Test
    public void testName(){
        //Setup
        int id = 5;
        String name = "Eric"; 
        int cost = 1;
        int quantity = 10;
        String type = "food";

        Need need = new Need(id,name,cost,quantity,type);

        String expected_name = "notEric";
        //Invoke
        need.setName(expected_name);
        //Analyze
        assertEquals(expected_name, need.getName());
    }

    @Test
    public void testToString(){
        //Setup
        int id = 5;
        String name = "Eric"; 
        int cost = 1;
        int quantity = 10;
        String type = "food";

        String expected_string = String.format(Need.STRING_FORMAT,id, name,
        cost,quantity,type);

        Need need = new Need(id,name,cost,quantity,type);
        //Invoke
        String actual_String = need.toString();

        //Anyalze
        assertEquals(expected_string, actual_String);
    }
}
