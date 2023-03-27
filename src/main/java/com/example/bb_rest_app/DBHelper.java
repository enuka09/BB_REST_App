package com.example.bb_rest_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {


    public static void main(String[] args) throws SQLException {

        DBHelper dbHelper =new DBHelper();
        dbHelper.getCustomers();


    }

    public static List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection()) {

            String sql = "SELECT * FROM users";
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
}
