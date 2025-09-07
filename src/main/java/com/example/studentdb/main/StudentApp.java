package com.example.studentdb.main;

import com.example.studentdb.db.DatabaseConnection;
import java.sql.Connection;

public class StudentApp {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Connected to the database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
