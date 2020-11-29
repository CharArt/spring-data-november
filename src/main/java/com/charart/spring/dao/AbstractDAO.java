package com.charart.spring.dao;

import java.util.List;

public interface AbstractDAO <T>{

    void create (T entity);

    List<T> getAll();

    T getById (long id);

    void update(long id, T entity);

    void  delete (long id);
}
