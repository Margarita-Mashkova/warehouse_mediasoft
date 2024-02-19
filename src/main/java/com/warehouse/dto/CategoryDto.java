package com.warehouse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * The class represents a data transfer object (DTO) for the <b>Category</b> entity.
 * Contains only the necessary data for transmission.
 */
@Data
public class CategoryDto {
    /**
     * Field category id
     */
    private Long id;

    /**
     * Field category name
     */
    private String name;
}
