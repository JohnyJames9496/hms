package com.hms.controller;

import com.hms.dto.AddStudentRequest;
import com.hms.dto.StudentDto;
import com.hms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody AddStudentRequest request) {
        try {
            StudentDto student = adminService.addStudent(request);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = adminService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/dashboard/stats")
    public ResponseEntity<?> getDashboardStats() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        try {
            adminService.deleteStudent(studentId);
            return ResponseEntity.ok("{\"message\": \"Student deleted successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/meals/reset")
    public ResponseEntity<?> resetAllMeals() {
        adminService.resetAllMealsToDefault();
        return ResponseEntity.ok("{\"message\": \"All meals reset to default successfully\"}");
    }

    @GetMapping("/payments/pending")
    public ResponseEntity<?> getPendingPayments() {
        return ResponseEntity.ok(adminService.getPendingPayments());
    }

    @PostMapping("/payments/{paymentId}/verify")
    public ResponseEntity<?> verifyPayment(@PathVariable Long paymentId) {
        try {
            adminService.verifyPayment(paymentId);
            return ResponseEntity.ok("{\"message\": \"Payment verified successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}