package com.bob.db.mapper;

import com.bob.db.dto.CityDto;
import com.bob.db.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {


    CityDto toDto(CityEntity cityEntity);


    List<CityDto> toDtoList(List<CityEntity> cityEntities);


}
