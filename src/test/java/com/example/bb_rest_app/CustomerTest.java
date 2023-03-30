package com.example.bb_rest_app;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void testGettersAndSetters() {
        int id = 1;
        String firstName = "Enuka";
        String lastName = "Pinsara";
        String username = "enuka@gmail.com";
        String password = "Enuka_@2002";
        String nic = "1234567890";
        Date dob = Date.valueOf("2002-09-06");

        Customer customer = new Customer(id, firstName, lastName, username, password, nic, dob);

        assertEquals(id, customer.getId());
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(username, customer.getUsername());
        assertEquals(password, customer.getPassword());
        assertEquals(nic, customer.getNIC());
        assertEquals(dob, customer.getDOB());

        int newId = 2;
        String newFirstName = "Nimal";
        String newLastName = "Perera";
        String newUsername = "nimal@gmail.com";
        String newPassword = "nimal@123";
        String newNic = "9876543210";
        Date newDob = Date.valueOf("1990-10-25");

        customer.setId(newId);
        customer.setFirstName(newFirstName);
        customer.setLastName(newLastName);
        customer.setUsername(newUsername);
        customer.setPassword(newPassword);
        customer.setNIC(newNic);
        customer.setDOB(newDob);

        assertEquals(newId, customer.getId());
        assertEquals(newFirstName, customer.getFirstName());
        assertEquals(newLastName, customer.getLastName());
        assertEquals(newUsername, customer.getUsername());
        assertEquals(newPassword, customer.getPassword());
        assertEquals(newNic, customer.getNIC());
        assertEquals(newDob, customer.getDOB());
    }

    @Test
    public void testConstructor() {
        int id = 1;
        String firstName = "Enuka";
        String lastName = "Pinsara";

        Customer customer = new Customer(id, firstName, lastName);

        assertEquals(id, customer.getId());
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
    }
}
