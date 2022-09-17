package ru.khantemirov.persist;

import java.util.Calendar;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter

public class Product {

    private Long id;

    @NotBlank(message="Name of product cant be empty")
    private String name;

    @Min(value = 1, message = "It cant be cost less then 1$")
    @Max(value = 10000, message = "It cant be cost more then 10000$")
    private int cost;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}