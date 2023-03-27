package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBHelperTest {

   @Test
    void getCustomers() throws SQLException {
        List<Customer> customers = DBHelper.getCustomers();

        // Check that the returned list is not empty
        assertFalse(customers.isEmpty());

        // Check that each customer object has the expected properties
        for (Customer customer : customers) {
            assertNotNull(customer.getId());
            assertNotNull(customer.getFirstName());
            assertNotNull(customer.getLastName());
            assertNotNull(customer.getDOB());
        }
    }
}