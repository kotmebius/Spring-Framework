package ru.khantemirov.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "Products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "Select u from Product u"),
        @NamedQuery(name = "countAllProducts", query = "Select count(u) from Product u"),
        @NamedQuery(name = "deleteProductsById", query = "delete from Product u where u.id = :id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = false)
    private Long cost;

    @ManyToMany
//    @Column(nullable = true, unique = false)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List <Order> orders;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Long cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
