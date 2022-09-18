package ru.khantemirov.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.model.dto.ProductDto;
import ru.khantemirov.service.ProductService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductRestConroller {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> listPage(@RequestParam(required = false) String productFilter,
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
        return  allByFilter;
    }

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") long id, Model model) {
        ProductDto product = productService.findProductById(id).orElseThrow();
        return product;
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto product) {
        productService.save(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id){
        productService.deleteProductById(id);
    }



}
