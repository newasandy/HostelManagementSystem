package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "leave_request")
public class LeaveRequest extends BaseEntity {
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

    @Column(name = "status", nullable = false, length = 25)
    private String status;

    public LeaveRequest() {
    }

    public LeaveRequest(Long studentId, String reason, Timestamp startDate, Timestamp endDate, String status) {
        this.studentId = studentId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
