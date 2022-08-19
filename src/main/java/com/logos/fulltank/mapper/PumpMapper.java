package com.logos.fulltank.mapper;

import com.logos.fulltank.dto.PumpDto;
import com.logos.fulltank.entity.Pump;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PumpMapper {
    public static final PumpMapper INSTANCE = Mappers.getMapper(PumpMapper.class);

    public abstract PumpDto entityToDto(Pump pump);
    public abstract Pump dtoToEntity(PumpDto pumpDto);
}
