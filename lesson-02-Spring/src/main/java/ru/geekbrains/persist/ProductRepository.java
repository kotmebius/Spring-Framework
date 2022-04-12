package ru.geekbrains.persist;

import org.springframework.context.annotation.Configuration;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(long id);
    void insert(Product product);

    void update(Product product);

    void delete(long id);

    long getCount();
}
