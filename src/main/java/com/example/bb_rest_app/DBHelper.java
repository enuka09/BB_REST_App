package com.example.bb_rest_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    public static void main(String[] args) throws SQLException {

//        View Category
//        try {
//            List<Brand> brands = getBrand();
//        } catch (SQLException e) {
//        }

          //Insert Customers
//        DBHelper dbHelper =new DBHelper();
//        dbHelper.getCustomers();
//        insertUser("test", "test", "test", "test", "123456789", Date.valueOf("2002-09-06"));

         // Login Method
//       String username = "enuka@09";
//       String password = "Enuka_@2002";
//       DBHelper dbHelper = new DBHelper();
//       if (dbHelper.validateAdmin(username, password)) {
//           System.out.println("Login successful");
//       } else {
//           System.out.println("Invalid username or password");
//       }

//        Insert Category
//        DBHelper dbHelper = new DBHelper();
//        dbHelper.addBrand("C004", "Nexo");


//        Delete category
//        Category categoryToDelete = new Category("C009");
//        try {
//            DBHelper.deleteCategory(categoryToDelete);
//            System.out.println("Category deleted successfully");
//        } catch (SQLException e) {
//            System.out.println("Error deleting category: " + e.getMessage());
//        }

        //Update Category
        DBHelper dbHelper = new DBHelper();
        Brand brand = new Brand("B003", "Nexo");
        dbHelper.updateBrand(brand);

 }

   // View Customer Details from database
    public static List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {

            String sql = "SELECT * FROM customers";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("CusID");
                String firstName = rs.getString("CusFirstName");
                String lastName = rs.getString("CusLastName");
                String username = rs.getString("CusUsername");
                String password = rs.getString("CusPassword");
                String nic = rs.getString("CusNIC");
                Date dob = rs.getDate("CusDOB");

                Customer customer = new Customer(id, firstName, lastName, username, password, nic, dob);
                customers.add(customer);

                System.out.println("Customer ID: " + customer.getId());
                System.out.println("First Name: " + customer.getFirstName());
                System.out.println("Last Name: " + customer.getLastName());
                System.out.println("Username: " + customer.getUsername());
                System.out.println("Password: " + customer.getPassword());
                System.out.println("NIC: " + customer.getNIC());
                System.out.println("Date of Birth: " + customer.getDOB());
                System.out.println("------------------------");
            }
            rs.close();
            stmt.close();
        }
        return customers;
    }


    // Insert Customers to the database
    public static void insertUser(String firstName, String lastName, String username, String password, String nic, Date dob) throws SQLException {
        try (Connection conn = DBConnector.getConnection()) {
            String sql = "INSERT INTO customers (CusFirstName, CusLastName, CusUsername, CusPassword, CusNIC, CusDOB) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, username);
                pstmt.setString(4, password);
                pstmt.setString(5, nic);
                pstmt.setDate(6, dob);
                pstmt.executeUpdate();
                System.out.println("New user created successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Validate Customer Credentials from the database
    public static boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM customers WHERE CusUsername=? AND CusPassword=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Customer exists with the given username and password
                return true;
            } else {
                // No customer found with the given username and password
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as appropriate for your application
            return false;
        }
    }


    // Validate Admin Credentials from the database
    public static boolean validateAdmin(String username, String password) {
        String sql = "SELECT * FROM admin WHERE AdminUsername=? AND AdminPassword=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Admin exists with the given username and password
                return true;
            } else {
                // No Admin found with the given username and password
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as appropriate for your application
            return false;
        }
    }

        //Insert Categories into the database
        public void addCategory(String categoryId, String categoryName) {
            String sql = "INSERT INTO category (CategoryID, CategoryName) VALUES (?, ?)";

            try (Connection conn = DBConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, categoryId);
                pstmt.setString(2, categoryName);
                pstmt.executeUpdate();
                System.out.println("Category added Successfully!");
            } catch (SQLException e) {
                throw new IllegalStateException("Failed to Add Category!", e);
            }
        }


    //View Category from database
    public static List<Category> getCategory() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {

            String sql = "SELECT * FROM category";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("CategoryID");
                String name = rs.getString("CategoryName");

                Category category = new Category(id, name);
                categories.add(category);

                System.out.println("Category ID: " + category.getId());
                System.out.println("Category Name: " + category.getName());
                System.out.println("------------------------");
            }
            rs.close();
            stmt.close();
        }
        return categories;
    }


    //Delete Category from the Database
    public static void deleteCategory(Category category) throws SQLException {
        String id = category.getId();
        String sql = "DELETE FROM category WHERE CategoryID = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    //Edit Category in the Database
    public void updateCategory(Category category) {
        String updateQuery = "UPDATE category SET CategoryName=? WHERE CategoryID=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, category.getName());
            stmt.setString(2, category.getId());

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //View Brands from the Database
    public static List<Brand> getBrand() throws SQLException {
        List<Brand> brands = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {

            String sql = "SELECT * FROM brand";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("BrandID");
                String name = rs.getString("BrandName");

                Brand brand = new Brand(id, name);
                brands.add(brand);

                System.out.println("Brand ID: " + brand.getId());
                System.out.println("Brand Name: " + brand.getName());
                System.out.println("------------------------");
            }
            rs.close();
            stmt.close();
        }
        return brands;
    }

    //Insert Brands into the database
    public void addBrand(String brandId, String brandName) {
        String sql = "INSERT INTO brand (BrandID, BrandName) VALUES (?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, brandId);
            pstmt.setString(2, brandName);
            pstmt.executeUpdate();
            System.out.println("Brand added Successfully!");
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to Add Brand!", e);
        }
    }

    //Delete Brand from the Database
    public static void deleteBrand(Brand brand) throws SQLException {
        String id = brand.getId();
        String sql = "DELETE FROM brand WHERE BrandID = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }


    //Edit Brands in the Database
    public void updateBrand(Brand brand) {
        String updateQuery = "UPDATE brand SET BrandName=? WHERE BrandID=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, brand.getName());
            stmt.setString(2, brand.getId());

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


