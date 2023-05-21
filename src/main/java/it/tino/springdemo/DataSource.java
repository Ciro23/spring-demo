package it.tino.springdemo;

import java.util.List;
import java.util.Optional;

public interface DataSource<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    void deleteById(ID id);
}
