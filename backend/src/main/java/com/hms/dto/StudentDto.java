package com.hms.dto;

import java.time.LocalDateTime;

public class StudentDto {
    private Long id;
    private String name;
    private String admissionNumber;
    private String email;
    private String phone;
    private String roomNumber;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private Double pendingAmount;
    private Integer totalMealsThisMonth;

    // Constructors
    public StudentDto() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Double getPendingAmount() { return pendingAmount; }
    public void setPendingAmount(Double pendingAmount) { this.pendingAmount = pendingAmount; }

    public Integer getTotalMealsThisMonth() { return totalMealsThisMonth; }
    public void setTotalMealsThisMonth(Integer totalMealsThisMonth) { this.totalMealsThisMonth = totalMealsThisMonth; }
}