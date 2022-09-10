package ru.khantemirov.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Customers")
@NamedQueries({
        @NamedQuery(name = "findAllCustomers", query = "Select u from Customer u"),
        @NamedQuery(name = "countAllCustomers", query = "Select count(u) from Customer u"),
        @NamedQuery(name = "deleteCustomerById", query = "delete from Customer u where u.id = :id")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column (nullable = true, unique = false)
    private List <Order> orders;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
