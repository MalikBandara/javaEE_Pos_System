package org.example.dao;

import org.example.entity.Customer;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    boolean save(T t);

    boolean update(T t);

    List<T> getAll();

    boolean delete(int customerId);

    int IdGenerate();
}
