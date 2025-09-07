package com.example.studentdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.studentdb.db.DatabaseConnection;
import com.example.studentdb.model.Student;

public class StudentDAO {

    // CREATE: Insert a new student
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, phone, dob, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPhone());
            stmt.setDate(4, student.getDob());
            stmt.setString(5, student.getAddress());

            stmt.executeUpdate();
            System.out.println("✅ Student added successfully!");

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                // Duplicate entry for email (MySQL error code 1062)
                System.out.println("⚠️ Email already exists! Please use a different email.");
            } else {
                System.out.println("❌ Could not add student. Please try again.");
            }
            e.printStackTrace();
        }
    }

    // READ: Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("dob"),
                        rs.getString("address")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch students from the database.");
            e.printStackTrace();
        }
        return students;
    }

    // UPDATE: Update a student
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, email=?, phone=?, dob=?, address=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPhone());
            stmt.setDate(4, student.getDob());
            stmt.setString(5, student.getAddress());
            stmt.setInt(6, student.getId());

            stmt.executeUpdate();
            System.out.println("✅ Student updated successfully!");

        } catch (SQLException e) {
            System.out.println("❌ Could not update student. Please check the ID and try again.");
            e.printStackTrace();
        }
    }

    // DELETE: Delete a student
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Student deleted successfully!");

        } catch (SQLException e) {
            System.out.println("❌ Could not delete student. Please check the ID.");
            e.printStackTrace();
        }
    }

    // SEARCH: Find students by name (partial match)
    public List<Student> findStudentsByName(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getDate("dob"),
                            rs.getString("address")
                    );
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to search students by name.");
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getDate("dob"),
                            rs.getString("address")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch student by ID.");
            e.printStackTrace();
        }
        return null;
    }

}
