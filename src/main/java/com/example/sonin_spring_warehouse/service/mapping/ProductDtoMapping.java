package com.example.sonin_spring_warehouse.service.mapping;

import com.example.sonin_spring_warehouse.dto.ProductDto;
import com.example.sonin_spring_warehouse.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductDtoMapping {
    public Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return product;
    }

    public ProductDto mapToDto(Product entity) {
        ProductDto productDto = new ProductDto();
        productDto.setName(entity.getName());
        productDto.setDescription(entity.getDescription());
        productDto.setPrice(entity.getPrice());
        productDto.setQuantity(entity.getQuantity());
        return productDto;
    }

}
