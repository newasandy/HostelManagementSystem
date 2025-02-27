package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class RoomModel extends BaseEntity{
    @Column(nullable = false)
    private int room_number;

    @Column(nullable = false)
    private int capacity;

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
