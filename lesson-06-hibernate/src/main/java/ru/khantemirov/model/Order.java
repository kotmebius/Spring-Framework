package ru.khantemirov.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Orders")
@NamedQueries({
        @NamedQuery(name = "findAllOrders", query = "Select u from Order u"),
        @NamedQuery(name = "countAllOrders", query = "Select count(u) from Order u"),
        @NamedQuery(name = "deleteOrdersById", query = "delete from Order u where u.id = :id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade = CascadeType.ALL)
//    @Column(nullable = false, unique = false)
//    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
//    @Column(nullable = false, unique = false)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )

    private List <Product> products;

    @Column(nullable = false, unique = false)
    private Long cost;

    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
        this.cost = products.stream().map(x -> x.getCost()).reduce(0L, (x,y) -> x+y);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", cost=" + cost +
                '}';
    }
}
