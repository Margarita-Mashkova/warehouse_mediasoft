package com.warehouse.repository;

import com.warehouse.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * An interface that allows to perform various operations of the <b>Category</b> entity, such as saving, deleting,
 * searching and updating, as well as receive data using various filters, for example, by ID or other attributes.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
