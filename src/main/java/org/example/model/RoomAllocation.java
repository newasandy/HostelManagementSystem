package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "room_allocation")
public class RoomAllocation extends BaseEntity {
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(name = "allocation_date", nullable = false)
    private Timestamp allocationDate;

    @Column(name = "unallocation_date")
    private Timestamp unallocationDate;

    public RoomAllocation() {
    }

    public RoomAllocation(Long studentId, Long roomId, Timestamp allocationDate, Timestamp unallocationDate) {
        this.studentId = studentId;
        this.roomId = roomId;
        this.allocationDate = allocationDate;
        this.unallocationDate = unallocationDate;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
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
