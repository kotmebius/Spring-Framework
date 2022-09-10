package ru.khantemirov.dao;

import jakarta.persistence.EntityManager;
import ru.khantemirov.model.Customer;
import ru.khantemirov.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class CustomerRepository {
    private final EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Customer> findById(Long id) {
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Customer.class, id)));
    }

    public List<Customer> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllCustomers", Customer.class).getResultList());
    }

    public long count() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("countAllCustomers", Long.class).getSingleResult());
    }

    public void save(Customer customer) {
        executeInTransaction(entityManager -> {
            if (customer.getId() == null) {
                entityManager.persist(customer);
            } else {
                entityManager.merge(customer);
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
