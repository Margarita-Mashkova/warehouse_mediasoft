package com.warehouse.controller;

import com.warehouse.dto.ProductDto;
import com.warehouse.mapper.ProductMapper;
import com.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * A web application controller that handles HTTP requests related to the <b>Product</b> entity.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    /**
     * Search for the appropriate bean of the ProductService type and inject it in this field.
     */
    @Autowired
    ProductService productService;
    /**
     * Search for the appropriate bean of the ProductMapper type and inject it in this field.
     */
    @Autowired
    ProductMapper productMapper;

    /**
     * Processes the GET request along the path <i>/product/products</i> and returns a list of all products
     * as a list of ProductDto objects.
     *
     * @return a list of ProductDto objects.
     */
    @GetMapping("/products")
    public List<ProductDto> findAllProducts() {
        return productService.findAllProducts()
                .stream()
                .map(product -> productMapper.toProductDto(product))
                .toList();
    }

    /**
     * Processes a GET request along the path <i>/product/{id}</i> and returns the product for the passed UUID.
     *
     * @param id - UUID of product.
     * @return the ProductDto object for the passed product UUID.
     */
    @GetMapping("/{id}")
    public ProductDto findProduct(@PathVariable UUID id) {
        return productMapper.toProductDto(productService.findProduct(id));
    }

    /**
     * Processes the POST request along the path <i>/product</i> and creates a new product with the passed params.
     *
     * @param name        - product name.
     * @param description - product description.
     * @param categoryId  - category id to which the added product belongs.
     * @param price       - product price in rubles.
     * @param amount      - amount of product in warehouse.
     * @return a ProductDto object for the created product.
     */
    @PostMapping
    public ProductDto createProduct(@RequestParam String name, @RequestParam String description,
                                    @RequestParam Long categoryId, @RequestParam float price, @RequestParam int amount) {
        return productMapper.toProductDto(productService.addProduct(name, description, categoryId, price, amount));
    }

    /**
     * Processes the PUT request along the path <i>/product/{id}</i> and changes the fields of the product with the passed UUID.
     *
     * @param id          - UUID of the product that needs to be changed.
     * @param name        - new product name.
     * @param description - new product description.
     * @param categoryId  - new category id to which the edited product belongs.
     * @param price       - new product price in rubles.
     * @param amount      - new amount of product in warehouse.
     * @return a ProductDto object for the edited product.
     */
    @PutMapping("/{id}")
    public ProductDto editProduct(@PathVariable UUID id, @RequestParam String name, @RequestParam String description,
                                  @RequestParam Long categoryId, @RequestParam float price, @RequestParam int amount) {
        return productMapper.toProductDto(productService.editProduct(id, name, description, categoryId, price, amount));
    }

    /**
     * Processes the DELETE request along the path <i>/product/{id}</i> and deletes the product with the passed UUID.
     *
     * @param id - UUID of the product that needs to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    /**
     * Processes the DELETE request along the path <i>/product</i> and deletes all products.
     */
    @DeleteMapping
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }
}
