package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "room_allocation")
public class RoomAllocationModel extends BaseEntity {
    @Column(nullable = false)
    private Long student_id;

    @Column(nullable = false)
    private Long room_id;

    @Column(nullable = false)
    private Timestamp allocation_date;

    @Column
    private Timestamp unallocation_date;

    public RoomAllocationModel() {
    }


    public RoomAllocationModel(Long student_id, Long room_id, Timestamp allocation_date, Timestamp unallocation_date) {
        this.student_id = student_id;
        this.room_id = room_id;
        this.allocation_date = allocation_date;
        this.unallocation_date = unallocation_date;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Date getAllocation_date() {
        return allocation_date;
    }

    public void setAllocation_date(Timestamp allocation_date) {
        this.allocation_date = allocation_date;
    }

    public Date getUnallocation_date() {
        return unallocation_date;
    }

    public void setUnallocation_date(Timestamp unallocation_date) {
        this.unallocation_date = unallocation_date;
    }
}
