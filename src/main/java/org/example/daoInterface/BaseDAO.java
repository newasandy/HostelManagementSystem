package org.example.daoInterface;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO <T, ID extends Serializable> {
    boolean add(T entity);
    boolean update(T entity);
    boolean delete(ID id);
    T getById(ID id);
    List<T> getAll();
}
