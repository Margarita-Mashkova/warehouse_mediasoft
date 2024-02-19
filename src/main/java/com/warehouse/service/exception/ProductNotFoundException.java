package com.warehouse.service.exception;

import java.util.UUID;

/**
 * A custom exception that is thrown if there is no product with the passed UUID in the database.
 */
public class ProductNotFoundException extends RuntimeException{
    /**
     *
     * @param uuid product UUID
     */
    public ProductNotFoundException(UUID uuid) {
        super(String.format("Product with UUID [%s] not found", uuid));
    }
}
