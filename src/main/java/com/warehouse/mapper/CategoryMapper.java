package com.warehouse.mapper;

import com.warehouse.dto.CategoryDto;
import com.warehouse.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * The interface used for mapping (converting) objects between the <b>Category</b> and <b>CategoryDto</b> classes.
 * MapStruct automatically generates a mapper implementation based on this interface, which simplifies the process
 * of mapping objects between different data models.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    /**
     * Defines the mapping rule for the Category object to the CategoryDto object.
     *
     * @param category a Category object.
     * @return a CategoryDto object.
     */
    CategoryDto toCategoryDto(Category category);
}
