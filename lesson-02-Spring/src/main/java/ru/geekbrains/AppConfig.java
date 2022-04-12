package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;

@Configuration
public class AppConfig {

    @Bean
    public ProductRepository productRepository(){
        return new ProductRepositoryImpl();
    }


    @Bean
    @Scope("prototype")
    public CartService cartService(ProductRepository productRepository) {
        return new CartService(productRepository);
    }
}
