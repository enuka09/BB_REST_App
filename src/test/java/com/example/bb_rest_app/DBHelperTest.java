package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import static com.example.bb_rest_app.DBHelper.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
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

    @Test
    public void testValidAdmin() {
        assertTrue(validateAdmin("enuka@09", "Enuka_@2002"));
    }

    @Test
    public void testInvalidAdmin() {
        assertFalse(validateAdmin("invalidAdmin", "wrongPassword"));
    }

    @Test
    public void testEmptyUsernameAndPassword() {
        assertFalse(validateAdmin("", ""));
    }


    @Test
    public void testAddCategory() throws SQLException {
        DBHelper dbHelper = new DBHelper();

        // Add a new category
        dbHelper.addCategory("C007", "Food");

        // Verify that the category was added to the database
        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM category WHERE CategoryID = 'C007'")) {
            assertTrue(rs.next());
            assertEquals("Food", rs.getString("CategoryName"));
            assertFalse(rs.next());
        }
    }

    @Test
    public void testGetCategory() throws SQLException {
        List<Category> categories = DBHelper.getCategory();

        assertNotNull(categories);
        assertTrue(categories.size() > 0);

        for (Category category : categories) {
            assertNotNull(category.getId());
            assertNotNull(category.getName());
        }
    }

    @Test
    public void testDeleteCategory() throws SQLException {
        // create a mock Category object with a known id
        Category category = new Category("1", "Test Category");

        // insert the mock Category into the database
        String insertSql = "INSERT INTO category (CategoryID, CategoryName) VALUES (?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setString(1, category.getId());
            stmt.setString(2, category.getName());
            stmt.executeUpdate();
        }

        // delete the mock Category from the database using the deleteCategory method
        deleteCategory(category);

        // verify that the Category has been deleted from the database
        String selectSql = "SELECT * FROM category WHERE CategoryID = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {
            stmt.setString(1, category.getId());
            ResultSet rs = stmt.executeQuery();
            assertFalse(rs.next()); // assert that there are no rows returned
        }
    }

    @Test
    void updateCategory() {
        DBHelper dbHelper = new DBHelper();
        Category categoryToUpdate = new Category("C005", "Food & Beverage");
        dbHelper.updateCategory(categoryToUpdate);

    }

    @Test
    public void testGetBrand() throws SQLException {
        List<Brand> brands = DBHelper.getBrand();

        assertNotNull(brands);
        assertTrue(brands.size() > 0);

        for (Brand brand : brands) {
            assertNotNull(brand.getId());
            assertNotNull(brand.getName());
        }
    }

    @Test
    public void testAddBrand() throws SQLException {

            DBHelper dbHelper = new DBHelper();

            // Add a new brand
            dbHelper.addBrand("B004", "Zebronics");

            // Verify that the Brand was added to the database
            try (Connection conn = DBConnector.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM brand WHERE BrandID = 'C004'")) {
                assertTrue(rs.next());
                assertEquals("Zebronics", rs.getString("BrandName"));
                assertFalse(rs.next());
            }
        }

    @Test
        public void testDeleteBrand() throws SQLException {
            // create a mock Brand object with a known id
            Brand brand = new Brand("1", "Test Brand");

            // insert the mock Category into the database
            String insertSql = "INSERT INTO brand (BrandID, BrandName) VALUES (?, ?)";
            try (Connection conn = DBConnector.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setString(1, brand.getId());
                stmt.setString(2, brand.getName());
                stmt.executeUpdate();
            }

            // delete the mock Brand from the database using the deleteBrand method
            deleteBrand(brand);

            // verify that the Brand has been deleted from the database
            String selectSql = "SELECT * FROM brand WHERE BrandID = ?";
            try (Connection conn = DBConnector.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setString(1, brand.getId());
                ResultSet rs = stmt.executeQuery();
                assertFalse(rs.next()); // assert that there are no rows returned
            }
        }

    @Test
    void updateBrand() {
            DBHelper dbHelper = new DBHelper();
            Brand brandToUpdate = new Brand("B003", "Zebronics");
            dbHelper.updateBrand(brandToUpdate);
        }
    }




