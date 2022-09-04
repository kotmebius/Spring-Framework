package ru.khantemirov.persist;
import java.util.List;

public interface ProductRepository {

    Product findProductById(Long id);

    void addProduct(Product product);

    List<Product> findAll();

    Product save(Product product);

    void delete (long id);

}
