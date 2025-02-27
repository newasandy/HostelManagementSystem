package org.example.model;

public class RoomAllocationModel {
    private int room_allocation_id;
    private int student_id;
    private int room_id;
    private String room_allocation_date;

    public int getRoom_allocation_id() {
        return room_allocation_id;
    }

    public void setRoom_allocation_id(int room_allocation_id) {
        this.room_allocation_id = room_allocation_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_allocation_date() {
        return room_allocation_date;
    }

    public void setRoom_allocation_date(String room_allocation_date) {
        this.room_allocation_date = room_allocation_date;
    }
}
