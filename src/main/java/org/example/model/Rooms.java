package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Rooms extends BaseEntity{
    @Column(name = "room_number", unique = true, nullable = false)
    private int roomNumber;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "status", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "roomId", cascade =CascadeType.ALL)
    private List<RoomAllocation> roomAllocations;

    public Rooms() {
    }

    public Rooms(int roomNumber, int capacity, boolean status, List<RoomAllocation> roomAllocations) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = status;
        this.roomAllocations = roomAllocations;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<RoomAllocation> getRoomAllocations() {
        return roomAllocations;
    }

    public void setRoomAllocations(List<RoomAllocation> roomAllocations) {
        this.roomAllocations = roomAllocations;
    }
}
