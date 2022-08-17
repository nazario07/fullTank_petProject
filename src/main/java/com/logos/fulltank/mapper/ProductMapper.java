package com.logos.fulltank.mapper;

import com.logos.fulltank.dto.ProductDto;
import com.logos.fulltank.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "pumpSet", ignore = true)
    })
    public abstract ProductDto entityToDto(Product product);
    public abstract Product dtoToEntity(ProductDto productDto);



}
