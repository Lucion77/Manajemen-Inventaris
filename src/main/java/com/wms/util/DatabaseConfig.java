package com.wms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Helper class untuk membuat koneksi JDBC ke SQLite.
 */
public class DatabaseConfig {
    private static final String URL = "jdbc:sqlite:wms.db";

    public static Connection getConnection() throws SQLException {
        // Melakukan load driver meski tidak selalu wajib di versi JDBC baru
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL);
    }

    public static void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT NOT NULL,\n"
                + " category TEXT NOT NULL,\n"
                + " quantity INTEGER NOT NULL,\n"
                + " price REAL NOT NULL\n"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Database tables initialized.");
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
}
