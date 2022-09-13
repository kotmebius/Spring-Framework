package ru.khantemirov.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

    @Query(value = "select * from products u " +
            "where ((:productFilter is null or u.name like :productFilter) and " +
            "(u.cost > :costMin) and (:costMax is null or u.cost< :costMax))", nativeQuery = true)
    List<Product> productByFilter(String productFilter, BigDecimal costMin, BigDecimal costMax);
}
