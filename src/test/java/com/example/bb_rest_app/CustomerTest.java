package com.example.bb_rest_app;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

private Customer customer;

@Before
public void setUp() {
        customer = new Customer(1, "John", "Doe", "johndoe", "password", "123456789V", Date.valueOf("1980-01-01"), 5000);
        }

@Test
public void testGetters() {
        assertEquals(1, customer.getId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("johndoe", customer.getUsername());
        assertEquals("password", customer.getPassword());
        assertEquals("123456789V", customer.getNIC());
        assertEquals(Date.valueOf("1980-01-01"), customer.getDOB());
        assertEquals(5000, customer.getLoanAmount(), 0.01);
        }

@Test
public void testSetters() {
        customer.setId(2);
        assertEquals(2, customer.getId());

        customer.setFirstName("Jane");
        assertEquals("Jane", customer.getFirstName());

        customer.setLastName("Doe");
        assertEquals("Doe", customer.getLastName());

        customer.setUsername("janedoe");
        assertEquals("janedoe", customer.getUsername());

        customer.setPassword("newpassword");
        assertEquals("newpassword", customer.getPassword());

        customer.setNIC("987654321V");
        assertEquals("987654321V", customer.getNIC());

        customer.setDOB(Date.valueOf("1990-01-01"));
        assertEquals(Date.valueOf("1990-01-01"), customer.getDOB());

        customer.setLoanAmount(10000);
        assertEquals(10000, customer.getLoanAmount(), 0.01);
        }
        }