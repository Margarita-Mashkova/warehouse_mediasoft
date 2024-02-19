package com.warehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * A class representing the entity of the <b>product</b>
 * */

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {

    /**
     * Field UUID of product
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Field product name
     */
    @NonNull
    @NotBlank(message = "Product name can't be null or empty")
    private String name;

    /**
     * Field product description
     */
    @NonNull
    @NotBlank(message = "Product description can't be null or empty")
    private String description;

    /**
     * Field product category
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NonNull
    private Category category;

    /**
     * Field product price
     */
    @NonNull
    @Min(value = 0, message = "Product price can't be less than 0")
    private float price;

    /**
     * Field amount of product in warehouse
     */
    @NonNull
    @Min(value = 0, message = "Product amount can't be less than 0")
    private int amount;

    /**
     * Field date of creation product
     */
    @NonNull
    private Date dateCreation;

    /**
     * Field date and time of the last change amount of product
     */
    @NonNull
    private Date dateLastChange;
}
