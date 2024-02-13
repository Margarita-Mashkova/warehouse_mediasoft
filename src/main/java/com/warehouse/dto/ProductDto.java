package com.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * The class represents a data transfer object (DTO) for the <b>Product</b> entity.
 * Contains only the necessary data for transmission.
 */
@Data
public class ProductDto {
    /**
     * Field product UUID
     */
    private UUID id;

    /**
     * Field product name
     */
    private String name;

    /**
     * Field product description
     */
    private String description;

    //TODO: category name

    /**
     * Field product price
     */
    private float price;

    /**
     * Field  amount of product in warehouse
     */
    private int amount;

    /**
     * Field date of creation product
     */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateCreation;

    /**
     * Field date and time of the last change amount of product
     */
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateLastChange;
}
