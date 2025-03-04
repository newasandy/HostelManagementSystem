package org.example.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "room_allocation")
public class RoomAllocation extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Users studentId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Rooms roomId;

    @Column(name = "allocation_date", nullable = false)
    private Timestamp allocationDate;

    @Column(name = "unallocation_date")
    private Timestamp unallocationDate;

    public RoomAllocation() {
    }

    public RoomAllocation(Users studentId, Rooms roomId, Timestamp allocationDate, Timestamp unallocationDate) {
        this.studentId = studentId;
        this.roomId = roomId;
        this.allocationDate = allocationDate;
        this.unallocationDate = unallocationDate;
    }

    public Users getStudentId() {
        return studentId;
    }

    public void setStudentId(Users studentId) {
        this.studentId = studentId;
    }

    public Rooms getRoomId() {
        return roomId;
    }

    public void setRoomId(Rooms roomId) {
        this.roomId = roomId;
    }

    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Timestamp allocationDate) {
        this.allocationDate = allocationDate;
    }

    public Date getUnallocationDate() {
        return unallocationDate;
    }

    public void setUnallocationDate(Timestamp unallocationDate) {
        this.unallocationDate = unallocationDate;
    }
}
