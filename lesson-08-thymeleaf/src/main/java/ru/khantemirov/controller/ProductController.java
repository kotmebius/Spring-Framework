package ru.khantemirov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.persist.Product;
import ru.khantemirov.persist.ProductRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;


@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String listPage(@RequestParam(required = false) String productFilter,
                           @RequestParam(required = false) BigDecimal costMinFilter,
                           @RequestParam(required = false) BigDecimal costMaxFilter,
                           Model model) {
        productFilter = productFilter == null || productFilter.isBlank() ? null : "%" + productFilter.trim() + "%";
        costMinFilter = costMinFilter == null ? BigDecimal.ZERO : costMinFilter;
        costMaxFilter = costMaxFilter == null ? null : costMaxFilter;
        model.addAttribute("products", productRepository.productByFilter(productFilter, costMinFilter, costMaxFilter));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("", BigDecimal.ZERO));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        productRepository.save(product);
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
