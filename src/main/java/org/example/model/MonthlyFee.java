package org.example.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Year;

@Entity
@Table(name = "monthly_fee")
public class MonthlyFee extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Users studentId;

    @Column(name = "year", nullable = false)
    private Year year;

    @Column(name = "month", nullable = false , length = 20)
    private String Month;

    @Column(name = "issue_date", nullable = false)
    private Timestamp issueDate;

    @Column(name = "fee_amount", nullable = false)
    private double feeAmount;

    @Column(name = "paid", nullable = false)
    private double paid;

    @Column(name = "due", nullable = false)
    private double due;


    public MonthlyFee() {
    }

    public MonthlyFee(Users studentId, Year year, String month, Timestamp issueDate, double feeAmount, double paid, double due) {
        this.studentId = studentId;
        this.year = year;
        Month = month;
        this.issueDate = issueDate;
        this.feeAmount = feeAmount;
        this.paid = paid;
        this.due = due;
    }

    public Users getStudentId() {
        return studentId;
    }

    public void setStudentId(Users studentId) {
        this.studentId = studentId;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }
}
