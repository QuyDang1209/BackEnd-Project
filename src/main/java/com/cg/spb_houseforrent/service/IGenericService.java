package com.cg.spb_houseforrent.service;

import java.util.Optional;

public interface IGenericService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    Optional<T> findByEmail(String email);

    T save(T t);

    void remove(Long id);
}
