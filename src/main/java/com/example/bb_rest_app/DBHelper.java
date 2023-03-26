package com.example.bb_rest_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    public static void main(String[] args) {
        try {
            List<Customer> customers = getCustomers();
            for (Customer customer : customers) {
                System.out.println("Customer ID: " + customer.getId());
                System.out.println("First Name: " + customer.getFirstName());
                System.out.println("Last Name: " + customer.getLastName());
                System.out.println("Date of Birth: " + customer.getDateOfBirth());
                System.out.println("------------------------");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {

            String sql = "SELECT * FROM customerinfo";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("CusID");
                String firstName = rs.getString("CusFirstName");
                String lastName = rs.getString("CusLastName");
                Date dateOfBirth = rs.getDate("CusDOB");

                Customer customer = new Customer(customerId, firstName, lastName, dateOfBirth);
                customers.add(customer);
            }

            rs.close();
            stmt.close();
        }
        return customers;
    }
}
