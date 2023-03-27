package com.example.bb_rest_app;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerResourceTest {

    @Test
    public void testGetCustomers() {
        CustomerResource resource = new CustomerResource();

        String customersJson = resource.getCustomers();

        // Assert that the returned JSON string is not null
        assertNotNull(customersJson);

        // Assert that the returned JSON string can be parsed into an array of customers
        Customer[] customers = new Gson().fromJson(customersJson, Customer[].class);
        assertNotNull(customers);
    }
}