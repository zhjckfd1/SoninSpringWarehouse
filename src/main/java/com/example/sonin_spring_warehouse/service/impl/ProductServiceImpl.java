package com.example.sonin_spring_warehouse.service.impl;

import com.example.sonin_spring_warehouse.Constants;
import com.example.sonin_spring_warehouse.dto.PriceDto;
import com.example.sonin_spring_warehouse.dto.ProductDto;
import com.example.sonin_spring_warehouse.dto.ProductUpdateDto;
import com.example.sonin_spring_warehouse.exception.ProductAlreadyExistsException;
import com.example.sonin_spring_warehouse.exception.ProductNotFoundException;
import com.example.sonin_spring_warehouse.exception.ProductQuantityNotFoundException;
import com.example.sonin_spring_warehouse.model.Product;
import com.example.sonin_spring_warehouse.repository.ProductRepository;
import com.example.sonin_spring_warehouse.service.inter.ProductService;
import com.example.sonin_spring_warehouse.service.mapping.ProductDtoMapping;
import java.util.List;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapping productDtoMapping;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductDtoMapping productDtoMapping){
        this.productRepository = productRepository;
        this.productDtoMapping = productDtoMapping;
    }

    @Override
    @Transactional
    public void addProduct(ProductDto productDto) {
        if (productRepository.findByName(productDto.getName()).isEmpty()){
            productRepository.save(productDtoMapping.mapToEntity(productDto));
        }
        else
            throw new ProductAlreadyExistsException();
    }

    @Override
    @Transactional
    public void updateProduct(Long productId, ProductUpdateDto productUpdateDto) {
        productRepository.findById(productId).ifPresentOrElse(product ->{
            if (productUpdateDto.getName() != null){
                if (productRepository.findByName(productUpdateDto.getName()).isEmpty())
                    product.setName(productUpdateDto.getName());
                else throw new ProductAlreadyExistsException();
            }
            if (productUpdateDto.getDescription() != null){
                product.setDescription(productUpdateDto.getDescription());
            }
            if (productUpdateDto.getPrice() != null){
                product.setPrice(productUpdateDto.getPrice());
            }
        }, () -> {
            throw new ProductNotFoundException(productId);
        });
    }

    @Override
    @Transactional
    public List<ProductDto> getAll() {
        return productRepository.findAll()
                                .stream()
                                .map(productDtoMapping::mapToDto)
                                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto getProductById(Long productId) {
        return productDtoMapping
                .mapToDto(productRepository.findById(productId)
                                  .orElseThrow(() -> new ProductNotFoundException(productId)));
    }

    @Override
    @Transactional
    public ProductDto getProductByName(String name) {
        return productDtoMapping
                .mapToDto(productRepository.findByName(name)
                                           .orElseThrow(() -> new ProductNotFoundException(Constants.PRODUCT_BY_NAME_NOT_FOUND + name)));
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsByPartOfNameIgnoreCase(String part) {
        return productRepository.findByNameIgnoreCaseContaining(part)
                                .stream()
                                .map(productDtoMapping::mapToDto)
                                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsByPartOfNameIgnoreCaseIfProductPresent(String part) {
        return productRepository.findByNameIgnoreCaseContainingAndQuantityGreaterThan(part, 0)
                                .stream()
                                .map(productDtoMapping::mapToDto)
                                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void changeQuantity(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                                           .orElseThrow(() -> new ProductNotFoundException(productId));
        int itog = product.getQuantity() + quantity;
        if (itog >= 0) product.setQuantity(itog);
        else throw new ProductQuantityNotFoundException(Constants.CURRENT_QUANTITY_LEFT + product.getQuantity() + Constants.CURRENT_QUANTITY_RIGHT);
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsByPriceBetween(PriceDto priceDto) {
        return productRepository.findByPriceBetween(priceDto.getMin(), priceDto.getMax())
                                .stream()
                                .map(productDtoMapping::mapToDto)
                                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsByPriceBetweenIfProductPresent(PriceDto priceDto) {
        return productRepository.findByQuantityGreaterThanAndPriceBetween(0, priceDto.getMin(), priceDto.getMax())
                                .stream()
                                .map(productDtoMapping::mapToDto)
                                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsByQuantityGreaterThanEqual(int q) {
        return productRepository.findByQuantityGreaterThanEqual(q)
                                .stream()
                                .map(productDtoMapping::mapToDto)
                                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsIfProductPresent() {
        return productRepository.findByQuantityGreaterThan(0)
                                .stream()
                                .map(productDtoMapping::mapToDto)
                                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsIfProductNotPresent() {
        return productRepository.findByQuantityEquals(0)
                                .stream()
                                .map(productDtoMapping::mapToDto)
                                .collect(Collectors.toList());
    }

}
