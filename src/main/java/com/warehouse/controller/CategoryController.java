package com.warehouse.controller;

import com.warehouse.dto.CategoryDto;
import com.warehouse.mapper.CategoryMapper;
import com.warehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A web application controller that handles HTTP requests related to the <b>Category</b> entity.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    /**
     * Search for the appropriate bean of the CategoryService type and inject it in this field.
     */
    @Autowired
    CategoryService categoryService;
    /**
     * Search for the appropriate bean of the CategoryMapper type and inject it in this field.
     */
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * Processes the GET request along the path <i>/category/categories</i> and returns a list of all categories
     * as a list of CategoryDto objects.
     *
     * @return a list of CategoryDto objects.
     */
    @GetMapping("/categories")
    public List<CategoryDto> findAllCategories() {
        return categoryService.findAllCategories()
                .stream()
                .map(category -> categoryMapper.toCategoryDto(category))
                .toList();
    }

    /**
     * Processes a GET request along the path <i>/category/{id}</i> and returns category for the passed id.
     *
     * @param id - category id
     * @return the CategoryDto object for the passed category id.
     */
    @GetMapping("/{id}")
    public CategoryDto findCategory(@PathVariable Long id) {
        return categoryMapper.toCategoryDto(categoryService.findCategory(id));
    }

    /**
     * Processes the POST request along the path <i>/category</i> and creates a new category with the passed name.
     *
     * @param name - category name.
     * @return a CategoryDto object for the created category.
     */
    @PostMapping
    public CategoryDto createCategory(@RequestParam String name) {
        return categoryMapper.toCategoryDto(categoryService.addCategory(name));
    }

    /**
     * Processes the PUT request along the path <i>/category/{id}</i> and changes the name of the category with the passed ID.
     *
     * @param id   - id of the category that needs to be changed.
     * @param name - new category name.
     * @return a CategoryDto object for the edited category.
     */
    @PutMapping("/{id}")
    public CategoryDto editCategory(@PathVariable Long id, @RequestParam String name) {
        return categoryMapper.toCategoryDto(categoryService.editCategory(id, name));
    }

    /**
     * Processes the DELETE request along the path <i>/category/{id}</i> and deletes the category with the passed ID.
     *
     * @param id - id of the category that needs to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    /**
     * Processes the DELETE request along the path <i>/category</i> and deletes all categories.
     */
    @DeleteMapping
    public void deleteAllCategories() {
        categoryService.deleteAllCategories();
    }
}
