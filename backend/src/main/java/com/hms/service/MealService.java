package com.hms.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class MealService {
    
    // In-memory storage for demo purposes
    // In a real application, this would be stored in database
    private Map<String, Boolean> mealPreferences = new HashMap<>();
    
    public void initializeDefaultMealsForStudent(Long studentId) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        
        // Initialize default meals for next 30 days
        for (int i = 0; i < 30; i++) {
            LocalDate date = tomorrow.plusDays(i);
            
            // By default, both breakfast and dinner are enabled
            setMealStatus(studentId, date, "breakfast", true);
            setMealStatus(studentId, date, "dinner", true);
        }
    }
    
    public boolean[] getMealStatusForDate(Long studentId, LocalDate date) {
        boolean breakfast = getMealStatus(studentId, date, "breakfast");
        boolean dinner = getMealStatus(studentId, date, "dinner");
        return new boolean[]{breakfast, dinner};
    }
    
    public void skipDay(Long studentId, LocalDate date) {
        setMealStatus(studentId, date, "breakfast", false);
        setMealStatus(studentId, date, "dinner", false);
    }
    
    public void enableDay(Long studentId, LocalDate date) {
        setMealStatus(studentId, date, "breakfast", true);
        setMealStatus(studentId, date, "dinner", true);
    }
    
    public void resetMealsToDefault(Long studentId) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        
        // Reset meals for next 30 days to default (enabled)
        for (int i = 0; i < 30; i++) {
            LocalDate date = tomorrow.plusDays(i);
            setMealStatus(studentId, date, "breakfast", true);
            setMealStatus(studentId, date, "dinner", true);
        }
    }
    
    public int getMonthlyMealCount(Long studentId) {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        
        int mealCount = 0;
        LocalDate current = startOfMonth;
        
        while (!current.isAfter(endOfMonth)) {
            boolean[] meals = getMealStatusForDate(studentId, current);
            if (meals[0]) mealCount++; // breakfast
            if (meals[1]) mealCount++; // dinner
            current = current.plusDays(1);
        }
        
        return mealCount;
    }
    
    public int getTodayMealCount() {
        // Calculate actual meals for today
        // Since we're using in-memory storage, return 0 for now
        // In a real database implementation, this would query actual meal records
        return 0;
    }
    
    private void setMealStatus(Long studentId, LocalDate date, String mealType, boolean enabled) {
        String key = studentId + "_" + date + "_" + mealType;
        mealPreferences.put(key, enabled);
    }
    
    private boolean getMealStatus(Long studentId, LocalDate date, String mealType) {
        String key = studentId + "_" + date + "_" + mealType;
        return mealPreferences.getOrDefault(key, true); // Default is enabled
    }
}