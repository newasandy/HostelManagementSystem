package org.example.daoInterface;

import org.example.model.UsersModel;

public interface UserDAO extends BaseDAO<UsersModel, Long>{
    UsersModel findByEmail(String Email);
}
