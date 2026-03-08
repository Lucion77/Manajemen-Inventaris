package com.wms.repository;

import java.util.List;

/**
 * Antarmuka (Interface) generic untuk pattern Repository.
 */
public interface Repository<T> {
    void save(T item);
    List<T> findAll();
    
    // Polimorfisme (Overloading): dua method dengan nama sama tapi argumen berbeda
    T findById(int id);
    List<T> findByName(String name);
    
    void update(T item);
    void delete(int id);
}
