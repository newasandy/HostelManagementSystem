package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "visitors")
public class VisitorModel extends BaseEntity {
    @Column(nullable = false)
    private Long student_id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String relation;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Date entry_time;

    @Column(nullable = false)
    private Date exit_time;

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    public Date getExit_time() {
        return exit_time;
    }

    public void setExit_time(Date exit_time) {
        this.exit_time = exit_time;
    }
}
