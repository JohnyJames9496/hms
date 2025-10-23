package com.hms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_meals")
public class StudentMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;
    
    @Column(name = "is_opted")
    private Boolean isOpted = true;
    
    @Column(name = "skipped_date")
    private LocalDateTime skippedDate;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public StudentMeal() {}
    
    public StudentMeal(Student student, Meal meal) {
        this.student = student;
        this.meal = meal;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    
    public Meal getMeal() { return meal; }
    public void setMeal(Meal meal) { this.meal = meal; }
    
    public Boolean getIsOpted() { return isOpted; }
    public void setIsOpted(Boolean isOpted) { this.isOpted = isOpted; }
    
    public LocalDateTime getSkippedDate() { return skippedDate; }
    public void setSkippedDate(LocalDateTime skippedDate) { this.skippedDate = skippedDate; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}