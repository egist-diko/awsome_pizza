package com.awsome_pizza;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionTest {
    @Test
    public void testConnection() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=awsome_pizza";
        String user = "egist";
        String password = "egist123";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
