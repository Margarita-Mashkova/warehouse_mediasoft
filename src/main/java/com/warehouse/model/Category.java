package com.warehouse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * A class representing the entity of the <b>category</b>
 * */

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Category {
    /**
     * Field category id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Filed category name
     */
    @NonNull
    private String name;

    /**
     * List of products of the category
     */
    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Product> products;
}
