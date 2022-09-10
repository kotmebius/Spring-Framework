package ru.khantemirov;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.khantemirov.dao.CustomerRepository;
import ru.khantemirov.dao.OrderRepository;
import ru.khantemirov.dao.ProductRepository;
import ru.khantemirov.model.Customer;
import ru.khantemirov.model.Order;
import ru.khantemirov.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ProductRepository productRepository = new ProductRepository(entityManager);
        CustomerRepository customerRepository = new CustomerRepository(entityManager);
        OrderRepository orderRepository = new OrderRepository(entityManager);
        CustomersProductsService customersProductsService = new CustomersProductsService(entityManager);

//        productRepository.save(new Product("Mouse", 300L));
//        productRepository.save(new Product("HDD", 3000L));
//        productRepository.save(new Product("SSD", 5000L));
//        productRepository.save(new Product("MotherBoard", 8000L));
//        productRepository.save(new Product("CPU", 18000L));
//        productRepository.save(new Product("GPU", 3000L));
//        productRepository.save(new Product("Keyboard", 1000L));
//        productRepository.save(new Product("LCD", 12000L));
//        productRepository.save(new Product("MFU", 17500L));
//
//        customerRepository.save(new Customer("Yozheg"));
//        customerRepository.save(new Customer("Belka"));
//        customerRepository.save(new Customer("Vedmed"));
//
//        List <Product> products = new ArrayList <>();
//
//        products.add(productRepository.findById(2L).orElseThrow());
//        products.add(productRepository.findById(6L).orElseThrow());
//        orderRepository.save(new Order(customerRepository.findById(1L).orElseThrow(), products));
//
//        List <Product> products2 = new ArrayList <>();
//
//        products2.add(productRepository.findById(1L).orElseThrow());
//        products2.add(productRepository.findById(5L).orElseThrow());
//        products2.add(productRepository.findById(7L).orElseThrow());
//        orderRepository.save(new Order(customerRepository.findById(3L).orElseThrow(), products2));
//
//        List <Product> products3 = new ArrayList <>();
//
//        products3.add(productRepository.findById(2L).orElseThrow());
//        products3.add(productRepository.findById(6L).orElseThrow());
//        products3.add(productRepository.findById(7L).orElseThrow());
//        products3.add(productRepository.findById(3L).orElseThrow());
//        orderRepository.save(new Order(customerRepository.findById(2L).orElseThrow(), products3));
//
//        List <Product> products4 = new ArrayList <>();
//
//        products4.add(productRepository.findById(1L).orElseThrow());
//        products4.add(productRepository.findById(5L).orElseThrow());
//        products4.add(productRepository.findById(7L).orElseThrow());
//        products4.add(productRepository.findById(9L).orElseThrow());
//        products4.add(productRepository.findById(8L).orElseThrow());
//        products4.add(productRepository.findById(6L).orElseThrow());
//        orderRepository.save(new Order(customerRepository.findById(1L).orElseThrow(), products4));
//
//
//        System.out.println(customersProductsService.CustomerToProducts(customerRepository.findById(1L).orElseThrow()));
//        System.out.println(customersProductsService.CustomerToProducts(customerRepository.findById(2L).orElseThrow()));
//
//        System.out.println(customersProductsService.ProductToCustomers(productRepository.findById(1L).orElseThrow()));
//        System.out.println(customersProductsService.ProductToCustomers(productRepository.findById(6L).orElseThrow()));

//        System.out.println(customersProductsService.ordersCost(customerRepository.findById(1L).orElseThrow()));

        entityManager.close();
        entityManagerFactory.close();
    }
}
