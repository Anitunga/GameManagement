package com.example.demo.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T save(T t);
    Optional<T> findById(Long id);
    List<T> findAll();
    void delete(T t);
}