package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    public void testGettersAndSetters() {
        Category category = new Category("1", "Books");
        assertEquals("1", category.getId());
        assertEquals("Books", category.getName());

        category.setId("2");
        category.setName("Electronics");
        assertEquals("2", category.getId());
        assertEquals("Electronics", category.getName());
    }

    @Test
    public void testEmptyConstructor() {
        Category category = new Category();
        assertNull(category.getId());
        assertNull(category.getName());
    }
}