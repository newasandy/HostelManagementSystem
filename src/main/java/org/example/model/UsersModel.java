package org.example.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UsersModel extends BaseEntity{
    @Column(nullable = false, length = 50)
    private String full_name;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String passwords;

    @Column(nullable = false, length = 20)
    private String roles;

    @Column(nullable = false)
    private boolean status;

    public UsersModel() {
    }

    public UsersModel(String full_name, String email, String passwords, String roles, boolean status) {
        this.full_name = full_name;
        this.email = email;
        this.passwords = passwords;
        this.roles = roles;
        this.status = status;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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
}
