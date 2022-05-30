package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);
        productRepository.insert(new Product("HDD", 2500));
        productRepository.insert(new Product("SSD", 3500));
        productRepository.insert(new Product("Ryzen3", 12000));
        productRepository.insert(new Product("Ryzen5", 25000));
        productRepository.insert(new Product("Ryzen7", 35000));
        productRepository.insert(new Product("Ryzen9", 120000));
        productRepository.insert(new Product("Flash Disk", 1500));
        productRepository.insert(new Product("Flash microSD", 1000));
        productRepository.insert(new Product("LCD монитор", 12000));
        productRepository.insert(new Product("GeForce RTX2070", 75000));
        productRepository.insert(new Product("Mouse", 350));
        productRepository.insert(new Product("Keyboard", 1000));

        CartService cartService = context.getBean("cartService", CartService.class);

        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            System.out.print("Введите команду (ADD, DELETE, NEW CART, SHOW)");
            String name = sc.nextLine();

            switch (name) {
                case "ADD":
                    System.out.println("Введите id Товара");
                    Long id = sc.nextLong();
                    System.out.println("Введите кол-во товара");
                    Integer count = sc.nextInt();
                    cartService.addProduct(id, count);
                    System.out.println("Товар добавлен");
                    break;
                case "DELETE":
                    System.out.println("Введите id Товара для удаления");
                    Long idToDel = sc.nextLong();
                    cartService.removeProduct(idToDel);
                    System.out.println("Товар удалён");
                    break;
                case "NEW CART":
                    cartService = context.getBean("cartService", CartService.class);
                    System.out.println("Новая корзина создана");
                    break;
                case "SHOW":
                    cartService.prinCart();
                    break;
            }
        }
    }
}
