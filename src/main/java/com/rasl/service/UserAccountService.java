package com.rasl.service;


import java.util.List;

public interface UserAccountService<T> {

    List<T> list();
    T getById(Integer id);
    T save(T obj);
    void deleteById(Integer id);

}
