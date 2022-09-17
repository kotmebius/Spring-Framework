package ru.khantemirov.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.khantemirov.model.Product;
import ru.khantemirov.model.dto.ProductDto;

@Mapper (componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDtoMapper {

    ProductDto map(Product product);

    @Mapping(target = "id", ignore = true)
    Product map(ProductDto dto);
}
