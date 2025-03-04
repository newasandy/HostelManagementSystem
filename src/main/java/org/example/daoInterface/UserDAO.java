package org.example.daoInterface;

import org.example.model.Users;

import java.util.List;

public interface UserDAO extends BaseDAO<Users, Long>{
    Users findByEmail(String Email);
    List<Users> getUnallocatedUsers();
    List<Users> getOnlyStudent();
}
