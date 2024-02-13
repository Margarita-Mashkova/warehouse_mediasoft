package com.warehouse.model;

import jakarta.persistence.*;
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
    private String name;

    /**
     * Field product description
     */
    @NonNull
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
    private float price;

    /**
     * Field amount of product in warehouse
     */
    @NonNull
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
