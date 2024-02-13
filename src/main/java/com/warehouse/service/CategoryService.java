package com.warehouse.service;

import com.warehouse.model.Category;
import com.warehouse.repository.CategoryRepository;
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

    // TODO: добавить исключение, если нет категории с переданным id

    /**
     * Returns the category with the passed <i>id</i> from the database if it exists. Else
     *
     * @param id - id of category.
     * @return the object of category with the passed <i>id</i>.
     */
    @Transactional(readOnly = true)
    public Category findCategory(long id) {
        final Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow();
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
     * @param name - category name.
     * @return
     */
    @Transactional
    public Category addCategory(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    @Transactional
    public Category editCategory(long id, String name) {
        // TODO: добавить исключение, если нет категории с переданным id
        Category category = findCategory(id);
        if (!name.isBlank())
            category.setName(name);
        return categoryRepository.save(category);
    }

    /**
     * Deletes the category with the passed <i>id</i> from the database.
     * @param id - category id.
     */
    @Transactional
    public void deleteCategory(long id) {
        final Category category = findCategory(id);
        categoryRepository.delete(category);
    }

    /**
     * Deletes all categories from the database.
     */
    @Transactional
    public void deleteAllCategories(){
        categoryRepository.deleteAll();
    }
}
