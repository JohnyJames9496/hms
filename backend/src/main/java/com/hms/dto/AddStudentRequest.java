package com.hms.dto;

public class AddStudentRequest {
    private String name;
    private String admissionNumber;
    private String email;
    private String phone;
    private String roomNumber;

    // Constructors
    public AddStudentRequest() {}

    public AddStudentRequest(String name, String admissionNumber) {
        this.name = name;
        this.admissionNumber = admissionNumber;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAdmissionNumber() { return admissionNumber; }
    public void setAdmissionNumber(String admissionNumber) { this.admissionNumber = admissionNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
}