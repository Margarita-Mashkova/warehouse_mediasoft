package com.warehouse.mapper;

import com.warehouse.dto.ProductDto;
import com.warehouse.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * The interface used for mapping (converting) objects between the <b>Product</b> and <b>ProductDto</b> classes.
 * MapStruct automatically generates a mapper implementation based on this interface, which simplifies the process
 * of mapping objects between different data models.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    /**
     * Defines the mapping rule for the Product object to the ProductDto object.
     *
     * @param product - a Product object.
     * @return a ProductDto object.
     */
    ProductDto toProductDto(Product product);
}