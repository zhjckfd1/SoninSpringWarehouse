package com.example.sonin_spring_warehouse.controller;

import com.example.sonin_spring_warehouse.dto.PriceDto;
import com.example.sonin_spring_warehouse.dto.ProductDto;
import com.example.sonin_spring_warehouse.dto.ProductUpdateDto;
import com.example.sonin_spring_warehouse.service.inter.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> readAll() {
        final List<ProductDto> products = productService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ProductDto> readById(
            @PathVariable(name = "id") Long id) {
        final ProductDto productDto = productService.getProductById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping(value = "/findByName/{name}")
    public ResponseEntity<ProductDto> readByName(
            @PathVariable(name = "name") String name) {
        final ProductDto productDto = productService.getProductByName(name);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping(value = "/findByPartOfNameIgnoreCase/{part}")
    public ResponseEntity<List<ProductDto>> readByPartOfName(
            @PathVariable(name = "part") String part) {
        final List<ProductDto> products = productService.getProductsByPartOfNameIgnoreCase(part);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/findByPartOfNameIgnoreCaseIfProductPresent/{part}")
    public ResponseEntity<List<ProductDto>> readByPartOfNameIgnoreCaseIfProductPresent(
            @PathVariable(name = "part") String part) {
        final List<ProductDto> products = productService.getProductsByPartOfNameIgnoreCaseIfProductPresent(part);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PatchMapping(value = "/updateById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeProduct(
            @PathVariable(name = "id") Long productId,
            @RequestBody ProductUpdateDto productUpdateDto) {
        productService.updateProduct(productId, productUpdateDto);
    }

    @PatchMapping(value = "/changeProductQuantityById/{id}/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void changeProductQuantity(
            @PathVariable(name = "id") Long productId,
            @PathVariable(name = "quantity") Integer quantity) {
        productService.changeQuantity(productId, quantity);
    }

    @GetMapping(value = "/findProductsByPriceBetween")
    public ResponseEntity<List<ProductDto>> readByPriceBetween(
            @RequestBody PriceDto priceDto) {
        final List<ProductDto> products = productService.getProductsByPriceBetween(priceDto);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/findProductsByPriceBetweenIfProductPresent")
    public ResponseEntity<List<ProductDto>> readByPriceBetweenIfProductPresent(
            @RequestBody PriceDto priceDto) {
        final List<ProductDto> products = productService.getProductsByPriceBetweenIfProductPresent(priceDto);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/findProductsByQuantityGreaterThanEqual/{quantity}")
    public ResponseEntity<List<ProductDto>> readByQuantityGreaterThanEqual(
            @PathVariable(name = "quantity") Integer quantity) {
        final List<ProductDto> products = productService.getProductsByQuantityGreaterThanEqual(quantity);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/findProductsByQuantityIfProductPresent")
    public ResponseEntity<List<ProductDto>> readByQuantityIfProductPresent(){
        final List<ProductDto> products = productService.getProductsIfProductPresent();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/findProductsByQuantityIfProductNotPresent")
    public ResponseEntity<List<ProductDto>> readByQuantityIfProductNotPresent(){
        final List<ProductDto> products = productService.getProductsIfProductNotPresent();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
