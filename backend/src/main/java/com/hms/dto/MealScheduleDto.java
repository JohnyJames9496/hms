package com.hms.dto;

import java.time.LocalDate;

public class MealScheduleDto {
    private LocalDate date;
    private Boolean isBreakfastEnabled;
    private Boolean isDinnerEnabled;
    private Boolean canModify; // Can modify if date is tomorrow or later
    private Double dailyCost; // 120 if both meals, 0 if skipped
    private String dayOfWeek;

    // Constructors
    public MealScheduleDto() {}

    public MealScheduleDto(LocalDate date, Boolean isBreakfastEnabled, Boolean isDinnerEnabled, Boolean canModify) {
        this.date = date;
        this.isBreakfastEnabled = isBreakfastEnabled;
        this.isDinnerEnabled = isDinnerEnabled;
        this.canModify = canModify;
        this.dailyCost = (isBreakfastEnabled && isDinnerEnabled) ? 120.0 : 0.0;
        this.dayOfWeek = date.getDayOfWeek().toString();
    }

    // Getters and Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Boolean getIsBreakfastEnabled() { return isBreakfastEnabled; }
    public void setIsBreakfastEnabled(Boolean isBreakfastEnabled) { this.isBreakfastEnabled = isBreakfastEnabled; }

    public Boolean getIsDinnerEnabled() { return isDinnerEnabled; }
    public void setIsDinnerEnabled(Boolean isDinnerEnabled) { this.isDinnerEnabled = isDinnerEnabled; }

    public Boolean getCanModify() { return canModify; }
    public void setCanModify(Boolean canModify) { this.canModify = canModify; }

    public Double getDailyCost() { return dailyCost; }
    public void setDailyCost(Double dailyCost) { this.dailyCost = dailyCost; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
}