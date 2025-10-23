package com.hms.service;

import com.hms.dto.MealScheduleDto;
import com.hms.dto.PaymentSummaryDto;
import com.hms.entity.Student;
import com.hms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MealService mealService;
    
    @Autowired
    private PaymentService paymentService;

    public List<MealScheduleDto> getMealSchedule(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<MealScheduleDto> schedule = new ArrayList<>();
        LocalDate today = LocalDate.now();
        
        // Show schedule for next 14 days
        for (int i = 0; i < 14; i++) {
            LocalDate date = today.plusDays(i);
            boolean canModify = date.isAfter(today); // Can only modify future dates
            
            // Get meal status for this date
            boolean[] mealStatus = mealService.getMealStatusForDate(studentId, date);
            boolean isBreakfastEnabled = mealStatus[0];
            boolean isDinnerEnabled = mealStatus[1];
            
            MealScheduleDto daySchedule = new MealScheduleDto(date, isBreakfastEnabled, isDinnerEnabled, canModify);
            schedule.add(daySchedule);
        }
        
        return schedule;
    }

    public void skipEntireDay(Long studentId, LocalDate date) {
        LocalDate today = LocalDate.now();
        
        // Can only skip future dates
        if (!date.isAfter(today)) {
            throw new RuntimeException("Can only skip meals for future dates");
        }
        
        // Skip both breakfast and dinner for the day
        mealService.skipDay(studentId, date);
    }

    public void enableEntireDay(Long studentId, LocalDate date) {
        LocalDate today = LocalDate.now();
        
        // Can only enable future dates
        if (!date.isAfter(today)) {
            throw new RuntimeException("Can only enable meals for future dates");
        }
        
        // Enable both breakfast and dinner for the day
        mealService.enableDay(studentId, date);
    }

    public PaymentSummaryDto getPaymentSummary(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        PaymentSummaryDto summary = new PaymentSummaryDto();
        
        // Calculate this month's meals and amount
        int thisMonthMeals = mealService.getMonthlyMealCount(studentId);
        double thisMonthAmount = thisMonthMeals * 60.0; // Each meal is ₹60
        
        summary.setTotalMealsThisMonth(thisMonthMeals);
        summary.setThisMonthAmount(thisMonthAmount);
        
        // Check payment status
        String paymentStatus = paymentService.getPaymentStatus(studentId);
        if ("confirmed".equals(paymentStatus)) {
            summary.setTotalPendingAmount(0.0); // Payment confirmed
        } else {
            summary.setTotalPendingAmount(thisMonthAmount); // Still pending
        }
        
        // Set next payment due (end of current month)
        LocalDate today = LocalDate.now();
        LocalDate monthEnd = today.withDayOfMonth(today.lengthOfMonth());
        summary.setNextPaymentDue(monthEnd);
        
        // Get payment history - for now, return empty list
        summary.setPaymentHistory(new ArrayList<>());
        
        return summary;
    }

    public void makePayment(Long studentId, Double amount, String status) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Create a payment record with pending verification status
        Long paymentId = paymentService.createPayment(
            studentId, 
            student.getName(), 
            student.getAdmissionNumber(), 
            amount
        );
        
        System.out.println("Payment created with ID: " + paymentId + " for student: " + student.getName());
    }
}