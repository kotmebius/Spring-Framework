package ru.khantemirov.dao;

import jakarta.persistence.EntityManager;
import ru.khantemirov.model.Order;
import ru.khantemirov.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrderRepository {
    private final EntityManager entityManager;

    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Order> findById(Long id) {
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Order.class, id)));
    }

    public List<Order> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllOrders", Order.class).getResultList());
    }

    public long count() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("countAllOrders", Long.class).getSingleResult());
    }

    public void save(Order order) {
        executeInTransaction(entityManager -> {
            if (order.getId() == null) {
                entityManager.persist(order);
            } else {
                entityManager.merge(order);
            }
        });
    }


    public void delete(long id) {
        executeInTransaction(entityManager ->
                entityManager.createNamedQuery("deleteProductsById")
                        .setParameter("id", id)
                        .executeUpdate());
    }

    public boolean isInOrder (Order order, Product product){
        return order.getProducts().stream()
                .anyMatch(x -> x.equals(product));
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
