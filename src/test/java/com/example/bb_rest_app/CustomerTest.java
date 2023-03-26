package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getFirstName() {
    }

    @Test
    void setFirstName() {
    }

    @Test
    void getLastName() {
    }

    @Test
    void setLastName() {
    }

    @Test
    void getDateOfBirth() {
    }

    @Test
    public void testConstructorAndGetters() {
        int id = 1;
        String firstName = "Amal";
        String lastName = "Perera";
        Date dateOfBirth = Date.valueOf("2001-01-01");
        Customer customer = new Customer(id, firstName, lastName, dateOfBirth);
        assertEquals(id, customer.getId());
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(dateOfBirth, customer.getDateOfBirth());
    }

    @Test
    public void testSetters() {
        Customer customer = new Customer(1, "Amal", "Perera", Date.valueOf("2001-01-01"));
        customer.setId(2);
        customer.setFirstName("Amal");
        customer.setLastName("Perera");
        customer.setDateOfBirth(Date.valueOf("2001-01-01"));
        assertEquals(2, customer.getId());
        assertEquals("Amal", customer.getFirstName());
        assertEquals("Perera", customer.getLastName());
        assertEquals(Date.valueOf("2001-01-01"), customer.getDateOfBirth());
    }

}