package com.example.studentdb.dao;

import com.example.studentdb.db.DatabaseConnection;
import com.example.studentdb.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE: Insert a new student
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, phone, dob, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPhone());
            stmt.setDate(4, student.getDob());
            stmt.setString(5, student.getAddress());

            stmt.executeUpdate();
            System.out.println("✅ Student added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
            e.printStackTrace();
        }
        return students;
    }

    // UPDATE: Update a student
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, email=?, phone=?, dob=?, address=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPhone());
            stmt.setDate(4, student.getDob());
            stmt.setString(5, student.getAddress());
            stmt.setInt(6, student.getId());

            stmt.executeUpdate();
            System.out.println("✅ Student updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: Delete a student
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Student deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
