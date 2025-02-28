package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "leave_request")
public class LeaveRequestModel extends BaseEntity {
    @Column(nullable = false)
    private Long student_id;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Date start_date;

    @Column(nullable = false)
    private Date end_date;

    @Column(nullable = false, length = 25)
    private String status;

    public LeaveRequestModel() {
    }

    public LeaveRequestModel(Long student_id, String reason, Date start_date, Date end_date, String status) {
        this.student_id = student_id;
        this.reason = reason;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
