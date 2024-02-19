package com.warehouse;

import com.warehouse.model.Category;
import com.warehouse.service.CategoryService;
import com.warehouse.service.exception.CategoryNotFoundException;
import com.warehouse.util.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * A set of tests for testing the methods of the Category service
 */
@SpringBootTest
public class JpaCategoryTests {
    /**
     * Search for the appropriate bean of the CategoryService type and inject it in this field.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Delete all categories before each test
     */
    @BeforeEach
    void deleteAllCategories(){
        categoryService.deleteAllCategories();
    }

    /**
     * Creating a category with a valid name (not blank and not null)
     */
    @Test
    void testCreateValidCategory(){
        String name = "test category";
        Category category = categoryService.addCategory(name);
        Assertions.assertEquals(name, categoryService.findCategory(category.getId()).getName());
    }

    /**
     * Creating a category with a not valid name (blank). An ValidationException is expected to be thrown.
     */
    @Test
    void testCreateNotValidCategory(){
        Assertions.assertThrows(ValidationException.class, () ->  categoryService.addCategory(" "));
    }

    /**
     * Search for a category by id.
     */
    @Test
    void testFindCategory(){
        String name = "find me";
        Category category = categoryService.addCategory(name);
        Category categoryFound = categoryService.findCategory(category.getId());
        Assertions.assertEquals(name, categoryFound.getName());
    }

    /**
     * Search for all categories
     */
    @Test
    void testFindAllCategories(){
        categoryService.addCategory("category1");
        categoryService.addCategory("category2");
        categoryService.addCategory("category3");
        Assertions.assertEquals(3, categoryService.findAllCategories().size());
    }

    /**
     * Changing the category name
     */
    @Test
    void testEditCategory(){
        String oldName = "oldName";
        String newName = "newName";
        Category category = categoryService.addCategory(oldName);
        Category editedCategory = categoryService.editCategory(category.getId(), newName);
        Assertions.assertEquals(newName, editedCategory.getName());
    }

    /**
     * Deleting a category with an existing id
     */
    @Test
    void testDeleteExistingCategory(){
        Category category = categoryService.addCategory("delete me");
        categoryService.deleteCategory(category.getId());
        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.findCategory(category.getId()));
    }

    /**
     * Deleting a category with not existing id
     */
    @Test
    void testDeleteNotExistingCategory(){
        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory(-1L));
    }
}
