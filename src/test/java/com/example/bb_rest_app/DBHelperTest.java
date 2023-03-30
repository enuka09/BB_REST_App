package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

class DBHelperTest {

    @Test
    void getCustomers() throws SQLException {
        List<Customer> customers = DBHelper.getCustomers();
        assertNotNull(customers);
        assertTrue(customers.size() > 0);

        for (Customer customer : customers) {
            assertNotNull(customer.getId());
            assertNotNull(customer.getFirstName());
            assertNotNull(customer.getLastName());
            assertNotNull(customer.getUsername());
            assertNotNull(customer.getPassword());
            assertNotNull(customer.getNIC());
            assertNotNull(customer.getDOB());
        }

    }

    @Test
    void insertUser() {
        try {
            DBHelper.insertUser("test", "test", "test@", "test321", "98543210", Date.valueOf("2000-05-11"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void validateUser() {
        String username = "test@";
        String password = "test321";

        assertTrue(DBHelper.validateUser(username, password));
    }
}