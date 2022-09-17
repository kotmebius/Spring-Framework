package ru.khantemirov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.khantemirov.model.dto.ProductDto;
import ru.khantemirov.model.mapper.ProductDtoMapper;
import ru.khantemirov.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductDtoMapper mapper;
    private final ProductRepository productRepository;

    public Page<ProductDto> findAllByFilter(String productFilter, BigDecimal costMin, BigDecimal costMax, int page, int size, String sortField) {
        productFilter = productFilter == null || productFilter.isBlank() ? null : "%" + productFilter.trim() + "%";
        costMin = costMin == null ? BigDecimal.ZERO : costMin;
        costMax = costMax == null ? null : costMax;
        return productRepository.productByFilter(productFilter, costMin, costMax, PageRequest.of(page, size, Sort.by(sortField)))
                .map(mapper::map);
    }

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public void save(ProductDto dto) {
        productRepository.save(mapper.map(dto));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
