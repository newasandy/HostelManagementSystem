package org.example.daoInterface;

import org.example.model.UsersModel;

import java.util.List;

public interface UserDAO extends BaseDAO<UsersModel, Long>{
    UsersModel findByEmail(String Email);
    List<UsersModel> getUnallocatedUsers();
}
