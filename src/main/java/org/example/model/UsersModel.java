package org.example.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UsersModel extends BaseEntity{
    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwords;

    @Column(nullable = false)
    private String roles;

    public UsersModel() {
    }

    public UsersModel(String fullName, String email, String passwords, String roles) {
        this.fullName = fullName;
        this.email = email;
        this.passwords = passwords;
        this.roles = roles;
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
}
