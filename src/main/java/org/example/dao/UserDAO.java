package org.example.dao;

import org.example.model.UsersModel;

import java.util.List;

public interface UserDAO {
    void addUser(UsersModel user);
    void updateUser(UsersModel user);
    void deleteUser(Long userId);
    UsersModel getUserById(Long userId);
    List<UsersModel> getAllUsers();
}
