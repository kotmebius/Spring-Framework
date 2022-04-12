package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CartService {
    private ProductRepository productRepository;
    private Map<Product, Integer> cart;

    @Autowired
    public CartService(ProductRepository productRepository){
        this.productRepository = productRepository;
        this.cart = new HashMap<>();
    }

    public void addProduct(Long id, int count) {
        Product product = productRepository.findById(id);
        cart.putIfAbsent(product, count);
    }

    public void removeProduct (Long id){
        Product product = productRepository.findById(id);
        cart.remove(product);
    }

    public void prinCart (){
        cart.forEach((Product, Integer) -> System.out.println(Product+" Кол-во: "+Integer));
    }

}
