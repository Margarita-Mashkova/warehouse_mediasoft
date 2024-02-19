package com.warehouse;

import com.warehouse.model.Product;
import com.warehouse.service.CategoryService;
import com.warehouse.service.ProductService;
import com.warehouse.service.exception.ProductNotFoundException;
import com.warehouse.util.validation.ValidationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * A set of tests for testing the methods of the Product service
 */
@SpringBootTest
public class JpaProductTests {
    /**
     * Search for the appropriate bean of the ProductService type and inject it in this field.
     */
    @Autowired
    private ProductService productService;

    /**
     * Search for the appropriate bean of the CategoryService type and inject it in this field.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Id of the test category
     */
    private Long categoryId;

    /**
     * Delete all products before each test
     */
    @BeforeEach
    void deleteAllProducts(){
        productService.deleteAllProducts();
        if(categoryId == null){
            categoryId = categoryService.addCategory("test category").getId();
        }
    }

    /**
     * Creating a product with a valid fields:
     * name and description - not blank and not null, price and amount are positive
     */
    @Test
    void testCreateValidProduct(){
        String name = "Skirt";
        String description = "Nice and beautiful";
        float price = 1000f;
        int amount = 10;
        Product product = productService.addProduct(name, description, categoryId,1000, 10);
        Product addedProduct = productService.findProduct(product.getId());
        Assertions.assertEquals(name, addedProduct.getName());
        Assertions.assertEquals(description, addedProduct.getDescription());
        Assertions.assertEquals(categoryId, addedProduct.getCategory().getId());
        Assertions.assertEquals(price, addedProduct.getPrice());
        Assertions.assertEquals(amount, addedProduct.getAmount());
    }

    /**
     * Creating a product with not valid fields:
     * name and description - blank or not null, price and amount are negative
     */
    @Test
    void testCreateNotValidProduct(){
        Assertions.assertThrows(ValidationException.class, () -> productService.addProduct(" ", " ",
                categoryId, -50, -1));
    }

    /**
     * Search for a product by id.
     */
    @Test
    void testFindProduct(){
        String name = "Ball";
        String description = "big";
        float price = 1000f;
        int amount = 10;
        Product product = productService.addProduct(name, description, categoryId, price, amount);
        Product productFound = productService.findProduct(product.getId());
        Assertions.assertEquals(name, productFound.getName());
        Assertions.assertEquals(description, productFound.getDescription());
        Assertions.assertEquals(categoryId, productFound.getCategory().getId());
        Assertions.assertEquals(price, productFound.getPrice());
        Assertions.assertEquals(amount, productFound.getAmount());
    }

    /**
     * Search for all products
     */
    @Test
    void testFindAllProducts(){
        productService.addProduct("product1", "description", categoryId, 1000f, 10);
        productService.addProduct("product2", "description", categoryId, 1000f, 10);
        Assertions.assertEquals(2, productService.findAllProducts().size());
    }

    /**
     * Changing the product fields. It is also expected that the date of the last change in the number of products will change.
     * @throws InterruptedException when a thread is waiting, sleeping, or otherwise occupied, and the thread
     * is interrupted, either before or during the activity
     */
    @Test
    void testEditProduct() throws InterruptedException {
        String oldName = "oldName";
        String newName = "newName";
        String oldDescription = "oldDescription";
        String newDescription = "newDescription";
        float oldPrice = 1000f;
        float newPrice = 5000f;
        int oldAmount = 5;
        int newAmount = 10;
        Product product = productService.addProduct(oldName, oldDescription, categoryId, oldPrice, oldAmount);
        Thread.sleep(3000);
        Product editedProduct = productService.editProduct(product.getId(), newName, newDescription, categoryId,
                                                                                                newPrice, newAmount);
        Assertions.assertEquals(newName, editedProduct.getName());
        Assertions.assertEquals(newDescription, editedProduct.getDescription());
        Assertions.assertEquals(newPrice, editedProduct.getPrice());
        Assertions.assertEquals(newAmount, editedProduct.getAmount());

        Assertions.assertNotEquals(product.getDateLastChange(), editedProduct.getDateLastChange());
    }

    /**
     * Deleting a product with an existing UUID
     */
    @Test
    void testDeleteExistingProduct(){
        Product product = productService.addProduct("Skirt", "Nice", categoryId, 2000f, 10);
        productService.deleteProduct(product.getId());
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findProduct(product.getId()));
    }

    /**
     * Deleting a product with not existing UUID
     */
    @Test
    void testDeleteNotExistingProduct(){
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(UUID.randomUUID()));
    }
}
