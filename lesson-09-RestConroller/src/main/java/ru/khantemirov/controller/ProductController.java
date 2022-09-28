package ru.khantemirov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.model.Product;
import ru.khantemirov.model.dto.ProductDto;
import ru.khantemirov.service.ProductService;

import java.math.BigDecimal;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping(value={"", "/", "/product"})
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String listPage(@RequestParam(required = false) String productFilter,
                           @RequestParam(required = false) BigDecimal costMinFilter,
                           @RequestParam(required = false) BigDecimal costMaxFilter,
                           @RequestParam(required = false) Optional<Integer> page,
                           @RequestParam(required = false) Optional<Integer> size,
                           @RequestParam(required = false) Optional<String> sortField,
                           Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(4);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");

        model.addAttribute("products", productService.findAllByFilter(productFilter, costMinFilter,
                costMaxFilter, pageValue, sizeValue, sortFieldValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("", BigDecimal.ZERO));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        productService.deleteProductById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(ProductDto product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(ProductDto product) {
        productService.save(product);
        return "redirect:/product";
    }

//    @PostConstruct
//    public void init() {
//        productRepository.save(new Product("Mouse", BigDecimal.valueOf(300)));
//        productRepository.save(new Product("HDD", BigDecimal.valueOf(3000)));
//        productRepository.save(new Product("SSD", BigDecimal.valueOf(5000)));
//        productRepository.save(new Product("MotherBoard", BigDecimal.valueOf(8000)));
//        productRepository.save(new Product("CPU", BigDecimal.valueOf(18000)));
//        productRepository.save(new Product("GPU", BigDecimal.valueOf(3000)));
//        productRepository.save(new Product("Keyboard", BigDecimal.valueOf(1000)));
//        productRepository.save(new Product("LCD", BigDecimal.valueOf(12000)));
//        productRepository.save(new Product("MFU", BigDecimal.valueOf(17500)));
//    }


}
