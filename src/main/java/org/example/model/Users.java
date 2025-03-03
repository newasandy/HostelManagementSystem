package org.example.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Users extends BaseEntity{
    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "passwords", nullable = false, length = 100)
    private String passwords;

    @Column(name = "roles", nullable = false, length = 20)
    private String roles;

    @Column(name = "status", nullable = false)
    private boolean status;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "studentId")
    private List<LeaveRequest> leaveRequests;

    @OneToMany(mappedBy = "studentId")
    private List<RoomAllocation> roomAllocations;

    @OneToMany(mappedBy = "studentId")
    private List<Visitors> visitors;

    public Users() {
    }

    public Users(String fullName, String email, String passwords, String roles, boolean status) {
        this.fullName = fullName;
        this.email = email;
        this.passwords = passwords;
        this.roles = roles;
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public List<RoomAllocation> getRoomAllocations() {
        return roomAllocations;
    }

    public void setRoomAllocations(List<RoomAllocation> roomAllocations) {
        this.roomAllocations = roomAllocations;
    }

    public List<Visitors> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitors> visitors) {
        this.visitors = visitors;
    }
}
