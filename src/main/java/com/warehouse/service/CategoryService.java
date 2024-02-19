package com.warehouse.service;

import com.warehouse.model.Category;
import com.warehouse.repository.CategoryRepository;
import com.warehouse.service.exception.CategoryNotFoundException;
import com.warehouse.util.validation.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    /**
     * Search for the appropriate bean of the CategoryRepository type and inject it in this field.
     */
    @Autowired
    private CategoryRepository categoryRepository;
    /**
     * Search for the appropriate bean of the ValidatorUtil type and inject it in this field.
     */
    @Autowired
    private ValidatorUtil validatorUtil;

    /**
     * Returns the category with the passed <i>id</i> from the database if it exists. Else
     *
     * @param id - id of category.
     * @return the object of category with the passed <i>id</i>.
     */
    @Transactional(readOnly = true)
    public Category findCategory(Long id) {
        final Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new CategoryNotFoundException(id));
    }

    /**
     * Returns a list of categories that are available in the database.
     *
     * @return The list of all categories or an empty list if there are no categories in the database.
     */
    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Adds the category to the database with the passed <i>name</i>.
     *
     * @param name - category name.
     * @return the object of the added category.
     */
    @Transactional
    public Category addCategory(String name) {
        Category category = new Category(name);
        validatorUtil.validate(category);
        return categoryRepository.save(category);
    }

    /**
     * Edits the category fields with the passed <i>id</i> if it exists.
     *
     * @param id   - id of the category that needs to be changed.
     * @param name - new category name.
     * @return the object of the edited category.
     */
    @Transactional
    public Category editCategory(Long id, String name) {
        Category category = findCategory(id);
        category.setName(name);
        validatorUtil.validate(category);
        return categoryRepository.save(category);
    }

    /**
     * Deletes the category with the passed <i>id</i> from the database.
     *
     * @param id - category id.
     */
    @Transactional
    public void deleteCategory(Long id) {
        final Category category = findCategory(id);
        categoryRepository.delete(category);
    }

    /**
     * Deletes all categories from the database.
     */
    @Transactional
    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }
}
