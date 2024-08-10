package vn.edu.likelion.app.bookstore.service.serv;

import java.util.Optional;

public interface BaseCRUD<T> {

    T create(T entity);

    T update(Long id, T entity);

    void delete(Long id);

    Optional<T> findById(Long id);

    Iterable<T> findAll();

    Optional<T> findByName(String name);

}
