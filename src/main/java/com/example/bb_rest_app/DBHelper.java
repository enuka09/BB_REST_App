package com.example.bb_rest_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

   public static void main(String[] args) throws SQLException {

         /* DBHelper dbHelper =new DBHelper();
         dbHelper.getCustomers(); */

       insertUser("test", "test", "test", "test", "2006-08-12", "56295372");
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

    public static void insertUser(String firstName, String lastName, String username, String password, String dob, String nic) throws SQLException {
        try (Connection conn = DBConnector.getConnection()) {

        String sql = "INSERT INTO users (CusFirstName, CusLastName, CusUsername, CusPassword, CusNIC, CusDOB) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.setString(5, nic);
            pstmt.setString(6, dob);
            pstmt.executeUpdate();
            System.out.println("New user created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}
