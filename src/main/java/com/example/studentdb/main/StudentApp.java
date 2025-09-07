package com.example.studentdb.main;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.example.studentdb.dao.StudentDAO;
import com.example.studentdb.model.Student;

public class StudentApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAO();

    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static Date parseDate(String input) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(input);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("❌ Invalid date format! Please use yyyy-mm-dd.");
            return null;
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Student Database Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by Name");
            System.out.println("6. View Student by ID");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 ->
                    addStudent();
                case 2 ->
                    viewStudents();
                case 3 ->
                    updateStudent();
                case 4 ->
                    deleteStudent();
                case 5 ->
                    searchStudentByName();
                case 6 ->
                    viewStudentById();
                case 7 -> {
                    System.out.println("👋 Exiting...");
                    return;
                }
                default ->
                    System.out.println("❌ Invalid choice!");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("❌ Invalid email format!");
            return;
        }

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        if (!phone.matches("\\d{10}")) {
            System.out.println("❌ Invalid phone number! Must be 10 digits.");
            return;
        }

        System.out.print("Enter DOB (yyyy-mm-dd): ");
        String dobInput = scanner.nextLine();
        Date dob = parseDate(dobInput);
        if (dob == null) {
            return;
        }

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
        if (!phone.matches("\\d{10}")) { // exactly 10 digits
            System.out.println("❌ Invalid phone number! Must be 10 digits.");
            return;
        }

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

    private static void searchStudentByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        List<Student> students = studentDAO.findStudentsByName(name);
        if (students.isEmpty()) {
            System.out.println("⚠️ No students found with that name.");
        } else {
            students.forEach(System.out::println);
        }

    }

    private static void viewStudentById() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentDAO.getStudentById(id);
        if (student == null) {
            System.out.println("⚠️ No student found with ID " + id);
        } else {
            System.out.println(student);
        }
    }

}
