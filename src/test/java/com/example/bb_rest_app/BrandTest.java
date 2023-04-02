package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    @Test
    public void testGettersAndSetters() {
        Brand brand = new Brand("1", "Apple");
        assertEquals("1", brand.getId());
        assertEquals("Apple", brand.getName());

        brand.setId("2");
        brand.setName("Lumix");
        assertEquals("2", brand.getId());
        assertEquals("Lumix", brand.getName());
    }

    @Test
    public void testEmptyConstructor() {
        Brand brand = new Brand();
        assertNull(brand.getId());
        assertNull(brand.getName());
    }
}