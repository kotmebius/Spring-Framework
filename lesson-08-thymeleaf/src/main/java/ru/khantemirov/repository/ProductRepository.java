package ru.khantemirov.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.khantemirov.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    Page<Product> findAllByProductLike(String usernameFilter, Pageable pageable);

    @Query( value = "select * from products u " +
            "where ((:productFilter is null or u.name like :productFilter) and " +
            "(u.cost > :costMin) and (:costMax is null or u.cost< :costMax))",
            countQuery = "select count(*) from products u " +
                    "where ((:productFilter is null or u.name like :productFilter) and " +
                    "(u.cost > :costMin) and (:costMax is null or u.cost< :costMax))",
            nativeQuery = true)
    Page<Product> productByFilter(String productFilter, BigDecimal costMin, BigDecimal costMax, Pageable pageable);
}
