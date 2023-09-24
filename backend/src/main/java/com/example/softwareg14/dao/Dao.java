package com.example.softwareg14.dao;

import java.util.List;

public interface Dao<T> {
    T getById(int id);
    void create(T t);
    void update(T t);
    void delete(int id);
    List<T> getAll();
}
