package ru.khantemirov.persist;

import java.math.BigDecimal;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table (name="Products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = false)
    private BigDecimal cost;


    public Product(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }
}
