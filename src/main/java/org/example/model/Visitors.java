package org.example.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "visitors")
public class Visitors extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Users studentId;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "relation", nullable = false, length = 50)
    private String relation;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "entry_datetime", nullable = false)
    private Timestamp entryDatetime;

    @Column(name = "exit_datetime")
    private Timestamp exitDatetime;


    public Visitors() {
    }

    public Visitors(Users studentId, String fullName, String relation, String reason, Timestamp entryDatetime, Timestamp exitDatetime) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.relation = relation;
        this.reason = reason;
        this.entryDatetime = entryDatetime;
        this.exitDatetime = exitDatetime;
    }

    public Users getStudentId() {
        return studentId;
    }

    public void setStudentId(Users studentId) {
        this.studentId = studentId;
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

    public Timestamp getEntryDatetime() {
        return entryDatetime;
    }

    public void setEntryDatetime(Timestamp entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public Timestamp getExitDatetime() {
        return exitDatetime;
    }

    public void setExitDatetime(Timestamp exitDatetime) {
        this.exitDatetime = exitDatetime;
    }
}
