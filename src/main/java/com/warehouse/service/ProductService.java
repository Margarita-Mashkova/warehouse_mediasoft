package com.warehouse.service;

import com.warehouse.model.Category;
import com.warehouse.model.Product;
import com.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * A service class that provides methods for performing CRUD operations on the <b>product</b> entity.
 */
@Service
public class ProductService {
    /**
     * Search for the appropriate bean of the ProductRepository type and inject it in this field.
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * Search for the appropriate bean of the CategoryService type and inject it in this field.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Returns a list of products that are available in the database.
     *
     * @return The list of all products or an empty list if there are no products in the database.
     */
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // TODO: добавить исключение, если нет продукта с переданным id

    /**
     * Returns the product with the passed <i>id</i> from the database if it exists.
     *
     * @param id - UUID of product.
     * @return the object of product with the passed <i>id</i>.
     */
    @Transactional(readOnly = true)
    public Product findProduct(UUID id) {
        final Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow();
    }

    /**
     * Adds the product to the database with the passed params.
     *
     * @param name        - product name.
     * @param description - product description.
     * @param categoryId  - category id to which the added product belongs.
     * @param price       - product price in rubles.
     * @param amount      - amount of product in warehouse.
     * @return the object of the added product.
     */
    @Transactional
    public Product addProduct(String name, String description, Long categoryId, float price, int amount) {
        Category category = categoryService.findCategory(categoryId);
        Date dateCreation = new Date();
        Product product = new Product(name, description, category, price, amount, dateCreation, dateCreation);
        return productRepository.save(product);
    }

    /**
     * Edits the product fields with the passed <i>id</i> if it exists.
     *
     * @param id          - UUID of the product that needs to be changed.
     * @param name        - new product name.
     * @param description - new product description.
     * @param categoryId  - new category id to which the edited product belongs.
     * @param price       - new product price in rubles.
     * @param amount      - new amount of product in warehouse.
     * @return the object of the edited product.
     */
    @Transactional
    public Product editProduct(UUID id, String name, String description, long categoryId, float price, int amount) {
        //TODO: проверка на существование продукта
        Product product = findProduct(id);
        //TODO: validation on blank lines (подумать, как изменять не все значения)
        if (!name.isBlank())
            product.setName(name);
        if (!description.isBlank())
            product.setDescription(description);
        if (categoryId != 0) {
            Category category = categoryService.findCategory(categoryId);
            product.setCategory(category);
        }
        if (price != 0)
            product.setPrice(price);
        if (product.getAmount() != amount) {
            Date dateLastChange = new Date();
            product.setDateLastChange(dateLastChange);
        }
        product.setAmount(amount);
        return productRepository.save(product);
    }

    /**
     * Deletes the product with the passed <i>id</i> from the database if it exists.
     *
     * @param id - UUID of the product that needs to be deleted.
     */
    @Transactional
    public void deleteProduct(UUID id) {
        final Product product = findProduct(id);
        productRepository.delete(product);
    }

    /**
     * Deletes all products from the database.
     */
    @Transactional
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
