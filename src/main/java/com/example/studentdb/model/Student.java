package com.example.studentdb.model;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Date dob;
    private String address;

    // Constructor without id (for inserts)
    public Student(String name, String email, String phone, Date dob, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
    }

    // Constructor with id (for updates and reads)
    public Student(int id, String name, String email, String phone, Date dob, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Date getDob() { return dob; }
    public String getAddress() { return address; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDob(Date dob) { this.dob = dob; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + email + " | " + phone + " | " + dob + " | " + address;
    }
}
