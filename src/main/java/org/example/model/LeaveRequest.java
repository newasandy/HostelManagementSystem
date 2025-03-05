package org.example.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "leave_request")
public class LeaveRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Users studentId;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "apply_date", nullable = false)
    private Timestamp applyDate;

    @Column(name = "start_from", nullable = false, length = 25)
    private String startFrom;

    @Column(name = "leave_days", nullable = false , length = 25)
    private String leaveDays;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    public LeaveRequest() {
    }

    public LeaveRequest(Users studentId, String reason, Timestamp applyDate, String startFrom, String leaveDays, String status) {
        this.studentId = studentId;
        this.reason = reason;
        this.applyDate = applyDate;
        this.startFrom = startFrom;
        this.leaveDays = leaveDays;
        this.status = status;
    }

    public Users getStudentId() {
        return studentId;
    }

    public void setStudentId(Users studentId) {
        this.studentId = studentId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Timestamp applyDate) {
        this.applyDate = applyDate;
    }

    public String getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(String startFrom) {
        this.startFrom = startFrom;
    }

    public String getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(String leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
