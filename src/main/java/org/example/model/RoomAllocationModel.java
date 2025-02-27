package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "room_allocation")
public class RoomAllocationModel extends BaseEntity {
    @Column(nullable = false)
    private Long student_id;

    @Column(nullable = false)
    private Long room_id;

    @Column(nullable = false)
    private Date room_allocation_date;

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

    public Date getRoom_allocation_date() {
        return room_allocation_date;
    }

    public void setRoom_allocation_date(Date room_allocation_date) {
        this.room_allocation_date = room_allocation_date;
    }
}
