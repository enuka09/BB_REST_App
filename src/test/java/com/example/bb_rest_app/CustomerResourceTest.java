package com.example.bb_rest_app;

import com.google.gson.*;
import jakarta.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class CustomerResourceTest {

    @Test
    public void testGetCustomers() {
        // Create a new instance of the CustomerResource class
        CustomerResource customerResource = new CustomerResource();

        // Call the getCustomers method and store the result in a String variable
        String result = customerResource.getCustomers();

        // Check if the result is not null
        assertNotNull(result);

        // Check if the result is a valid JSON string
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(result);
            assertTrue(jsonElement.isJsonArray());
        } catch (JsonSyntaxException e) {
            fail("Invalid JSON string returned");
        }
    }
}
