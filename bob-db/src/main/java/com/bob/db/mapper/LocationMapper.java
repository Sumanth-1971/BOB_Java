package com.bob.db.mapper;

import com.bob.db.dto.CityDto;
import com.bob.db.dto.LocationDto;
import com.bob.db.entity.CityEntity;
import com.bob.db.entity.LocationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface LocationMapper {

    LocationDto toDto(LocationEntity locationEntity);


    List<LocationDto> toDtoList(List<LocationEntity> locationEntities);
}
