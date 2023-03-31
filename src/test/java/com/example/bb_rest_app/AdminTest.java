package com.example.bb_rest_app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AdminTest {
    private Admin admin;

    @Before
    public void setUp() {
        admin = new Admin();
        admin.setId(1);
        admin.setName("test1");
        admin.setUsername("test@1");
        admin.setPassword("test123");
    }

    @Test
    public void testGetId() {
        assertEquals(1, admin.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("test1", admin.getName());
    }

    @Test
    public void testGetUsername() {
        assertEquals("test@1", admin.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("test123", admin.getPassword());
    }

    @Test
    public void testSetId() {
        admin.setId(2);
        assertEquals(2, admin.getId());
    }

    @Test
    public void testSetName() {
        admin.setName("test2");
        assertEquals("test2", admin.getName());
    }

    @Test
    public void testSetUsername() {
        admin.setUsername("test@2");
        assertEquals("test@2", admin.getUsername());
    }

    @Test
    public void testSetPassword() {
        admin.setPassword("test321");
        assertEquals("test321", admin.getPassword());
    }
}

