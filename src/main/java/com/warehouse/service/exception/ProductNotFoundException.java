package com.warehouse.service.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(UUID uuid) {
        super(String.format("Product with UUID [%s] not found", uuid));
    }
}
