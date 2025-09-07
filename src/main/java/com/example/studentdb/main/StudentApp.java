package com.example.studentdb.main;

import com.example.studentdb.dao.StudentDAO;
import com.example.studentdb.model.Student;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class StudentApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Student Database Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> {
                    System.out.println("👋 Exiting...");
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter DOB (yyyy-mm-dd): ");
        String dobInput = scanner.nextLine();
        Date dob = Date.valueOf(dobInput);
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Student student = new Student(name, email, phone, dob, address);
        studentDAO.addStudent(student);
    }

    private static void viewStudents() {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("⚠️ No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter new DOB (yyyy-mm-dd): ");
        String dobInput = scanner.nextLine();
        Date dob = Date.valueOf(dobInput);
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        Student student = new Student(id, name, email, phone, dob, address);
        studentDAO.updateStudent(student);
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        studentDAO.deleteStudent(id);
    }
}
