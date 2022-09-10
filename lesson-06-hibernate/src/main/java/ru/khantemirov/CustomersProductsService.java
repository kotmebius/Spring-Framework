package ru.khantemirov;


import jakarta.persistence.EntityManager;
import ru.khantemirov.dao.CustomerRepository;
import ru.khantemirov.dao.OrderRepository;
import ru.khantemirov.dao.ProductRepository;
import ru.khantemirov.model.Customer;
import ru.khantemirov.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersProductsService {
    private EntityManager entityManager;
    OrderRepository orderRepository;

    public CustomersProductsService(EntityManager entityManager) {
        this.entityManager = entityManager;
        orderRepository = new OrderRepository(entityManager);
    }

    public List<Product> CustomerToProducts(Customer customer) {
        List<Product> products = new ArrayList<>();
        orderRepository.findAll().stream()
                .filter(x -> x.getCustomer() == customer)
                .forEach(x -> products.addAll(x.getProducts()));
        return products.stream()
                .distinct().collect(Collectors.toList());
    }

    public List<Customer> ProductToCustomers(Product product) {
        List<Product> products = new ArrayList<>();

        return orderRepository.findAll().stream()
                .filter(x -> orderRepository.isInOrder(x, product))
                .map(x -> x.getCustomer())
                .distinct()
                .collect(Collectors.toList());
    }

    public List <Long> ordersCost (Customer customer){
        return orderRepository.findAll().stream()
                .filter(x -> x.getCustomer() == customer)
                .map(x -> x.getCost())
                .collect(Collectors.toList());
    }

}
