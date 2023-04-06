package com.example.bb_rest_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    public static void main(String[] args) throws SQLException {
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
                double loanAmount = rs.getDouble("LoanAmount");

                Customer customer = new Customer(id, firstName, lastName, username, password, nic, dob, loanAmount);
                customers.add(customer);

                System.out.println("Customer ID: " + customer.getId());
                System.out.println("First Name: " + customer.getFirstName());
                System.out.println("Last Name: " + customer.getLastName());
                System.out.println("Username: " + customer.getUsername());
                System.out.println("Password: " + customer.getPassword());
                System.out.println("NIC: " + customer.getNIC());
                System.out.println("Date of Birth: " + customer.getDOB());
                System.out.println("Loan Amount: " + customer.getLoanAmount());
                System.out.println("------------------------");
            }
            rs.close();
            stmt.close();
        }
        return customers;
    }


    //     Insert Customers to the database
    public static void insertUser(String firstName, String lastName, String username, String password, String nic, Date dob, double loanAmount) throws SQLException {
        try (Connection conn = DBConnector.getConnection()) {
            String sql = "INSERT INTO customers (CusFirstName, CusLastName, CusUsername, CusPassword, CusNIC, CusDOB, LoanAmount) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, username);
                pstmt.setString(4, password);
                pstmt.setString(5, nic);
                pstmt.setDate(6, dob);
                pstmt.setDouble(7, 15000);


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


    public static List<Product> getProduct() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {

            String sql = "SELECT * FROM product";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ProductID");
                String name = rs.getString("ProductName");
                double price = rs.getDouble("ProductPrice");
                String description = rs.getString("ProductDescription");
                String category = rs.getString("ProductCategory");
                String brand = rs.getString("ProductBrand");
                int quantity = rs.getInt("ProductQuantity");

                Product product = new Product(id, name, price, description, category, brand, quantity);
                products.add(product);

                System.out.println("Product ID: " + product.getId());
                System.out.println("Product Name: " + product.getName());
                System.out.println("Product Price: " + product.getPrice());
                System.out.println("Product Description: " + product.getDescription());
                System.out.println("Product Category: " + product.getCategory());
                System.out.println("Product Brand: " + product.getBrand());
                System.out.println("Product Quantity: " + product.getQuantity());
                System.out.println("------------------------");
            }
            rs.close();
            stmt.close();
        }
        return products;
    }


    //Insert Products into the database
    public void addProduct(Product product) {
        String sql = "INSERT INTO product (ProductID, ProductName, ProductPrice, ProductDescription, ProductCategory, ProductBrand, ProductQuantity) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getDescription());
            pstmt.setString(5, product.getCategory());
            pstmt.setString(6, product.getBrand());
            pstmt.setInt(7, product.getQuantity());
            pstmt.executeUpdate();
            System.out.println("Product added Successfully!");
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to Add Product!", e);
        }
    }

    //Delete Product from the Database
    public static void deleteProduct(Product product) throws SQLException {
        String id = product.getId();
        String sql = "DELETE FROM product WHERE ProductID = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    //Edit Products in the Database
    public void updateProduct(Product product) {
        String updateQuery = "UPDATE product SET ProductName=?, ProductPrice=?, ProductDescription=?, " +
                "ProductCategory=?, ProductBrand=?, ProductQuantity=? WHERE ProductID=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setString(5, product.getBrand());
            stmt.setInt(6, product.getQuantity());
            stmt.setString(7, product.getId());

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Record Customer Purchases in DB
    public static void addPurchase(Purchases purchases) throws SQLException {
        String sql = "INSERT INTO purchase (CusUsername, ProductID, ProductName, ProductPrice, LoanBalance, Installment1, Installment2, Installment3) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, purchases.getCus_username());
            pstmt.setString(2, purchases.getProduct_id());
            pstmt.setString(3, purchases.getProduct_name());
            pstmt.setDouble(4, purchases.getProduct_price());
            pstmt.setDouble(5, purchases.getLoan_balance());
            pstmt.setDouble(6, purchases.getInstallment_1());
            pstmt.setDouble(7, purchases.getInstallment_2());
            pstmt.setDouble(8, purchases.getInstallment_3());
            pstmt.executeUpdate();
            System.out.println("Customer Purchase added Successfully!");
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to Add Customer Purchase!", e);
        }

        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {

            String sql1 = "SELECT ProductQuantity FROM product where ProductID ='" + purchases.getProduct_id() + "'";

            PreparedStatement stmt = conn.prepareStatement(sql1);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int quantity = rs.getInt("ProductQuantity");

                Product product = new Product(quantity);
                products.add(product);
                System.out.println(product);

                Integer newProductQty = quantity - 1;
                product.setQuantity(newProductQty);


                String updateQuery = "UPDATE product SET  " +
                        "ProductQuantity=? WHERE ProductID=?";

                try (Connection connn = DBConnector.getConnection();
                     PreparedStatement stmtt = connn.prepareStatement(updateQuery)) {


                    stmtt.setInt(1, product.getQuantity());
                    stmtt.setString(2, purchases.getProduct_id());


//                    int rowsAffected = stmt.executeUpdate();
                    int rowsAffected = stmtt.executeUpdate();
                    System.out.println(rowsAffected + " row(s) updated.");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    //View all Purchase Details from the database
    public static List<Purchases> getPurchase() throws SQLException {
        List<Purchases> purchasesList = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {

            String sql = "SELECT * FROM purchase";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int purchase_id = rs.getInt("PurchaseID");
                String cus_username = rs.getString("CusUsername");
                String product_id = rs.getString("ProductID");
                String product_name = rs.getString("ProductName");
                double product_price = rs.getDouble("ProductPrice");
                double loan_balance = rs.getDouble("LoanBalance");
                double installment_1 = rs.getDouble("Installment1");
                double installment_2 = rs.getDouble("Installment2");
                double installment_3 = rs.getDouble("Installment3");

                Purchases purchases = new Purchases(purchase_id, cus_username, product_id, product_name, product_price, loan_balance, installment_1, installment_2, installment_3);
                purchasesList.add(purchases);

                System.out.println("Purchase ID: " + purchases.getPurchase_id());
                System.out.println("Customer Username: " + purchases.getCus_username());
                System.out.println("Product ID: " + purchases.getProduct_id());
                System.out.println("Product Name: " + purchases.getProduct_name());
                System.out.println("Product Price: " + purchases.getProduct_price());
                System.out.println("Loan Balance: " + purchases.getLoan_balance());
                System.out.println("Installment 1: " + purchases.getInstallment_1());
                System.out.println("Installment 2: " + purchases.getInstallment_2());
                System.out.println("Installment 3: " + purchases.getInstallment_3());
                System.out.println("------------------------");
            }
            rs.close();
            stmt.close();
        }
        return purchasesList;
    }


    //    View Purchase Details of Relevant Customer Only
    public static List<Purchases> getPurchasebyUsername(String cus_username) throws SQLException {
        List<Purchases> purchasesList = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {

            String sql = "SELECT * FROM purchase WHERE CusUsername=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cus_username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int purchase_id = rs.getInt("PurchaseID");
                String product_id = rs.getString("ProductID");
                String product_name = rs.getString("ProductName");
                double product_price = rs.getDouble("ProductPrice");
                double loan_balance = rs.getDouble("LoanBalance");
                double installment_1 = rs.getDouble("Installment1");
                double installment_2 = rs.getDouble("Installment2");
                double installment_3 = rs.getDouble("Installment3");

                Purchases purchases = new Purchases(purchase_id, cus_username, product_id, product_name, product_price, loan_balance, installment_1, installment_2, installment_3);
                purchasesList.add(purchases);

                System.out.println("Purchase ID: " + purchases.getPurchase_id());
                System.out.println("Customer Username: " + purchases.getCus_username());
                System.out.println("Product ID: " + purchases.getProduct_id());
                System.out.println("Product Name: " + purchases.getProduct_name());
                System.out.println("Product Price: " + purchases.getProduct_price());
                System.out.println("Loan Balance: " + purchases.getLoan_balance());
                System.out.println("Installment 1: " + purchases.getInstallment_1());
                System.out.println("Installment 2: " + purchases.getInstallment_2());
                System.out.println("Installment 3: " + purchases.getInstallment_3());
                System.out.println("------------------------");
            }
            rs.close();
            stmt.close();
        }
        return purchasesList;
    }
}


