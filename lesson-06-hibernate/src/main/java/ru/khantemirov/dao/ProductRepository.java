package ru.khantemirov.dao;

import jakarta.persistence.EntityManager;
import ru.khantemirov.model.Product;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductRepository {

    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Product> findById(Long id) {
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Product.class, id)));
    }

    public List<Product> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllProducts", Product.class).getResultList());
    }

    public long count() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("countAllProducts", Long.class).getSingleResult());
    }

    public void save(Product product) {
        executeInTransaction(entityManager -> {
            if (product.getId() == null) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
        });
    }

    public void delete(long id) {
        executeInTransaction(entityManager ->
                entityManager.createNamedQuery("deleteProductsById")
                        .setParameter("id", id)
                        .executeUpdate());
    }


    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
            return function.apply(entityManager);
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        try {
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}
