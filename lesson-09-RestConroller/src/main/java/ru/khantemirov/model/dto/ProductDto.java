package ru.khantemirov.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Setter
@Getter
@Table (name="Products")
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "can not be empty!!!")
    private String name;

    @Min(value = 1, message = "It cant be cost less then 1$")
    @Max(value = 100000, message = "It cant be cost more then 100000$")
    private BigDecimal cost;


    public ProductDto(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }
}
