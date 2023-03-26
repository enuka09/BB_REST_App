package com.example.bb_rest_app;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectorTest {

    @Test
    public void testConnection() {
        try (Connection connection = DBConnector.getConnection()) {
            assertNotNull(connection);
            Statement statement = connection.createStatement();
            assertNotNull(statement);
            statement.close();
        } catch (SQLException ex) {
            fail("An exception occurred: " + ex.getMessage());
        }
    }
}