package no.accelerate.chinook.repositories;

import no.accelerate.chinook.models.Customer;

import java.util.List;

public interface CRUDRepository<T, U> {

    List<T> findAll();
    T findById(Long id);
    int insert(T object);
    int update(T object);
    int delete(T object);
    int deleteById(U id);
}
