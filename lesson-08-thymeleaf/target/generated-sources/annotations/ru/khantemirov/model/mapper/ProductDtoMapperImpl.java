package ru.khantemirov.model.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.khantemirov.model.Product;
import ru.khantemirov.model.dto.ProductDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-18T20:48:12+0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class ProductDtoMapperImpl implements ProductDtoMapper {

    @Override
    public ProductDto map(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        if ( product.getId() != null ) {
            productDto.setId( product.getId() );
        }
        if ( product.getName() != null ) {
            productDto.setName( product.getName() );
        }
        if ( product.getCost() != null ) {
            productDto.setCost( product.getCost() );
        }

        return productDto;
    }

    @Override
    public Product map(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        if ( dto.getName() != null ) {
            product.setName( dto.getName() );
        }
        if ( dto.getCost() != null ) {
            product.setCost( dto.getCost() );
        }

        return product;
    }
}
