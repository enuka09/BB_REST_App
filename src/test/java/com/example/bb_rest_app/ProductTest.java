package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    public void testProduct() {

        // Create a new Product object
        Product product = new Product("12345", "Sample Product", 100.00, "This is a sample product.", "Sample Category", "Sample Brand", 10);

        // Test the getters and setters
        assertEquals("12345", product.getId());
        product.setId("67890");
        assertEquals("67890", product.getId());

        assertEquals("Sample Product", product.getName());
        product.setName("New Product");
        assertEquals("New Product", product.getName());

        assertEquals(100.00, product.getPrice(), 0);
        product.setPrice(200.00);
        assertEquals(200.00, product.getPrice(), 0);

        assertEquals("This is a sample product.", product.getDescription());
        product.setDescription("This is a new product.");
        assertEquals("This is a new product.", product.getDescription());

        assertEquals("Sample Category", product.getCategory());
        product.setCategory("New Category");
        assertEquals("New Category", product.getCategory());

        assertEquals("Sample Brand", product.getBrand());
        product.setBrand("New Brand");
        assertEquals("New Brand", product.getBrand());

        assertEquals(10, product.getQuantity());
        product.setQuantity(20);
        assertEquals(20, product.getQuantity());
    }
}