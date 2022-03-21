package com.example.sonin_spring_warehouse.service.inter;

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

    List<ProductDto> getProductByPartOfName(String part);

    //получение товаров в ценовом диапазоне (поиск товара по цене)?

    //поиск товаров в наличии (quantity>0)?

    //удаление отсутствующих товаров (quantity=0)?
}
