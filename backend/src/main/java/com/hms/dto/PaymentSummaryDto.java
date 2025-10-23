package com.hms.dto;

import java.time.LocalDate;
import java.util.List;

public class PaymentSummaryDto {
    private Double totalPendingAmount;
    private Double thisMonthAmount;
    private Integer totalMealsThisMonth;
    private LocalDate nextPaymentDue;
    private List<PaymentHistoryDto> paymentHistory;
    private Double dailyRate = 120.0; // 2 meals per day

    // Constructors
    public PaymentSummaryDto() {}

    // Getters and Setters
    public Double getTotalPendingAmount() { return totalPendingAmount; }
    public void setTotalPendingAmount(Double totalPendingAmount) { this.totalPendingAmount = totalPendingAmount; }

    public Double getThisMonthAmount() { return thisMonthAmount; }
    public void setThisMonthAmount(Double thisMonthAmount) { this.thisMonthAmount = thisMonthAmount; }

    public Integer getTotalMealsThisMonth() { return totalMealsThisMonth; }
    public void setTotalMealsThisMonth(Integer totalMealsThisMonth) { this.totalMealsThisMonth = totalMealsThisMonth; }

    public LocalDate getNextPaymentDue() { return nextPaymentDue; }
    public void setNextPaymentDue(LocalDate nextPaymentDue) { this.nextPaymentDue = nextPaymentDue; }

    public List<PaymentHistoryDto> getPaymentHistory() { return paymentHistory; }
    public void setPaymentHistory(List<PaymentHistoryDto> paymentHistory) { this.paymentHistory = paymentHistory; }

    public Double getDailyRate() { return dailyRate; }
    public void setDailyRate(Double dailyRate) { this.dailyRate = dailyRate; }
}

class PaymentHistoryDto {
    private LocalDate paymentDate;
    private Double amount;
    private String status;
    private String month;

    // Constructors
    public PaymentHistoryDto() {}

    public PaymentHistoryDto(LocalDate paymentDate, Double amount, String status, String month) {
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.status = status;
        this.month = month;
    }

    // Getters and Setters
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
}