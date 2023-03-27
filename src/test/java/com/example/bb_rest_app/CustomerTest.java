package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    public void testConstructorAndGetters() {
        int id = 1;
        String firstName = "Enuka";
        String lastName = "Pinsara";
        String username = "enuka";
        String password = "123";
        String nic = "123456789V";
        Date dob = Date.valueOf("2002-06-09");

        Customer customer = new Customer(id, firstName, lastName, username, password, nic, dob);

        assertEquals(id, customer.getId());
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(username, customer.getUsername());
        assertEquals(password, customer.getPassword());
        assertEquals(nic, customer.getNIC());
        assertEquals(dob, customer.getDOB());
    }

    @Test
    public void testSetters() {
        Customer customer = new Customer(1, "Enuka", "Pinsara", Date.valueOf("2002-06-09"));

        int newId = 2;
        String newFirstName = "Sunil";
        String newLastName = "Kamal";
        String newUsername = "sunil.kamal";
        String newPassword = "356";
        String newNic = "987654321V";
        Date newDob = Date.valueOf("1998-11-25");

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

}
