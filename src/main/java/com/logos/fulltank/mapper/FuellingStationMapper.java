package com.logos.fulltank.mapper;

import com.logos.fulltank.dto.FuellingStationDto;
import com.logos.fulltank.entity.FuellingStation;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class FuellingStationMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    public abstract FuellingStationDto entityToDto(FuellingStation fuellingStation);
    public abstract FuellingStation dtoToEntity(FuellingStationDto fuellingStationDto);


}
