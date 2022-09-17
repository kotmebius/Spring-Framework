package ru.khantemirov.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.model.Product;
import ru.khantemirov.model.dto.ProductDto;
import ru.khantemirov.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductRestConroller {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> listPage(@RequestParam(required = false) String productFilter,
                                     @RequestParam(required = false) BigDecimal costMinFilter,
                                     @RequestParam(required = false) BigDecimal costMaxFilter,
                                     @RequestParam(required = false) Optional<Integer> page,
                                     @RequestParam(required = false) Optional<Integer> size,
                                     @RequestParam(required = false) Optional<String> sortField,
                                     Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(5);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");

        Page<ProductDto> allByFilter = productService.findAllByFilter(productFilter, costMinFilter,
                costMaxFilter, pageValue, sizeValue, sortFieldValue);
        return  allByFilter.get().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") long id, Model model) {
        ProductDto product = productService.findProductById(id).orElseThrow();
        return product;
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto product) {
//        if (product.getId() != null) {
//            throw new IllegalArgumentException("Created product shouldn't have id");
//        }
        productService.save(product);
        return product;
    }

}
