package com.hms.dto;

public class LoginRequest {
    private String username;
    private String password;
    private String name;
    private String admissionNumber;
    private String role;

    // Constructors
    public LoginRequest() {}

    public LoginRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAdmissionNumber() { return admissionNumber; }
    public void setAdmissionNumber(String admissionNumber) { this.admissionNumber = admissionNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}