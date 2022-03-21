package com.example.sonin_spring_warehouse.service.inter;

import com.example.sonin_spring_warehouse.dto.PriceDto;
import com.example.sonin_spring_warehouse.dto.ProductDto;
import com.example.sonin_spring_warehouse.dto.ProductUpdateDto;
import java.util.List;

public interface ProductService {

    void addProduct(ProductDto productDto);

    void updateProduct(Long productId, ProductUpdateDto productUpdateDto);

    void changeQuantity(Long productId, Integer quantity);

    List<ProductDto> getAll();

    ProductDto getProductById(Long productId);

    ProductDto getProductByName(String name);

    List<ProductDto> getProductsByPartOfNameIgnoreCase(String part);

    List<ProductDto> getProductsByPartOfNameIgnoreCaseIfProductPresent(String part);

    List<ProductDto> getProductsByPriceBetween(PriceDto priceDto);

    List<ProductDto> getProductsByPriceBetweenIfProductPresent(PriceDto priceDto);

    List<ProductDto> getProductsByQuantityGreaterThanEqual(int q);

    List<ProductDto> getProductsIfProductPresent();

    List<ProductDto> getProductsIfProductNotPresent();
}
