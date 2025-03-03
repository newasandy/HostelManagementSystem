package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "visitors")
public class VisitorModel extends BaseEntity {
    @Column(nullable = false)
    private Long student_id;

    @Column(nullable = false, length = 50)
    private String full_name;

    @Column(nullable = false, length = 50)
    private String relation;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Timestamp entry_datetime;

    @Column(nullable = false)
    private Timestamp exit_datetime;


    public VisitorModel() {
    }

    public VisitorModel(Long student_id, String full_name, String relation, String reason, Timestamp entry_datetime, Timestamp exit_datetime) {
        this.student_id = student_id;
        this.full_name = full_name;
        this.relation = relation;
        this.reason = reason;
        this.entry_datetime = entry_datetime;
        this.exit_datetime = exit_datetime;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    public Date getEntry_datetime() {
        return entry_datetime;
    }

    public void setEntry_datetime(Timestamp entry_datetime) {
        this.entry_datetime = entry_datetime;
    }

    public Date getExit_datetime() {
        return exit_datetime;
    }

    public void setExit_datetime(Timestamp exit_datetime) {
        this.exit_datetime = exit_datetime;
    }
}
