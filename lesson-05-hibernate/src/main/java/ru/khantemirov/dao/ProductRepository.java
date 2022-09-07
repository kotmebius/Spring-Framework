package ru.khantemirov.dao;
import ru.khantemirov.model.Product;

import java.util.List;

public interface ProductRepository {

    Product findById(Long id);

    List<Product> findAll();

    void addProduct(Product product);

    void save(Product product);

    void delete (long id);

}
