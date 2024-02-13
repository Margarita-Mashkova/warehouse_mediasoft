package com.warehouse.repository;

import com.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * An interface that allows to perform various operations of the <b>Product</b> entity, such as saving, deleting,
 * searching and updating, as well as receive data using various filters, for example, by ID or other attributes.
 */
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
