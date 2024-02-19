package com.warehouse.service.exception;

/**
 * A custom exception that is thrown if there is no category with the passed id in the database.
 */
public class CategoryNotFoundException extends RuntimeException{
    /**
     *
     * @param id category id
     */
    public CategoryNotFoundException(Long id){
        super(String.format("Category with id [%d] not found", id));
    }
}
