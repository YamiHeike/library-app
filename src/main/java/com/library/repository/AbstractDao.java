package com.library.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDao {
    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "pass";

        return DriverManager.getConnection(url, user, password);
    }
}
