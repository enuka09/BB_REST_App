package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.bb_rest_app.DBHelper.insertUser;
import static org.junit.jupiter.api.Assertions.*;

class DBHelperTest {

   @Test
    void getCustomers() throws SQLException {
        List<Customer> customers = DBHelper.getCustomers();

        // Check that the returned list is not empty
        assertFalse(customers.isEmpty());

        // Check that each customer object has the expected properties
        for (Customer customer : customers) {
            assertNotNull(customer.getId());
            assertNotNull(customer.getFirstName());
            assertNotNull(customer.getLastName());
            assertNotNull(customer.getDOB());
        }
    }

    @Test
    public void testInsertUser() {
        String firstName = "Enuka";
        String lastName = "Pinsara";
        String username = "enuka";
        String password = "enuka123";
        String dob = "2002-06-09";
        String nic = "987654321";

        try {
            // Insert the user
            insertUser(firstName, lastName, username, password, dob, nic);

            // Retrieve the user from the database
            try (Connection conn = DBConnector.getConnection()) {
                String sql = "SELECT * FROM users WHERE CusUsername=?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();

                // Verify the user is inserted correctly
                assertTrue(rs.next());
                assertEquals(firstName, rs.getString("CusFirstName"));
                assertEquals(lastName, rs.getString("CusLastName"));
                assertEquals(username, rs.getString("CusUsername"));
                assertEquals(password, rs.getString("CusPassword"));
                assertEquals(dob, rs.getString("CusDOB"));
                assertEquals(nic, rs.getString("CusNIC"));

                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Exception occurred while inserting user");
        }
    }


}